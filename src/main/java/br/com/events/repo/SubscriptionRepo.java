package br.com.events.repo;

import org.springframework.data.repository.CrudRepository;

import br.com.events.model.Event;
import br.com.events.model.Subscription;
import br.com.events.model.User;

public interface SubscriptionRepo extends CrudRepository<Subscription, Integer> {
	public Subscription findByEventAndSubscriber(Event evt, User user);
}
