package vietpower.com.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.FormulaColourantDao;
import vietpower.com.dao.FormulaCustomerDao;
import vietpower.com.model.FormulaColourant;
import vietpower.com.model.FormulaCustomer;
import vietpower.com.model.Machine;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Repository("FormulaCustomerDao")
public class FormulaCustomerDaoImpl extends AbstractDao<Integer, FormulaCustomer> implements FormulaCustomerDao {
    @Override
    public List<FormulaCustomer> findByFormulaId(Long formulaId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("formula.formulaId", formulaId));
        return (List<FormulaCustomer>) criteria.list();
    }

    @Override
    public List<FormulaCustomer> findByMachine(Long machineId) {
        Criteria criteria = createEntityCriteria();
        if(machineId != null) {
            Machine machine = new Machine();
            machine.setMachineId(machineId);
            criteria.add(Restrictions.isNotNull("formula.machine"));
            criteria.add(Restrictions.eq("formula.machine", machine));
        }
        return (List<FormulaCustomer>) criteria.list();
    }
}