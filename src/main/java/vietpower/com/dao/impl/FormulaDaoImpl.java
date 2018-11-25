package vietpower.com.dao.impl;

import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.FormulaDao;
import vietpower.com.model.Formula;

/**
 * Created by HauKute on 11/25/2018.
 */
@Repository("formulaDao")
public class FormulaDaoImpl extends AbstractDao<Integer, Formula> implements FormulaDao {
}
