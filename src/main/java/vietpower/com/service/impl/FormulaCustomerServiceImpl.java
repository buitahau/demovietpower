package vietpower.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.FormulaColourantDao;
import vietpower.com.dao.FormulaCustomerDao;
import vietpower.com.model.FormulaColourant;
import vietpower.com.model.FormulaCustomer;
import vietpower.com.service.FormulaColorantService;
import vietpower.com.service.FormulaCustomerService;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Service("FormulaCustomerService")
@Transactional
public class FormulaCustomerServiceImpl implements FormulaCustomerService {

    @Autowired
    FormulaCustomerDao formulaCustomerDao;

    @Override
    public List<FormulaCustomer> findByFormulaId(Long formulaId) {
        return formulaCustomerDao.findByFormulaId(formulaId);
    }
}
