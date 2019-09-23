package br.com.leroymerlin.productservice.rest.validator;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import br.com.leroymerlin.productservice.constant.MimeType;

@Component
public class ProductImportSheetValidator {

	public void validate(MultipartFile file) {
		
		if(file.isEmpty()) {
			throw new IllegalArgumentException("Arquivo inválido!");
		}
		
		validateMimeType(file);
		
	}
	
	public void validateMimeType(MultipartFile file) {
		Tika tika = new Tika();
		try {
			String mimeType = tika.detect(file.getOriginalFilename());
			if(!mimeType.equals(MimeType.XLS_MIME_TYPE)) {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Arquivo inválido!");
		}
	}
	
}
