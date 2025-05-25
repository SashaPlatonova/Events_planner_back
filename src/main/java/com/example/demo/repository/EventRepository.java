package com.example.demo.repository;

import com.example.demo.entity.ClientEvent;
import com.example.demo.entity.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

    @Query(value = "SELECT * FROM event as e where e.owner_id=:owner", nativeQuery = true)
    List<Event> findAllByOwner(int owner);

    Event findEventById(int id);

    @Query(value = "SELECT * FROM event where extract(month from start_time)=:month and category=:id", nativeQuery = true)
    List<Event> getByMonthAndCat(int month, int id);

    @Query(value = "SELECT * FROM event as e where e.title=:title and e.owner_id=:owner", nativeQuery = true)
    List<Event> findByTitleAndOwner(String title, int owner);
}
