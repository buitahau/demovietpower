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
        criteria.add(Restrictions.eq("formula.formulaId", formulaId));
        return (List<FormulaProductBase>) criteria.list();
    }
}
