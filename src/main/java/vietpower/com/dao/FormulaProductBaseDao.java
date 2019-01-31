package vietpower.com.dao;

import vietpower.com.model.FormulaProductBase;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface FormulaProductBaseDao {
    List<FormulaProductBase> findByFormulaId(Long formulaId);

    FormulaProductBase findById(Long formulaProductBaseId);

    void persist(FormulaProductBase fpb);

    void delete(FormulaProductBase formulaProductBase);

    void update(FormulaProductBase dbItem);

    void deleteAll();

    FormulaProductBase findByFormulaIdAndProductBase(Long formulaId, Long productBaseId);
}
