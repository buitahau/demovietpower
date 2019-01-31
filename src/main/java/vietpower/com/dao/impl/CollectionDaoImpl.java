package vietpower.com.dao.impl;

import org.apache.commons.lang3.StringUtils;
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

    @Override
    public List<Collection> find(Collection collection) {
        Criteria crit = createEntityCriteria();
        if (collection != null){
            if(StringUtils.isNotBlank(collection.getCollectionName())){
                crit.add(Restrictions.ilike("collectionName", "%" + collection.getCollectionName() + "%"));
            }
            if(collection.getMachine() != null && collection.getMachine().getMachineId() != null){
                if(collection.getMachine().getMachineId() == -1){
                    crit.add(Restrictions.isNull("machine"));
                }else{
                    crit.add(Restrictions.eq("machine.machineId", collection.getMachine().getMachineId()));
                }
            }
        }
        return (List<Collection>) crit.list();
    }

    @Override
    public Collection findByCodeAndMachine(String collectionName, Long machineId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("collectionName", collectionName));
        crit.add(Restrictions.eq("machine.machineId", machineId));
        return (Collection)crit.uniqueResult();
    }
}
