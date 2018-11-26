package vietpower.com.dao;

import vietpower.com.model.Product;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface ProductDao {
    List<Product> findAll();

    void persist(Product product);
}
