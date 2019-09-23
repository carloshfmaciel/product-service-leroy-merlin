package br.com.leroymerlin.productservice.utils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import br.com.leroymerlin.productservice.orm.ProductORM;

@Component
public class ProductSheetUtils {

	@SuppressWarnings("resource")
	public List<ProductORM> extractProducts(InputStream sheetFileInpuStream) throws IOException {

		Workbook workbook = new XSSFWorkbook(sheetFileInpuStream);
		Sheet sheet = workbook.getSheetAt(0);

		Row firstRow = sheet.getRow(0);
		Cell cell = firstRow.getCell(1);

		Long categoryId = getCellValueAsLong(cell);

		List<ProductORM> products = new ArrayList<ProductORM>();

		Row row = null;
		for (int rowIndex = 3; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			row = sheet.getRow(rowIndex);

			ProductORM productORM = new ProductORM();
			productORM.setProductCode(getCellValueAsLong(row.getCell(0)));
			productORM.setProductName(row.getCell(1).getStringCellValue());
			productORM.setFreeShipping(getCellValueAsString(row.getCell(2)).equals("1") ? true : false);
			productORM.setProductDesc(row.getCell(3).getStringCellValue());
			productORM.setProductPrice(new BigDecimal(row.getCell(4).getStringCellValue()));
			productORM.setCategoryId(categoryId);

			products.add(productORM);

		}

		return products;
	}

	private Long getCellValueAsLong(Cell cell) {
		if (cell.getCellType().equals(CellType.NUMERIC)) {
			return Double.valueOf(cell.getNumericCellValue()).longValue();
		} else {
			return Long.valueOf(cell.getStringCellValue());
		}
	}

	private String getCellValueAsString(Cell cell) {
		if (cell.getCellType().equals(CellType.NUMERIC)) {
			return new BigDecimal(cell.getNumericCellValue()).toBigInteger().toString();
		} else {
			return cell.getStringCellValue();
		}
	}

}
