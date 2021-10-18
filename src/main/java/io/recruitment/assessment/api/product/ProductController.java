package io.recruitment.assessment.api.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping(value = "/page/{pageNumber}")
    public ResponseEntity<List<Product>> getPage(@PathVariable int pageNumber) {
        return ResponseEntity.ok(service.findProductPage(pageNumber));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Collection<Product>> createProducts(@RequestBody Collection<Product> products) {
        return ResponseEntity.ok(service.saveAll(products));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product) {
        product.setId(id);
        return ResponseEntity.ok(service.save(product));
    }

    @DeleteMapping
    public ResponseEntity<Product> deleteProduct(@RequestBody Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
