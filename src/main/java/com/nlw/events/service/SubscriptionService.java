package com.nlw.events.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlw.events.exception.EventNotFoundException;
import com.nlw.events.model.Event;
import com.nlw.events.model.Subscription;
import com.nlw.events.model.User;
import com.nlw.events.repo.EventRepo;
import com.nlw.events.repo.SubscriptionRepo;
import com.nlw.events.repo.UserRepo;

@Service
public class SubscriptionService {
	
	@Autowired
	private EventRepo evtRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private SubscriptionRepo subRepo;
	
	public Subscription createNewSubscription(String eventName, User user) {
		
		// return event by name
		Event evt = evtRepo.findByPrettyName(eventName);
		if (evt == null) {
			throw new EventNotFoundException("Evento " + eventName + " nao existe");
		}
		User userRec = userRepo.findByEmail(user.getEmail());
		if (userRec == null) {
			userRec = userRepo.save(user);
		}
		user = userRepo.save(user);
		
		Subscription subs = new Subscription();
		subs.setEvent(evt);
		subs.setSubscriber(user);
		
		Subscription res = subRepo.save(subs);
		return res;
	}

}

