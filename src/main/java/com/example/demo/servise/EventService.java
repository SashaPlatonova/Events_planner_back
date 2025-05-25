package com.example.demo.servise;

import com.example.demo.entity.ClientEvent;
import com.example.demo.entity.Event;
import com.example.demo.repository.ClientEventRepo;
import com.example.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;
    @Autowired
    private ClientEventRepo clientEventRepo;

    public List<Event> getAll() {
        return (List<Event>) repository.findAll();
    }

    public List<Event> getAllByOwnerId(int id) {
        return repository.findAllByOwner(id);
    }

    public List<Event> findByTitleAndOwner(String title, int owner) {
        return repository.findByTitleAndOwner(title, owner);
    }

    public Event getById(int id){
        return repository.findEventById(id);
    }

    public List<Event> getByCat(int id, int client){
        List<ClientEvent> clientEvents = clientEventRepo.getByCat(id, client);
        List<Event> events = new ArrayList<>();
        for (ClientEvent clientEvent : clientEvents) {
            events.add(clientEvent.getEvent());
        }
        return events;
    }

    public void addEvent(Event event){
        if(event.getId()>0) {
            repository.save(event);
        }
        else {
            Event newEvent = repository.save(event);
            clientEventRepo.addClientEvent(newEvent.getId(), event.getOwner().getId());
        }

    }

    public List<Event> getByMonthAndCat(int month, int id){
        return repository.getByMonthAndCat(month, id);
    }

    public void delete(Event event){
        repository.delete(event);
    }

}
