package vietpower.com.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.FormulaDao;
import vietpower.com.model.Formula;
import vietpower.com.model.Machine;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Repository("formulaDao")
public class FormulaDaoImpl extends AbstractDao<Integer, Formula> implements FormulaDao {
    @Override
    public List<Formula> findAll() {
        return (List<Formula>) createEntityCriteria().list();
    }

    @Override
    public List<Formula> findByCollection(Long collectionId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("collection.collectionId", collectionId));
        return (List<Formula>) crit.list();
    }

    @Override
    public Formula findById(Long formulaId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("formulaId", formulaId));
        Formula formula = (Formula)crit.uniqueResult();
        return formula;
    }

    @Override
    public void deleteAll() {
        Query query = getSession().createQuery("delete Formula");
        query.executeUpdate();
    }

    @Override
    public Formula findByCode(String formulaCode, Long machineId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("formulaCode", formulaCode));
        crit.add(Restrictions.eq("machine.machineId", machineId));
        Formula formula = (Formula)crit.uniqueResult();
        return formula;
    }
}
