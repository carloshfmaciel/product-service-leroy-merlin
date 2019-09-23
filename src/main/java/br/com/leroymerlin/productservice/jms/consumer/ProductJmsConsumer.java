package br.com.leroymerlin.productservice.jms.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import br.com.leroymerlin.productservice.jms.message.ProductImportSheetMessage;
import br.com.leroymerlin.productservice.service.ProductImportSheetService;

@Component
public class ProductJmsConsumer {

	private Logger logger = LoggerFactory.getLogger(ProductJmsConsumer.class);
	
	@Autowired
	private ProductImportSheetService productImportSheetService;
	
	@JmsListener(destination = "jms.message.product.sheet")
	public void receiveMessage(ProductImportSheetMessage message) {
		logger.info("Received " + message);
		
		productImportSheetService.processSheet(message);

	}

}
