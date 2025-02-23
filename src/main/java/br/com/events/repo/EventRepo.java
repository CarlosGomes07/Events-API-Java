package br.com.events.repo;

import org.springframework.data.repository.CrudRepository;

import br.com.events.model.Event;

public interface EventRepo extends CrudRepository<Event, Integer> {
	 public Event findByPrettyName(String prettyName);
}
