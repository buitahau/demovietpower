package vietpower.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.FormulaColourantDao;
import vietpower.com.model.FormulaColourant;
import vietpower.com.service.FormulaColorantService;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Service("formulaColorantService")
@Transactional
public class FormulaColourantServiceImpl implements FormulaColorantService {

    @Autowired
    FormulaColourantDao formulaColourantDao;

    @Override
    public List<FormulaColourant> findByFormulaId(Long formulaId) {
        return formulaColourantDao.findByFormulaId(formulaId);
    }
}
