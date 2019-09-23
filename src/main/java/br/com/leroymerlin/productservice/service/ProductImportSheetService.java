package br.com.leroymerlin.productservice.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.leroymerlin.productservice.constant.Messages;
import br.com.leroymerlin.productservice.jms.message.ProductImportSheetMessage;
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
	private ProductRepository productRepository;

	@Autowired
	private ProductSheetUtils productSheetUtils;

	public ProductImportSheetResponse importSheet(MultipartFile file) throws IOException {

		ProductImportSheetLogORM productImportSheetLogORMSaved = productImportSheetLogService.create(file);

		ProductImportSheetMessage message = new ProductImportSheetMessage();
		message.setFileName(file.getOriginalFilename());
		message.setFileContent(file.getBytes());
		message.setProcessId(productImportSheetLogORMSaved.getId());

		productJmsProducer.sendMessage(message);

		ProductImportSheetResponse response = new ProductImportSheetResponse(productImportSheetLogORMSaved.getId(),
				Messages.IMPORT_SHEET_SUCCESS);

		return response;
	}

	public void processSheet(ProductImportSheetMessage message) {

		try {

			productImportSheetLogService.updateStatusInProcessing(message.getProcessId());

			File sheetFile = new File("product.xlsx");
			FileUtils.writeByteArrayToFile(sheetFile, message.getFileContent());

			List<ProductORM> products = productSheetUtils.extractProducts(sheetFile);

			List<String> errors = new ArrayList<String>();

			products.forEach(product -> {
				try {
					productRepository.save(product);
				} catch (Exception e) {
					errors.add(String.format("Error to save product: %s", product));
				}
			});

			if (!errors.isEmpty()) {
				productImportSheetLogService.updateStatusError(message.getProcessId(), errors.toString());
			} else {
				productImportSheetLogService.updateStatusSuccess(message.getProcessId());
			}

		} catch (Exception e) {
			productImportSheetLogService.updateStatusError(message.getProcessId(), e.getMessage());
			logger.error(e.getMessage(), e);
		}

	}

}
