package br.com.leroymerlin.productservice.rest.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.leroymerlin.productservice.orm.ProductImportSheetLogORM;
import br.com.leroymerlin.productservice.orm.ProductORM;
import br.com.leroymerlin.productservice.rest.response.ProductImportSheetResponse;
import br.com.leroymerlin.productservice.rest.validator.ProductImportSheetValidator;
import br.com.leroymerlin.productservice.service.ProductImportSheetLogService;
import br.com.leroymerlin.productservice.service.ProductImportSheetService;
import br.com.leroymerlin.productservice.service.ProductService;

@RestController
@SuppressWarnings("rawtypes")
public class ProductController {

	@Autowired
	private ProductImportSheetValidator productImportSheetValidator;

	@Autowired
	private ProductImportSheetService productImportSheetService;
	
	@Autowired
	private ProductImportSheetLogService productImportSheetLogService;
	
	@Autowired
	private ProductService productService;

	@PostMapping(path = "/products/sheets", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity importProductSheet(@RequestParam("sheetFile") MultipartFile sheetFile) throws IOException {

		productImportSheetValidator.validate(sheetFile);

		ProductImportSheetResponse response = productImportSheetService.importSheet(sheetFile);

		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "/products/sheets/status/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity getImportSheetStatus(@PathVariable("id") Long importProcessId) {
		ProductImportSheetLogORM log = productImportSheetLogService.getById(importProcessId);
		return ResponseEntity.ok(log);
	}

	@GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity getAllProducts(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		List<ProductORM> products = productService.getAllByPage(pageNumber, pageSize);
		return ResponseEntity.ok(products);
	}

	@GetMapping(path = "/products/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity getProductById(@PathVariable("id") Long productId) {
		ProductORM product = productService.getById(productId);
		return ResponseEntity.ok(product);
	}

	@PutMapping(path = "/products", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity updateProduct(@RequestBody ProductORM product) {
		productService.update(product);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/products/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity deleteProductById(@PathVariable("id") Long productId) {
		productService.deleteById(productId);
		return ResponseEntity.ok().build();
	}

}
