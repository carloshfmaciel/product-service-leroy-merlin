package br.com.leroymerlin.productservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.leroymerlin.productservice.orm.ProductORM;
import br.com.leroymerlin.productservice.repository.ProductRepository;

@Service
public class ProductService {

	private Logger logger = LoggerFactory.getLogger(ProductImportSheetService.class);
	
	@Autowired
	private ProductRepository productRepository;

	public ProductORM getById(Long id) {
		Optional<ProductORM> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new EntityNotFoundException("Produto não encontrado!");
		}
	}

	public List<ProductORM> getAllByPage(Integer pageNumber, Integer pageSize) {

		Page<ProductORM> productsResult = productRepository
				.findAll(PageRequest.of(pageNumber == null ? 0 : pageNumber, pageSize == null ? 25 : pageSize));
		List<ProductORM> products = productsResult.stream().collect(Collectors.toList());

		if (!products.isEmpty()) {
			return products;
		} else {
			throw new EntityNotFoundException("Produto não encontrado!");
		}
	}
	
	public void deleteById(Long id) {
		Optional<ProductORM> optional = productRepository.findById(id);
		
		if(optional.isPresent()) {
			productRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("O Produto não existe!");
		}
	}
	
	public void update(ProductORM product) {
		try {
			productRepository.save(product);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new IllegalArgumentException("O Produto informado não pode ser atualizado!");
		}
		
	}

}
