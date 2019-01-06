package vietpower.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.*;
import vietpower.com.model.*;
import vietpower.com.service.FormulaColorantService;
import vietpower.com.service.FormulaProductBaseService;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Service("formulaProductBaseService")
@Transactional
public class FormulaProductBaseServiceImpl implements FormulaProductBaseService {

    @Autowired
    FormulaProductBaseDao formulaProductBaseDao;

    @Override
    public FormulaProductBase findById(Long formulaProductBaseId) {
        return formulaProductBaseDao.findById(formulaProductBaseId);
    }
}
