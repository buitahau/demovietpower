package vietpower.com.service;

import vietpower.com.model.Base;
import vietpower.com.model.ProductBase;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface BaseService {
    List<Base> findAll();
    List<ProductBase> findAllProductBases();

    void save(Base base);

    void update(Base base);

    void saveProductBase(ProductBase pb);

    void updateProductBase(ProductBase pb);
}
