package vietpower.com.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.MachineDao;
import vietpower.com.model.Machine;

import java.util.List;

/**
 * Created by HauKute on 12/14/2018.
 */
@Repository("machineDao")
public class MachineDaoImpl extends AbstractDao<Integer, Machine> implements MachineDao {
    @Override
    public List<Machine> findAll() {
        return (List<Machine>) createEntityCriteria().list();
    }

    @Override
    public Machine findById(Long machineId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("machineId", machineId));
        Machine machine = (Machine)crit.uniqueResult();
        return machine;
    }
}
