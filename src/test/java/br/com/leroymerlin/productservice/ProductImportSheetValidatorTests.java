package br.com.leroymerlin.productservice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.leroymerlin.productservice.rest.validator.ProductImportSheetValidator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductImportSheetValidatorTests {

	@InjectMocks
	private ProductImportSheetValidator validator;

	private File validFile = null;
	
	private File invalidFile = null;
	
	@Before
	public void setup() {
		
		Path resourceDirectory = Paths.get("src", "test", "resources");
		
		String resourceName = "product.docx";
		invalidFile = new File(resourceDirectory.toFile().getAbsolutePath() + "\\" + resourceName);
		
		resourceName = "product_valid_mime_type.xlsx";
		validFile = new File(resourceDirectory.toFile().getAbsolutePath() + "\\" + resourceName);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenEmptyFile_WhenValidateFile_ThenReturnsIllegalArgumentException() {
		validator.validateFileIsEmpty(new MockMultipartFile("product.xlsx", new byte[] {}));
	}

	@Test
	public void givenEmptyFile_WhenValidateFile_ThenReturnsNoException() throws IOException {
		validator.validateFileIsEmpty(new MockMultipartFile(validFile.getName(), FileUtils.readFileToByteArray(validFile)));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenFileWithInvalidMimeType_WhenValidateFile_ThenReturnsIllegalArgumentException() throws IOException {
		validator.validateMimeType(new MockMultipartFile(invalidFile.getName(), invalidFile.getName(), "",
				Files.readAllBytes(invalidFile.toPath())));
	}

	@Test
	public void givenValidFileWithValidMimeType_WhenValidate_ThenReturnsNoException() throws IOException {
		validator.validateMimeType(new MockMultipartFile(validFile.getName(), validFile.getName(), "",
				Files.readAllBytes(validFile.toPath())));
	}
	
}
