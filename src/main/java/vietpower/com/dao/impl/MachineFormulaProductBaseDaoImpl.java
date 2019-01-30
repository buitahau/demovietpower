package vietpower.com.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.MachineFormulaProductBaseDao;
import vietpower.com.model.Machine;
import vietpower.com.model.MachineFormulaProductBase;

import java.util.List;

/**
 * Created by HauKute on 12/20/2018.
 */
@Repository("machineFormulaProductBaseDao")
public class MachineFormulaProductBaseDaoImpl extends AbstractDao<Integer, MachineFormulaProductBase> implements MachineFormulaProductBaseDao{
    @Override
    public List<MachineFormulaProductBase> getByMachine(Long machineId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("machine.machineId", machineId));
        List<MachineFormulaProductBase> res = (List<MachineFormulaProductBase>)crit.list();
        return res;
    }

    @Override
    public MachineFormulaProductBase findById(Long taskId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("machineFormulaProductBaseId", taskId));
        MachineFormulaProductBase record = (MachineFormulaProductBase)crit.uniqueResult();
        return record;
    }

    @Override
    public void deleteAll() {
        Query query = getSession().createQuery("delete MachineFormulaProductBase");
        query.executeUpdate();
    }
}
