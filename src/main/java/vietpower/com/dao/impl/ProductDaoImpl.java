package vietpower.com.dao.impl;

import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.ProductDao;
import vietpower.com.model.Product;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<Integer, Product> implements ProductDao {

    @Override
    public List<Product> findAll() {
        List<Product> listProducts = (List<Product>) createEntityCriteria().list();
        return listProducts;
    }
}
