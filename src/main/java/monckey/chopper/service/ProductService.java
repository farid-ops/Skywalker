package monckey.chopper.service;

import monckey.chopper.entity.Product;

import javax.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.Optional;

public interface ProductService {

    @NotNull Iterator<Product> getAllProducts();

    Optional<Product> getProduct(String productId);
}
