package vietpower.com.service;

import vietpower.com.model.Base;
import vietpower.com.model.ProductBase;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface ProductBaseService {

    List<ProductBase> findByProductId (Long productId);

    List<Base> findByProduct (Long productId);
}
