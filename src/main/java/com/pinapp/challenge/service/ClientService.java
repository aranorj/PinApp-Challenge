package com.pinapp.challenge.service;

import com.pinapp.challenge.datatransfer.ClientDTO;
import com.pinapp.challenge.datatransfer.ClientDTORes;
import com.pinapp.challenge.datatransfer.KpiDTO;
import com.pinapp.challenge.exception.AgeConflictException;
import com.pinapp.challenge.persistence.entities.Client;

import java.text.ParseException;
import java.util.List;


public interface ClientService {

    Client createClient(ClientDTO clientDTO) throws ParseException, AgeConflictException;

    List<ClientDTORes> getAllClients();

    KpiDTO getKpiAllClientes();
}
