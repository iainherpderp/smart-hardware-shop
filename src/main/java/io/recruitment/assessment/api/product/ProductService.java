package io.recruitment.assessment.api.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Transactional
    List<Product> findProductPage(int pageNumber) {
        Page<Product> page = productRepository.findAll(PageRequest.of(pageNumber, 50));
        return page.toList();
    }

    @Transactional
    Product findById(long id) {
        return productRepository.findByIdNotNull(id);
    }

    @Transactional
    Collection<Product> saveAll(Collection<Product> products) {
        return productRepository.saveAll(products);
    }

    @Transactional
    Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    void delete(long id) {
        productRepository.deleteById(id);
    }

}
