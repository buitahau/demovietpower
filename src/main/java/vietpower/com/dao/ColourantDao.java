package vietpower.com.dao;

import vietpower.com.model.Colourant;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface ColourantDao {

    List<Colourant> findAll();

    void save(Colourant colourant);

    void update(Colourant colourant);

    void persist(Colourant colourant);

    Colourant findById(Long colourantId);

    void deleteAll();
}
