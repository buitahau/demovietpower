package vietpower.com.service.impl;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.CollectionDao;
import vietpower.com.dao.MachineDao;
import vietpower.com.model.Collection;
import vietpower.com.model.Machine;
import vietpower.com.service.CollectionService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Service("collectionService")
@Transactional
public class CollectionServiceImp implements CollectionService {
    @Autowired
    private CollectionDao collectionDao;

    @Autowired
    private MachineDao machineDao;

    @Override
    public List<Collection> findAll() {
        return collectionDao.findAll();
    }

    @Override
    public void save(Collection c) {
        collectionDao.persist(c);
    }

    @Override
    public Collection addOrUpdate(Collection collection) {
        if(collection.getMachine() != null && collection.getMachine().getMachineId() != null && collection.getMachine().getMachineId() > 0){
            Machine _machine = this.machineDao.findById(collection.getMachine().getMachineId());
            collection.setMachine(_machine);
        }
        if(collection.getCollectionId() != null && collection.getCollectionId() > 0){
            collectionDao.update(collection);
        } else {
            collection.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            collectionDao.persist(collection);
        }

        return collection;
    }

    @Override
    public Collection findById(Long collectionId) {
        return collectionDao.findById(collectionId);
    }

    @Override
    public void deleteCollection(Long collectionId) {
        Collection dbItem = this.collectionDao.findById(collectionId);
        collectionDao.delete(dbItem);
    }

    @Override
    public List<Collection> find(Collection collection) {
        return collectionDao.find(collection);
    }

    @Override
    public Collection saveOrUpdate(Collection collection) {
        if(collection.getCollectionId() != null && collection.getCollectionId() > 0){
            collectionDao.update(collection);
        } else {
            collection.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            collectionDao.persist(collection);
        }
        return collection;
    }
}
