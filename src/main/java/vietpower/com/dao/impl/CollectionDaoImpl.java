package vietpower.com.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.CollectionDao;
import vietpower.com.model.Collection;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Repository("collectionDao")
public class CollectionDaoImpl extends AbstractDao<Integer, Collection> implements CollectionDao{
    @Override
    public List<Collection> findAll() {
        return (List<Collection>) createEntityCriteria().list();
    }

    @Override
    public Collection findById(Long collectionId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("collectionId", collectionId));
        Collection collection = (Collection)crit.uniqueResult();
        return collection;
    }
}
