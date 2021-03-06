package vietpower.com.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.MachineColourantLogDao;
import vietpower.com.model.MachineColourantLog;
import vietpower.com.model.MachineFormulaProductBase;

import java.util.List;

/**
 * Created by HauKute on 12/20/2018.
 */
@Repository("machineColourantLogDao")
public class MachineColourantLogDaoImpl extends AbstractDao<Integer, MachineColourantLog> implements MachineColourantLogDao {
    @Override
    public List<MachineColourantLog> getByMachineColourant(Long machineColourantLogId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("machineColourant.machineColourantId", machineColourantLogId));
        crit.addOrder(Order.desc("createdDate"));
        List<MachineColourantLog> res = (List<MachineColourantLog>)crit.list();
        return res;
    }

    @Override
    public void deleteAll() {
        Query query = getSession().createQuery("delete MachineColourantLog");
        query.executeUpdate();
    }

}
