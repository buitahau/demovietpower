package vietpower.com.service;

import vietpower.com.model.Formula;
import vietpower.com.model.FormulaColourant;
import vietpower.com.model.FormulaProductBase;
import vietpower.com.model.ProductBaseCan;

import java.util.List;
import java.util.Map;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface FormulaService {
    List<Formula> findAll();

    void save(Formula formula);

    List<FormulaProductBase> findFormulaProductBaseByFormulaId(Long formulaId);

    void saveFormulaProductBase(FormulaProductBase fpb);

    void deleteFormulaProductBase(FormulaProductBase formulaProductBase);

    List<FormulaColourant> findFormulaColourantByFormulaId(Long formulaId);

    void deleteFormulaColourant(FormulaColourant formulaColourant);

    void saveFormulaColourant(FormulaColourant formulaColourant);

    List<ProductBaseCan> findFormulaProductBaseCanByFormulaId(Long formulaId);

    List<Formula> findByCollection(Long collectionId);

    void update(Formula formula);
}
