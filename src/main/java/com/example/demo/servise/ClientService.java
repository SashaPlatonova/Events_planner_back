package com.example.demo.servise;

import com.example.demo.entity.Client;
import com.example.demo.entity.Event;
import com.example.demo.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepo repository;

    public List<Client> getAll() {
        return (List<Client>) repository.findAll();
    }
    public Client getById(int id){
        return repository.findClientById(id);
    }

    public void add(Client client) {
        if(client.getId()!=0) {
            Client newClient = repository.save(client);
            newClient.setId(client.getId());
        }
        else {
            int id = (int) (Math.random() * 100) +100;
            client.setId(id);
            repository.save(client);
        }
    }

    public void deleteById(Client client){
        repository.deleteById(client.getId());
    }
}
