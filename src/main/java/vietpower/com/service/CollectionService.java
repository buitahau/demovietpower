package vietpower.com.service;

import vietpower.com.model.Collection;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface CollectionService {
    List<Collection> findAll();

    void save(Collection c);

    Collection addOrUpdate(Collection collection);

    Collection findById(Long collectionId);
}
