package vietpower.com.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.MachineColourantDao;
import vietpower.com.model.MachineColourant;

import java.util.List;

/**
 * Created by HauKute on 12/20/2018.
 */
@Repository("machineColourantDao")
public class MachineColourantDaoImpl extends AbstractDao<Integer, MachineColourant> implements MachineColourantDao {
    @Override
    public MachineColourant findByMachineAndColour(Long machineId, Long colourantId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("machine.machineId", machineId));
        crit.add(Restrictions.eq("colourant.colourantId", colourantId));
        MachineColourant machineColourant = (MachineColourant)crit.uniqueResult();
        return machineColourant;
    }

    @Override
    public List<MachineColourant> findByMachineId(Long machineId) {
        Criteria criteria = createEntityCriteria();
        if(machineId != null) {
            criteria.add(Restrictions.eq("machine.machineId", machineId));
        }
        return (List<MachineColourant>) criteria.list();
    }
}
