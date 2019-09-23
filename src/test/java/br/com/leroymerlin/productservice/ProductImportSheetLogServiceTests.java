package br.com.leroymerlin.productservice;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.leroymerlin.productservice.orm.ProductImportSheetLogORM;
import br.com.leroymerlin.productservice.repository.ProductImportSheetLogRepository;
import br.com.leroymerlin.productservice.service.ProductImportSheetLogService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductImportSheetLogServiceTests {

	@InjectMocks
	private ProductImportSheetLogService service;
	
	@Mock
	private ProductImportSheetLogRepository repository;
	
	@Test(expected = IllegalArgumentException.class)
	public void givenInexistingImportProductSheetProcessId_WhenGetImportSheetLogByProcessId_ThenReturnsIllegalArgumentException() {
		when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
		
		service.getLogById(1L);
	}

	@Test
	public void givenExistingImportProductSheetProcessId_WhenGetImportSheetLogByProcessId_ThenReturnsNonNullObject() {
		when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(new ProductImportSheetLogORM()));
		
		ProductImportSheetLogORM productImportSheetLogORM = service.getLogById(1L);
		
		assertNotNull(productImportSheetLogORM);
	}
	
}
