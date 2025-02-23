package br.com.events.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.events.dto.SubscriptionResponse;
import br.com.events.exception.EventNotFoundException;
import br.com.events.exception.SubscriptionConflictException;
import br.com.events.exception.UserIndicadorNotFoundException;
import br.com.events.model.Event;
import br.com.events.model.Subscription;
import br.com.events.model.User;
import br.com.events.repo.EventRepo;
import br.com.events.repo.SubscriptionRepo;
import br.com.events.repo.UserRepo;

@Service
public class SubscriptionService {

	@Autowired
	private EventRepo evtRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private SubscriptionRepo subRepo;

	public SubscriptionResponse createNewSubscription(String eventName, User user, Integer userId) {

		// Recuperar o evento pelo nome
		Event evt = evtRepo.findByPrettyName(eventName);
		if (evt == null) { // Caso alternativo 2
			throw new EventNotFoundException("Evento " + eventName + " Não existe");
		}

		User userRec = userRepo.findByEmail(user.getEmail());
		if (userRec == null) { // Caso alternativo 1
			userRec = userRepo.save(user);
		}
		
		User indicador = userRepo.findById(userId).orElse(null);
		if (indicador == null) {
			throw new UserIndicadorNotFoundException("Usuario "+userId+" indicador não existe");
		}

		Subscription subs = new Subscription();
		subs.setEvent(evt);
		subs.setSubscriber(userRec);
		subs.setIndication(indicador);

		Subscription tmpSub = subRepo.findByEventAndSubscriber(evt, userRec);
		if (tmpSub != null) { // Caso alternativo 3
			throw new SubscriptionConflictException(
					"Já existe inscrição para o usuário " + userRec.getName() + " no evento " + evt.getTitle());
		}

		Subscription res = subRepo.save(subs);

		return new SubscriptionResponse(res.getSubscriptionNumber(),
				"http://codecraft.com/subscription/" + res.getEvent().getPrettyName() + "/" + res.getSubscriber().getId());
	}
}
