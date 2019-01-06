package vietpower.com.dao;

import vietpower.com.model.Base;
import vietpower.com.model.ProductBase;

import java.util.List;

/**
 * Created by HauKute on 11/26/2018.
 */
public interface ProductBaseDao {
    List<ProductBase> findAll();

    void persist(ProductBase pb);

    void update(ProductBase pb);

    List<ProductBase> findByProductId(Long productId);

    List<ProductBase> findByProduct(Long productId);
}
