package vietpower.com.dao;

import vietpower.com.model.FormulaProductBase;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface FormulaProductBaseDao {
    List<FormulaProductBase> findByFormulaId(Long formulaId);

    void persist(FormulaProductBase fpb);

    void delete(FormulaProductBase formulaProductBase);
}
