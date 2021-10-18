package io.recruitment.assessment.api.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    default Product findByIdNotNull(Long id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Product found with ID " + id));
    }
}
