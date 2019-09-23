package br.com.leroymerlin.productservice.jms.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductJmsProducer {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void sendMessage(Object msg) {
		jmsTemplate.convertAndSend("jms.message.product.sheet", msg);
	}
	
}
