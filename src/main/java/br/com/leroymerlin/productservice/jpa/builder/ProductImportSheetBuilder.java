package br.com.leroymerlin.productservice.jpa.builder;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.leroymerlin.productservice.constant.ProductSheetProcessStatus;
import br.com.leroymerlin.productservice.orm.ProductImportSheetLogORM;

@Component
public class ProductImportSheetBuilder {

	public ProductImportSheetLogORM build(MultipartFile file) {
		
		ProductImportSheetLogORM orm = new ProductImportSheetLogORM();
		orm.setFileName(file.getOriginalFilename());
		orm.setStartProcessDate(new Date());
		orm.setStatus(ProductSheetProcessStatus.WAITING_PROCESS);
		
		return orm;
	}
	
}
