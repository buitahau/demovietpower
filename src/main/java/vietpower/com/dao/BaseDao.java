package vietpower.com.dao;

import vietpower.com.model.Base;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface BaseDao {
    List<Base> findAll();

    void persist(Base base);

    void update(Base base);
}
