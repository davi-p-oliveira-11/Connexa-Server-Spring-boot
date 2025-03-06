package com.nlw.events.repo;

import org.springframework.data.repository.CrudRepository;

import com.nlw.events.model.Event;

public interface EventRepo extends CrudRepository<Event, Integer> {
   public Event findByPrettyName(String prettyName);
}
