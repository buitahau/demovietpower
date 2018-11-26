package vietpower.com.dao;

import vietpower.com.model.ProductBaseCan;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface ProductBaseCanDao {
    List<ProductBaseCan> findAll();

    void persist(ProductBaseCan pbc);
}
