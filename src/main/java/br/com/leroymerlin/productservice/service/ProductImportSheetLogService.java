package br.com.leroymerlin.productservice.service;

import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.leroymerlin.productservice.constant.ProductSheetProcessStatus;
import br.com.leroymerlin.productservice.jpa.builder.ProductImportSheetBuilder;
import br.com.leroymerlin.productservice.orm.ProductImportSheetLogORM;
import br.com.leroymerlin.productservice.repository.ProductImportSheetLogRepository;

@Service
public class ProductImportSheetLogService {

	@Autowired
	private ProductImportSheetBuilder productImportSheetBuilder;

	@Autowired
	private ProductImportSheetLogRepository productImportSheetLogRepository;

	public ProductImportSheetLogORM createLog(MultipartFile file) {
		ProductImportSheetLogORM productImportSheetLogORM = productImportSheetBuilder.build(file);
		productImportSheetLogORM = productImportSheetLogRepository.save(productImportSheetLogORM);

		return productImportSheetLogORM;
	}

	public void updateLogStatusInProcessing(Long processId) {
		Optional<ProductImportSheetLogORM> optional = productImportSheetLogRepository.findById(processId);
		ProductImportSheetLogORM productImportSheetLogORM = optional.get();
		productImportSheetLogORM.setStatus(ProductSheetProcessStatus.PROCESSING);
		productImportSheetLogRepository.save(productImportSheetLogORM);
	}
	
	public void updateLogStatusSuccess(Long processId) {
		Optional<ProductImportSheetLogORM> optional = productImportSheetLogRepository.findById(processId);
		ProductImportSheetLogORM productImportSheetLogORM = optional.get();
		productImportSheetLogORM.setStatus(ProductSheetProcessStatus.PROCESSED_SUCCESS);
		productImportSheetLogORM.setEndProcessDate(new Date());
		productImportSheetLogRepository.save(productImportSheetLogORM);
	}
	
	public void updateLogStatusError(Long processId, String errorMessage) {
		Optional<ProductImportSheetLogORM> optional = productImportSheetLogRepository.findById(processId);
		ProductImportSheetLogORM productImportSheetLogORM = optional.get();
		productImportSheetLogORM.setStatus(ProductSheetProcessStatus.PROCESSED_ERROR);
		productImportSheetLogORM.setEndProcessDate(new Date());
		productImportSheetLogORM.setMessageError(errorMessage);
		productImportSheetLogRepository.save(productImportSheetLogORM);
	}
	
	public ProductImportSheetLogORM getLogById(Long id) {
		Optional<ProductImportSheetLogORM> optional = productImportSheetLogRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			throw new IllegalArgumentException("Log de processamento inválido!");
		}
	}

}
