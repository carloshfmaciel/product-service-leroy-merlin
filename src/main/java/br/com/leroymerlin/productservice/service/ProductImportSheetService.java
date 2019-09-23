package br.com.leroymerlin.productservice.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import br.com.leroymerlin.productservice.constant.Messages;
import br.com.leroymerlin.productservice.jms.message.ProductImportSheetMessage;
import br.com.leroymerlin.productservice.jms.message.builder.ProductImportSheetMessageBuilder;
import br.com.leroymerlin.productservice.jms.producer.ProductJmsProducer;
import br.com.leroymerlin.productservice.orm.ProductImportSheetLogORM;
import br.com.leroymerlin.productservice.orm.ProductORM;
import br.com.leroymerlin.productservice.repository.ProductRepository;
import br.com.leroymerlin.productservice.rest.response.ProductImportSheetResponse;
import br.com.leroymerlin.productservice.utils.ProductSheetUtils;

@Service
public class ProductImportSheetService {

	private Logger logger = LoggerFactory.getLogger(ProductImportSheetService.class);

	@Autowired
	private ProductJmsProducer productJmsProducer;

	@Autowired
	private ProductImportSheetLogService productImportSheetLogService;

	@Autowired
	private ProductImportSheetMessageBuilder productImportMessageBuilder;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductSheetUtils productSheetUtils;

	public ProductImportSheetResponse importSheet(MultipartFile file) throws IOException {

		ProductImportSheetLogORM productImportSheetLogORM = productImportSheetLogService.createLog(file);

		ProductImportSheetMessage message = productImportMessageBuilder.build(file, productImportSheetLogORM);

		productJmsProducer.sendMessage(message);

		ProductImportSheetResponse response = new ProductImportSheetResponse(productImportSheetLogORM.getId(),
				Messages.IMPORT_SHEET_SUCCESS);

		return response;
	}

	public void processSheet(ProductImportSheetMessage message) {

		try {

			productImportSheetLogService.updateLogStatusInProcessing(message.getProcessId());

			List<ProductORM> products = productSheetUtils.extractProducts(new ByteArrayInputStream(message.getFileContent()));

			List<String> errors = new ArrayList<String>();

			products.forEach(product -> {
				try {
					productRepository.save(product);
				} catch (Exception e) {
					errors.add(String.format("Error to save product: %s", product));
				}
			});

			if (!errors.isEmpty()) {
				productImportSheetLogService.updateLogStatusError(message.getProcessId(), errors.toString());
			} else {
				productImportSheetLogService.updateLogStatusSuccess(message.getProcessId());
			}

		} catch (Exception e) {
			productImportSheetLogService.updateLogStatusError(message.getProcessId(), e.getMessage());
			logger.error(e.getMessage(), e);
		}

	}

}
