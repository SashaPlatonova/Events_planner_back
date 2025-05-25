package com.example.demo.repository;

import com.example.demo.entity.Preference;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PreferenceRepo extends CrudRepository<Preference, Integer> {

}
