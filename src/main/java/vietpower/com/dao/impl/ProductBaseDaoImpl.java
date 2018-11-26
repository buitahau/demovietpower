package vietpower.com.dao.impl;

import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.ProductBaseDao;
import vietpower.com.model.ProductBase;

import java.util.List;

/**
 * Created by HauKute on 11/26/2018.
 */
@Repository("productBaseDao")
public class ProductBaseDaoImpl extends AbstractDao<Integer, ProductBase> implements ProductBaseDao {
    @Override
    public List<ProductBase> findAll() {
        return (List<ProductBase>) createEntityCriteria().list();
    }
}
