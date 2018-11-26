package vietpower.com.service;

import vietpower.com.model.Product;
import vietpower.com.model.ProductBaseCan;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface ProductService {

    List<Product> findAll();

    void save(Product product);

    List<ProductBaseCan> findAllProductBaseCan();

    void saveProductBaseCan(ProductBaseCan pbc);
}
