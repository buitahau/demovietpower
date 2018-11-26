package vietpower.com.dao.impl;

import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.FormulaProductBaseDao;
import vietpower.com.model.FormulaProductBase;

/**
 * Created by HauKute on 11/25/2018.
 */
@Repository("formulaProductBaseCanDao")
public class FormulaProductBaseDaoImpl extends AbstractDao<Integer, FormulaProductBase> implements FormulaProductBaseDao {
}
