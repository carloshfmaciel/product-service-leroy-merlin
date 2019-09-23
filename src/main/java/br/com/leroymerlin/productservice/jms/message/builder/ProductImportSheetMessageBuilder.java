package br.com.leroymerlin.productservice.jms.message.builder;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.leroymerlin.productservice.jms.message.ProductImportSheetMessage;
import br.com.leroymerlin.productservice.orm.ProductImportSheetLogORM;

@Component
public class ProductImportSheetMessageBuilder {

	public ProductImportSheetMessage build(MultipartFile file, ProductImportSheetLogORM productImportSheetLogORM)
			throws IOException {
		ProductImportSheetMessage message = new ProductImportSheetMessage();
		message.setFileName(file.getOriginalFilename());
		message.setFileContent(file.getBytes());
		message.setProcessId(productImportSheetLogORM.getId());

		return message;
	}

}
