package vietpower.com.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.ColourantDao;
import vietpower.com.model.Colourant;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Repository("colourantDao")
public class ColourantDaoImpl extends AbstractDao<Integer, Colourant> implements ColourantDao {

    @Override
    public List<Colourant> findAll() {
        List<Colourant> listColourants = (List<Colourant>) createEntityCriteria().list();
        return listColourants;
    }

    @Override
    public void save(Colourant colourant) {
        persist(colourant);
    }

    @Override
    public Colourant findById(Long colourantId) {
        return getByKey(colourantId.intValue());
    }
}
