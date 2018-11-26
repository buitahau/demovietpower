package vietpower.com.dao.impl;

import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.ProductBaseCanDao;
import vietpower.com.model.ProductBaseCan;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Repository("productBaseCanDao")
public class ProductBaseCanDaoImpl extends AbstractDao<Integer, ProductBaseCan> implements ProductBaseCanDao {
    @Override
    public List<ProductBaseCan> findAll() {
        List<ProductBaseCan> list = (List<ProductBaseCan>) createEntityCriteria().list();
        return list;
    }
}
