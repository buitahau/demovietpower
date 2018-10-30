package hau.kute.dao.impl;

import hau.kute.dao.AbstractDao;
import hau.kute.model.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HauKute on 8/4/2018.
 */
@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile> implements hau.kute.dao.UserProfileDao {
    @Override
    public List<UserProfile> findAll() {
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("type"));
        return (List<UserProfile>)crit.list();
    }

    @Override
    public UserProfile findByType(String type) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("type", type));
        return (UserProfile)crit.uniqueResult();
    }

    @Override
    public UserProfile findById(int id) {
        return (UserProfile) getByKey(id);
//        Criteria crit = createEntityCriteria();
//        crit.add(Restrictions.eq("id", id));
//        return (UserProfile)crit.uniqueResult();
    }
}
