package com.example.demo.repository;

import com.example.demo.entity.ClientEvent;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientEventRepo extends CrudRepository<ClientEvent, Integer> {

    @Query(value = "select " +
                   "CASE WHEN ce.id is null THEN (1000+event.id) ELSE ce.id END as id," +
                   "CASE WHEN ce.client_id is null THEN event.owner_id ELSE ce.client_id END as client_id," +
                   "CASE WHEN ce.event_id is null THEN event.id ELSE ce.event_id END as event_id" +
                   " from client_event ce right join event on " +
                   "event.id=ce.event_id left join client on client.id = ce.client_id " +
                   "where (ce.client_id=:id or event.owner_id=:id) " +
                   "and event.category=:catId and extract(month from event.start_time)=:month"
            ,nativeQuery = true)
    List<ClientEvent> getByClientAndDateAndCat(int id, int catId, int month);

    @Query(value = "select " +
                   "CASE WHEN ce.id is null THEN (1000+event.id) ELSE ce.id END as id," +
                   "CASE WHEN ce.client_id is null THEN event.owner_id ELSE ce.client_id END as client_id," +
                   "CASE WHEN ce.event_id is null THEN event.id ELSE ce.event_id END as event_id" +
                   " from client_event ce right join event on " +
                   "event.id=ce.event_id left join client on client.id = ce.client_id " +
                   "where (ce.client_id=:id or event.owner_id=:id) "
            ,nativeQuery = true)
    List<ClientEvent> getByClient(int id);

    @Query(value = "SELECT * FROM client_event as ce join client c on c.id = ce.client_id" +
            " join event e on e.id = ce.event_id where ce.event_id=:id and e.owner_id!=c.id"
            ,nativeQuery = true)
    List<ClientEvent> getByEvent(int id);

    @Query(value = "SELECT * FROM client_event ce join client c on c.id = ce.client_id join event e " +
            "on ce.event_id = e.id " +
            " where category=:id and (ce.client_id=:client or e.owner_id=:client)", nativeQuery = true)
    List<ClientEvent> getByCat(int id, int client);

    @Query(value = "select * from client_event cl join event e on cl.event_id = e.id " +
            "where e.owner_id!=:client and cl.client_id=:client", nativeQuery = true)
    List<ClientEvent> getInvitation(int client);


    @Modifying
    @Transactional
    @Query(value = "insert into client_event values (default, :clientId, :eventId)", nativeQuery = true)
    void addClientEvent(int eventId, int clientId);

    @Modifying
    @Transactional
    @Query(value = "delete from client_event ce where ce.event_id =:event_id", nativeQuery = true)
    void updateGuestList(int event_id);

    @Modifying
    @Transactional
    @Query(value = "delete from client_event ce where client_id=:client and event_id=:event", nativeQuery = true)
    void deleteByClientAndEvent(int client, int event);
}
