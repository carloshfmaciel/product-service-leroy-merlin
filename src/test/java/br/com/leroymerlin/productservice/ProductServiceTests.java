package br.com.leroymerlin.productservice;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.leroymerlin.productservice.orm.ProductORM;
import br.com.leroymerlin.productservice.repository.ProductRepository;
import br.com.leroymerlin.productservice.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ProductServiceTests {

	@InjectMocks
	private ProductService productService;

	@Mock
	private ProductRepository productRepository;

	@Test
	public void givenExistingProductId_WhenGetProductById_ThenReturnsProduct() {
		when(productRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(new ProductORM()));

		ProductORM productORM = productService.getById(1L);

		assertNotNull(productORM);
	}

	@Test(expected = EntityNotFoundException.class)
	public void givenInexistentProductId_WhenGetProductById_ThenReturnsEntityNotFoundException() {
		when(productRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

		productService.getById(1L);
	}

	@Test(expected = EntityNotFoundException.class)
	public void givenThatDoesntExistProduct_WhenGetAllProducts_ThenReturnsEntityNotFoundException() {

		Integer pageNumber = 0;
		Integer pageSize = 25;

		when(productRepository.findAll(PageRequest.of(pageNumber, pageSize)))
				.thenReturn(new PageImpl(new ArrayList<>(0)));

		productService.getAllByPage(pageNumber, pageSize);
	}

	@Test
	public void givenExistingProductId_WhenGetDeleteById_ThenReturnsNoException() {
		
		long productId = ArgumentMatchers.anyLong();
		
		when(productRepository.findById(productId)).thenReturn(Optional.of(new ProductORM(productId)));

		productService.deleteById(productId);
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenInexistentProductId_WhenDeleteById_ThenReturnsEntityNotFoundException() {
		when(productRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

		productService.deleteById(ArgumentMatchers.anyLong());
	}

}
