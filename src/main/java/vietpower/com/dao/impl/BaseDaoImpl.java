package vietpower.com.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.BaseDao;
import vietpower.com.model.Base;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Repository("baseDao")
public class BaseDaoImpl extends AbstractDao<Integer, Base> implements BaseDao{

    @Override
    public List<Base> findAll() {
        List<Base> listBases = (List<Base>) createEntityCriteria().list();
        return listBases;
    }

    @Override
    public void deleteAll() {
        Query query = getSession().createQuery("delete Base");
        query.executeUpdate();
    }
}
