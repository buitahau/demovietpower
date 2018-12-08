package vietpower.com.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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

    @Override
    public List<ProductBaseCan> findByProductAndBase(Long productId, Long baseId) {
        Criteria criteria = createEntityCriteria();
        if(productId != null && baseId != null && productId > 0 && baseId > 0) {
            criteria.add(Restrictions.eq("productBase.product.productId", productId));
            criteria.add(Restrictions.eq("productBase.base.baseId", baseId));
        }
        return (List<ProductBaseCan>) criteria.list();
    }

    @Override
    public List<ProductBaseCan> findByProductBase(Long productBaseId) {
        Criteria criteria = createEntityCriteria();
        if(productBaseId != null && productBaseId > 0) {
            criteria.add(Restrictions.eq("productBase.productBaseId", productBaseId));
        }
        return (List<ProductBaseCan>) criteria.list();
    }
}
