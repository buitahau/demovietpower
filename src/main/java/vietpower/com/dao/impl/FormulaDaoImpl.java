package vietpower.com.dao.impl;

import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.FormulaDao;
import vietpower.com.model.Formula;

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
}
