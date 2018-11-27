package vietpower.com.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.FormulaColourantDao;
import vietpower.com.model.FormulaColourant;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Repository("formulaColourantDao")
public class FormulaColourantDaoImpl extends AbstractDao<Integer, FormulaColourant> implements FormulaColourantDao{
    @Override
    public List<FormulaColourant> findByFormulaId(Long formulaId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("formula.formulaId", formulaId));
        return (List<FormulaColourant>) criteria.list();
    }
}
