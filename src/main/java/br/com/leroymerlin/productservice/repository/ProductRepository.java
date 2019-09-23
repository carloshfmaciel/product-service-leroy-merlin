package br.com.leroymerlin.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.leroymerlin.productservice.orm.ProductORM;

/**
 * 
 * @author carloshfmaciel
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductORM, Long> {

}
