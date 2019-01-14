package vietpower.com.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.CustomerDao;
import vietpower.com.model.Customer;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Repository("customerDao")
public class CustomerDaoImpl extends AbstractDao<Integer, Customer> implements CustomerDao {
    @Override
    public List<Customer> findAll(Long machineId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("machine.machineId", machineId));
        return (List<Customer>) crit.list();
    }

    @Override
    public Customer findById(Long machineId, Long customerId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("customerId", customerId));
        crit.add(Restrictions.eq("machine.machineId", machineId));
        Customer c = (Customer)crit.uniqueResult();
        return c;
    }
}
