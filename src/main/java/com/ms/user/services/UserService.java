package com.ms.user.services;

import com.ms.user.domain.User;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserProducer producer;

	@Transactional
	public User save(User user){
		User userSaved = userRepository.save(user);
		producer.publishMessageEmail(userSaved);
		return userSaved;
	}
}
