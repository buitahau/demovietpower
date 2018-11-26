package vietpower.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.BaseDao;
import vietpower.com.dao.ProductBaseDao;
import vietpower.com.model.Base;
import vietpower.com.model.ProductBase;
import vietpower.com.service.BaseService;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Service("baseService")
@Transactional
public class BaseServiceImpl implements BaseService {
    @Autowired
    private BaseDao baseDao;
    @Autowired
    private ProductBaseDao productBaseDao;

    @Override
    public List<Base> findAll() {
        return baseDao.findAll();
    }

    @Override
    public List<ProductBase> findAllProductBases() {
        return productBaseDao.findAll();
    }

    @Override
    public void save(Base base) {
        baseDao.persist(base);
    }

    @Override
    public void update(Base base) {
        baseDao.update(base);
    }

    @Override
    public void saveProductBase(ProductBase pb) {
        productBaseDao.persist(pb);
    }

    @Override
    public void updateProductBase(ProductBase pb) {
        productBaseDao.update(pb);
    }
}
