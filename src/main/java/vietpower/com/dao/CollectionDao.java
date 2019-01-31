package vietpower.com.dao;

import vietpower.com.model.Collection;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface CollectionDao {
    List<Collection> findAll();

    void persist(Collection c);

    void update(Collection collection);

    Collection findById(Long collectionId);

    void delete(Collection collection);

    List<Collection> find(Collection collection);

    Collection findByCodeAndMachine(String collectionName, Long machineId);
}
