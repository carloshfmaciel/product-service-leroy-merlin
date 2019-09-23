package br.com.leroymerlin.productservice.jms.consumer;

import java.io.IOException;

import javax.jms.JMSException;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.leroymerlin.productservice.jms.message.ProductImportSheetMessage;
import br.com.leroymerlin.productservice.service.ProductImportSheetService;
import br.com.leroymerlin.productservice.utils.JsonUtils;

@Component
public class ProductJmsConsumer {

	private Logger logger = LoggerFactory.getLogger(ProductJmsConsumer.class);

	@Autowired
	private ProductImportSheetService productImportSheetService;

	@JmsListener(destination = "jms.message.product.sheet")
	public void receiveMessage(Object message) throws JMSException, JsonParseException, JsonMappingException, IOException {
		logger.info("Received " + message);

		if (message instanceof ActiveMQTextMessage) {
			ActiveMQTextMessage activeMQTextMessage = (ActiveMQTextMessage) message;
			
			ProductImportSheetMessage bodyMessage = (ProductImportSheetMessage) JsonUtils
					.getObject(activeMQTextMessage.getText(), ProductImportSheetMessage.class);
			
			productImportSheetService.processSheet(bodyMessage);
		} else {
			throw new IllegalArgumentException(
					String.format("Invalid message at queue %s", "jms.message.product.sheet"));
		}

	}

}
