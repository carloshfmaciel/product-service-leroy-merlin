package br.com.leroymerlin.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.leroymerlin.productservice.orm.ProductImportSheetLogORM;

@Repository
public interface ProductImportSheetLogRepository extends JpaRepository<ProductImportSheetLogORM, Long> {

}
