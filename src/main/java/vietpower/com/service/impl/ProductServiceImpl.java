package vietpower.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.ProductBaseCanDao;
import vietpower.com.dao.ProductDao;
import vietpower.com.model.Product;
import vietpower.com.model.ProductBaseCan;
import vietpower.com.service.ProductService;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductBaseCanDao productBaseCanDao;

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.persist(product);
    }

    @Override
    public List<ProductBaseCan> findAllProductBaseCan() {
        return productBaseCanDao.findAll();
    }

    @Override
    public void saveProductBaseCan(ProductBaseCan pbc) {
        productBaseCanDao.persist(pbc);
    }
}
