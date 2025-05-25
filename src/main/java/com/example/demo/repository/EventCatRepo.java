package com.example.demo.repository;

import com.example.demo.entity.Event;
import com.example.demo.entity.EventCategory;
import org.springframework.data.repository.CrudRepository;

public interface EventCatRepo extends CrudRepository<EventCategory, Integer> {

    EventCategory findEventCategoriesById(int id);
}
