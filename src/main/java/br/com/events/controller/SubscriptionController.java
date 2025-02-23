package br.com.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.events.dto.ErrorMessage;
import br.com.events.dto.SubscriptionResponse;
import br.com.events.exception.EventNotFoundException;
import br.com.events.exception.SubscriptionConflictException;
import br.com.events.exception.UserIndicadorNotFoundException;
import br.com.events.model.User;
import br.com.events.service.SubscriptionService;

@RestController
public class SubscriptionController {

	@Autowired
	private SubscriptionService service;

	@PostMapping({ 
		"/subscription/{prettyName}", 
		"/subscription/{prettyName}/{userId}" 
		})
	public ResponseEntity<?> createSubscription(@PathVariable String prettyName, 
												@RequestBody User subscribe, 
												@PathVariable(required=false) Integer userId) {
		try {
			SubscriptionResponse res = service.createNewSubscription(prettyName, subscribe, userId);
			if (res != null) {

				return ResponseEntity.ok(res);
			}
		} catch (EventNotFoundException ex) {

			return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));

		} catch (SubscriptionConflictException ex) {

			return ResponseEntity.status(409).body(new ErrorMessage(ex.getMessage()));
		} catch (UserIndicadorNotFoundException ex) {
			return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
		}

		return ResponseEntity.badRequest().build();
	}
}
