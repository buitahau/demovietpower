package vietpower.com.service;

import vietpower.com.model.*;

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

    Formula findById(Long formulaId);

    Formula saveOrUpdate(Formula formula, List<FormulaColourant> listFormulaColourant);

    void clearAllData();
}
