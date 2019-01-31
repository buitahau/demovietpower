package vietpower.com.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.FormulaProductBaseDao;
import vietpower.com.model.FormulaProductBase;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Repository("formulaProductBaseCanDao")
public class FormulaProductBaseDaoImpl extends AbstractDao<Integer, FormulaProductBase> implements FormulaProductBaseDao {
    @Override
    public List<FormulaProductBase> findByFormulaId(Long formulaId) {
        Criteria criteria = createEntityCriteria();
        if(formulaId != null) {
            criteria.add(Restrictions.eq("formula.formulaId", formulaId));
        }
        return (List<FormulaProductBase>) criteria.list();
    }

    @Override
    public FormulaProductBase findById(Long formulaProductBaseId) {
        Criteria criteria = createEntityCriteria();
        if(formulaProductBaseId != null) {
            criteria.add(Restrictions.eq("formulaProductBaseId", formulaProductBaseId));
        }
        List<FormulaProductBase> result = (List<FormulaProductBase>) criteria.list();
        if(result != null && result.size() > 0){
            return result.get(0);
        } else {
            return null;
        }
    }

    @Override
    public FormulaProductBase findByFormulaIdAndProductBase(Long formulaId, Long productBaseId) {
        Criteria criteria = createEntityCriteria();
        if(formulaId != null) {
            criteria.add(Restrictions.eq("formula.formulaId", formulaId));
        }

        if(formulaId != null) {
            criteria.add(Restrictions.eq("productBase.productBaseId", productBaseId));
        }
        return (FormulaProductBase) criteria.uniqueResult();
    }
}
