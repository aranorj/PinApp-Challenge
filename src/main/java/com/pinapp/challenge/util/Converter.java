package com.pinapp.challenge.util;

import com.pinapp.challenge.datatransfer.ClientDTO;
import com.pinapp.challenge.datatransfer.ClientDTORes;
import com.pinapp.challenge.persistence.entities.Client;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class Converter {

    @Autowired
    ModelMapper modelMapper;

    public ClientDTORes convertClientToDto(Client client) {
        DateTimeFormatter latinFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ClientDTORes clientDTO = modelMapper.map(client, ClientDTORes.class);
        clientDTO.setFechaNacimiento(client.getFechaNacimiento().format(latinFormat));
        return clientDTO;
    }

    public Client convertDtoToClient(ClientDTO clientDTO){
        return modelMapper.map(clientDTO, Client.class);
    }


}
