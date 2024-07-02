package com.ms.user.producers;

import com.ms.user.domain.User;
import com.ms.user.dto.EmailDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value(value = "${broker.queue.email.name}")
	private String routingKey;

	public void publishMessageEmail(User user){
		var emailDto = new EmailDTO();
		emailDto.setEmailId(user.getId());
		emailDto.setEmailTo(user.getEmail());
		emailDto.setSubject("Cadstro realizado com sucesso!");
		emailDto.setText(user.getName() + ", seja bem vindo(a)! \nAgradecemos o seu cadastro!");

		rabbitTemplate.convertAndSend("", routingKey, emailDto);
	}
}
