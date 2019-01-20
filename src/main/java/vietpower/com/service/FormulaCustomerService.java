package vietpower.com.service;

import vietpower.com.model.FormulaColourant;
import vietpower.com.model.FormulaCustomer;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface FormulaCustomerService {

    List<FormulaCustomer> findByFormulaId(Long formulaId);

    List<FormulaCustomer> findByMachine(Long machineId);
}
