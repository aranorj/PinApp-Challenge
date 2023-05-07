package com.pinapp.challenge.service;

import com.pinapp.challenge.datatransfer.ClientDTO;
import com.pinapp.challenge.datatransfer.ClientDTORes;
import com.pinapp.challenge.datatransfer.KpiDTO;
import com.pinapp.challenge.exception.AgeConflictException;
import com.pinapp.challenge.persistence.entities.Client;
import com.pinapp.challenge.persistence.repositories.ClientRepository;
import com.pinapp.challenge.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    Converter converter;

    @Override
    public Client createClient(ClientDTO clientDTO) throws AgeConflictException {
        validateAge(clientDTO);
        Client client = converter.convertDtoToClient(clientDTO);
        client.setFechaNacimiento(LocalDate.parse(clientDTO.getFechaNacimiento()));
        clientRepository.saveAndFlush(client);
        return client;
    }

    private void validateAge(ClientDTO clientDTO) throws AgeConflictException {
        Integer edad = clientDTO.getEdad();
        LocalDate today = LocalDate.now();
        LocalDate fechaNacimiento = LocalDate.parse(clientDTO.getFechaNacimiento());
        Integer calculatedAge = Period.between(fechaNacimiento, today).getYears();
        Integer calculatedBirthYear = today.minusYears(edad).getYear();
        if(!calculatedAge.equals(edad)){
            String message = String.format("Su fecha de nacimiento no coincide con la edad ingresada. " +
                            "Hoy es %s. Si su día de nacimiento es correcto, usted tiene %s años o su nacimiento fue en %s. " +
                            "Reintente la operación introduciendo datos validos",
                    today, calculatedAge, calculatedBirthYear);
            throw new AgeConflictException(message);
        }

    }


    private List<Integer> getAllAges() {
         return clientRepository.findAllAges().orElse(new ArrayList<>());
    }

    @Override
    public List<ClientDTORes> getAllClients(){
        List<Client> clients = clientRepository.findAll();
        DateTimeFormatter latinFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<ClientDTORes> clientDTOList = new ArrayList<>();
        for(Client client : clients){
            ClientDTORes clientDTO =  converter.convertClientToDto(client);
            LocalDate fechaMuerteProbable = getProbableDeathDate(client.getEdad());
            clientDTO.setFechaMuerteProbable(fechaMuerteProbable.format(latinFormat));
            clientDTOList.add(clientDTO);
        }
        return clientDTOList;
    }

    private LocalDate getProbableDeathDate(Integer edad) {
        double  yearsToAdd =getLifeExpectancyByAge(edad);
        long daysToAdd = Math.round(yearsToAdd * 365.25); // Aprox. dias del año para incluir feriados
        return LocalDate.now().plusDays(daysToAdd);
    }

    private Double getLifeExpectancyByAge(Integer edad) {

        //Parametros para una población hipotética con alta expectativa de vida

        double A = 3.0; // tasa de mortalidad constante en la población
        double B = 0.0001; // tasa de mortalidad que varía en función de la edad
        double C = 0.00016; // tasa de aceleración de la mortalidad a medida que la edad aumenta

        // Calcular la probabilidad de supervivencia para la edad actual segun la Ley de Gompertz-Makeham
        double q = Math.exp(-(A + B * edad + C * Math.pow(edad, 2)));

        // Calcular la expectativa de vida restante en base a las probababilidades año a año
        double lifeExpectancy = 0.0;
        int maxAge = 102;
        for (int i = edad; i < maxAge; i++) {
            double qi = Math.exp(-(A + B * i + C * Math.pow(i, 2)));
            lifeExpectancy += qi;
        }
        lifeExpectancy /= q;

        return lifeExpectancy;
    }

    @Override
    public KpiDTO getKpiAllClientes() {
        List<Integer> edades = getAllAges();
        Double promedio = getAVGFromList(edades);
        Double desviacionEstandar = getStandarDeviation(edades, promedio);

        return KpiDTO.builder()
                .promedioEdades(promedio)
                .desviacionEstandar(desviacionEstandar)
                .build();
    }

    private Double getStandarDeviation(List<Integer> lista, Double media) {
        double varianza = lista.stream()
                .mapToInt(Integer::intValue)
                .mapToDouble(num -> Math.pow(num - media, 2))
                .sum() / (lista.size() - 1);

        return Math.sqrt(varianza);
    }

    private Double getAVGFromList(List<Integer> list) {
       return list.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }
}
