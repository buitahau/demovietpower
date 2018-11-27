package vietpower.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.CollectionDao;
import vietpower.com.model.Collection;
import vietpower.com.service.CollectionService;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Service("collectionService")
@Transactional
public class CollectionServiceImp implements CollectionService {
    @Autowired
    private CollectionDao collectionDao;

    @Override
    public List<Collection> findAll() {
        return collectionDao.findAll();
    }

    @Override
    public void save(Collection c) {
        collectionDao.persist(c);
    }
}
