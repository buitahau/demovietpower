package vietpower.com.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.ProductBaseDao;
import vietpower.com.model.Base;
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

    @Override
    public List<ProductBase> findByProductId(Long productId) {
        Criteria criteria = createEntityCriteria();
        if(productId != null) {
            criteria.add(Restrictions.eq("product.productId", productId));
        }
        return (List<ProductBase>) criteria.list();
    }

    @Override
    public List<ProductBase> findByProduct(Long productId) {
        Criteria criteria = createEntityCriteria();
        if(productId != null) {
            criteria.add(Restrictions.eq("product.productId", productId));
        }
        return (List<ProductBase>) criteria.list();
    }
}
