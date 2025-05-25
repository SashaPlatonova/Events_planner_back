package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.servise.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MainController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientEventService clientEventService;
    @Autowired
    private EventService eventService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PreferenceService preferenceService;

    @GetMapping("/clients/all")
    public List<Client> gelAll() {
        return clientService.getAll();
    }
    @GetMapping("/clients/id")
    public Client getById(@RequestParam int id){
        return clientService.getById(id);
    }
    @PostMapping("/client/add")
    public void addClient(@RequestBody Client client){
        clientService.add(client);
    }
    @PutMapping("/client/update")
    public void updateClient(@RequestBody Client client){
        clientService.add(client);
    }

    @DeleteMapping("/client/delete")
    public void deleteClient(@RequestBody Client client){
        clientService.deleteById(client);
    }

    @GetMapping("/invitation/all")
    public List<ClientEvent> getALl() {
        return clientEventService.getAll();
    }

    @GetMapping("/invitation/client/params")
    public List<Event> getEventsByClientAndDateAndCat(@RequestParam int id, @RequestParam int cat, @RequestParam int month){
        return clientEventService.getAllByClientAndDateAndCat(id, cat, month);
    }
    @GetMapping("/invitation/client")
    public List<Event> getEventsByClient(@RequestParam int id){
        return clientEventService.getAllByClient(id);
    }

    @GetMapping("/invitation/notowner")
    public List<Event> getInvitation(@RequestParam int client) {
        return clientEventService.getInvitation(client);
    }

    @GetMapping("/invitation/event")
    public List<Client> getClientByEvent(@RequestParam int id){
        return clientEventService.getByEvent(id);
    }

    @PostMapping("/invite")
    public void inviteClients(@RequestBody InvitationRequest request){
        clientEventService.inviteClients(request);
    }

    @DeleteMapping("/invitation/delete")
    public void deleteInvite(@RequestParam int client, int event){
        clientEventService.deleteInvitation(client, event);
    }

    @GetMapping("/category/all")
    public List<EventCategory> getAllCats() {
        return categoryService.getAll();
    }
    @GetMapping("/category/id")
    public EventCategory getCatById(@RequestParam int id){
        return categoryService.getById(id);
    }

    @PostMapping("/category/add")
    public void addCat(@RequestBody EventCategory category){
        categoryService.addCat(category);
    }

    @GetMapping("/events/all")
    public List<Event> getAllEvent() {
        return eventService.getAll();
    }

    @GetMapping("/events/id")
    public Event getEventById(@RequestParam int id){
        return eventService.getById(id);
    }

    @GetMapping("/events/owner")
    public List<Event> getByOwner(@RequestParam int owner){
        return eventService.getAllByOwnerId(1);
    }
    @GetMapping("/events/title")
    public List<Event> getByTitle(@RequestParam String title, int owner){
        return eventService.findByTitleAndOwner(title, owner);
    }
    @GetMapping("/events/month")
    public List<Event> getByMonth(@RequestParam int month, @RequestParam int id){
        return eventService.getByMonthAndCat(month, id);
    }

    @GetMapping("/events/category")
    public List<Event> getByCat(@RequestParam int category, @RequestParam int client){
        return eventService.getByCat(category, client);
    }

    @PostMapping("/events/add")
    public void addEvent(@RequestBody Event event){
        eventService.addEvent(event);
    }

    @PutMapping("/events/update")
    public void updateEvent(@RequestBody Event event){
        eventService.addEvent(event);
    }

    @DeleteMapping("/event/delete")
    public void deleteEvent(@RequestBody Event event){
        eventService.delete(event);
    }

    @GetMapping("/preferences/all")
    public List<Preference> getAllPref(){
        return preferenceService.getAll();
    }

    @GetMapping("/preferences/client")
    public List<Preference> getPrefByClient(@RequestParam int client) {
        return preferenceService.getByClient(client);
    }

    @PostMapping("/preferences/add")
    public void addPref(@RequestBody PreferenceRequest request)
    {
        preferenceService.addClientPreference(request);
    }

}
