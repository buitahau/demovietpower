package vietpower.com.service;

import vietpower.com.model.FormulaColourant;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface FormulaColorantService {

    List<FormulaColourant> findByFormulaId(Long formulaId);
}
