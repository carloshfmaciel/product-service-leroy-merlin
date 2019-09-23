package br.com.leroymerlin.productservice;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.leroymerlin.productservice.orm.ProductORM;
import br.com.leroymerlin.productservice.utils.ProductSheetUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductSheetUtilsTests {

	private static final int PRODUCT_LIST_SIZE = 6;
	
	private File file = null;

	@InjectMocks
	private ProductSheetUtils productSheetUtils;

	@Before
	public void setup() {
		Path resourceDirectory = Paths.get("src", "test", "resources");

		String resourceName = "product_valid_mime_type.xlsx";
		file = new File(resourceDirectory.toFile().getAbsolutePath() + "\\" + resourceName);
	}

	@Test
	public void givenValidSheetWithTreeProducts_WhenExtractProductsFromSheet_ThenReturnsListWithTreeProducts()
			throws IOException {
		List<ProductORM> products = productSheetUtils.extractProducts(new ByteArrayInputStream(FileUtils.readFileToByteArray(file)));
		
		assertNotNull(products);
		assertFalse(products.isEmpty());
		assertTrue(products.size() == PRODUCT_LIST_SIZE);
	}

}
