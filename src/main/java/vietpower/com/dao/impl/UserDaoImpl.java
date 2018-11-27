package vietpower.com.dao.impl;

import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.UserDao;
import vietpower.com.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HauKute on 8/4/2018.
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
    @Override
    public User findById(int id) {
        User user = getByKey(id);
        return user;
    }

    @Override
    public User findBySSO(String sso) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", sso));
        User user = (User)crit.uniqueResult();
        return user;
    }

    @Override
    public void save(User user) {
        persist(user);
    }

    @Override
    public void deleteBySSO(String sso) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", sso));
        User user = (User)crit.uniqueResult();
        delete(user);
    }

    @Override
    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("userName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// avoid duplicate
        List<User> users = (List<User>) criteria.list();

        return users;
    }

    @Override
    public User findByUserName(String userName) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("userName", userName));
        User user = (User)crit.uniqueResult();
        return user;
    }

}
