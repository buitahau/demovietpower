package vietpower.com.dao.impl;

import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.ProductDao;
import vietpower.com.model.Product;

/**
 * Created by HauKute on 11/25/2018.
 */
@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<Integer, Product> implements ProductDao {
}
