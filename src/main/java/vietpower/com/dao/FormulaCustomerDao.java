package vietpower.com.dao;

import vietpower.com.model.FormulaColourant;
import vietpower.com.model.FormulaCustomer;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface FormulaCustomerDao {
    List<FormulaCustomer> findByFormulaId(Long formulaId);

    void delete(FormulaCustomer formulaCustomer);

    void persist(FormulaCustomer formulaCustomer);

    List<FormulaCustomer> findByMachine(Long machineId);

    List<FormulaCustomer> findAll();

    void deleteAll();

}
