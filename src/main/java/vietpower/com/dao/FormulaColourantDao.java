package vietpower.com.dao;

import vietpower.com.model.FormulaColourant;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface FormulaColourantDao {
    List<FormulaColourant> findByFormulaId(Long formulaId);

    void delete(FormulaColourant formulaColourant);

    void persist(FormulaColourant formulaColourant);
}
