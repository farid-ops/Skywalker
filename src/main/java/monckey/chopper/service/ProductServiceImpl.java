package monckey.chopper.service;

import monckey.chopper.entity.Product;
import monckey.chopper.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Iterator<Product> getAllProducts() {
        return this.productRepository.findAll().iterator();
    }

    @Override
    public Optional<Product> getProduct(String productId) {
        return this.productRepository.findById(UUID.fromString(productId));
    }
}
