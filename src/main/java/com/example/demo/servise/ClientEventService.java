package com.example.demo.servise;

import com.example.demo.entity.Client;
import com.example.demo.entity.ClientEvent;
import com.example.demo.entity.Event;
import com.example.demo.entity.InvitationRequest;
import com.example.demo.repository.ClientEventRepo;
import com.example.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ClientEventService {
    @Autowired
    private ClientEventRepo repository;
    @Autowired
    private EventRepository eventRepository;

    public List<ClientEvent> getAll() {
        return (List<ClientEvent>) repository.findAll();
    }

    public List<Event> getAllByClientAndDateAndCat(int id, int catId, int month){
        Set<Event> events = new HashSet<>();
        List<ClientEvent> clientEvents = repository.getByClientAndDateAndCat(id, catId, month);
        for (ClientEvent clientEvent : clientEvents) {
            events.add(clientEvent.getEvent());
        }
        System.out.println(events);
        return new ArrayList<>(events);
    }

    public List<Event> getAllByClient(int id){
        List<Event> events = new ArrayList<>();
        List<ClientEvent> clientEvents = repository.getByClient(id);
        for (ClientEvent clientEvent : clientEvents) {
            if(!events.contains(clientEvent.getEvent())) {
                events.add(clientEvent.getEvent());
            }
        }
        return events;
    }

    public List<Client> getByEvent(int id){
        List<ClientEvent> clientEvents = repository.getByEvent(id);
        List<Client> clients = new ArrayList<>();
        for (ClientEvent clientEvent : clientEvents) {
            clients.add(clientEvent.getClient());
        }
        return clients;
    }

    public void inviteClients(InvitationRequest request){
        List<Client> clients = request.getClients();
        Event event = request.getEvent();
        if(event.getId()>0) {
            eventRepository.save(event);
            if(!clients.isEmpty()) {
                repository.updateGuestList(event.getId());
                for (Client client : clients) {
                    repository.save(new ClientEvent(client, event));
                }
            }
        }
        else {
            Event newEvent = eventRepository.save(event);
            repository.updateGuestList(event.getId());
            for (Client client : clients) {
                repository.save(new ClientEvent(client, newEvent));
            }
        }
    }

    public List<Event> getInvitation(int client){
        Set<Event> events = new HashSet<>();
        List<ClientEvent> clientEvents = repository.getInvitation(client);
        for (ClientEvent clientEvent : clientEvents) {
            events.add(clientEvent.getEvent());
        }
        return new ArrayList<>(events);
    }

    public void deleteInvitation(int client, int event){
        repository.deleteByClientAndEvent(client, event);
    }
}
