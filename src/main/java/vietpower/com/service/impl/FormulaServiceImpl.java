package vietpower.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.FormulaColourantDao;
import vietpower.com.dao.FormulaDao;
import vietpower.com.dao.FormulaProductBaseDao;
import vietpower.com.dao.ProductBaseCanDao;
import vietpower.com.model.Formula;
import vietpower.com.model.FormulaColourant;
import vietpower.com.model.FormulaProductBase;
import vietpower.com.model.ProductBaseCan;
import vietpower.com.service.FormulaService;

import java.util.List;
import java.util.Map;

/**
 * Created by HauKute on 11/25/2018.
 */
@Service("formulaService")
@Transactional
public class FormulaServiceImpl implements FormulaService {
    @Autowired
    FormulaDao formulaDao;

    @Autowired
    FormulaProductBaseDao formulaProductBaseDao;

    @Autowired
    FormulaColourantDao formulaColourantDao;

    @Autowired
    ProductBaseCanDao productBaseCanDao;

    @Override
    public List<Formula> findAll() {
        return formulaDao.findAll();
    }

    @Override
    public void save(Formula formula) {
        formulaDao.persist(formula);
    }

    @Override
    public List<FormulaProductBase> findFormulaProductBaseByFormulaId(Long formulaId) {
        return formulaProductBaseDao.findByFormulaId(formulaId);
    }

    @Override
    public void saveFormulaProductBase(FormulaProductBase fpb) {
        formulaProductBaseDao.persist(fpb);
    }

    @Override
    public void deleteFormulaProductBase(FormulaProductBase formulaProductBase) {
        formulaProductBaseDao.delete(formulaProductBase);
    }

    @Override
    public List<FormulaColourant> findFormulaColourantByFormulaId(Long formulaId) {
        return formulaColourantDao.findByFormulaId(formulaId);
    }

    @Override
    public void deleteFormulaColourant(FormulaColourant formulaColourant) {
        formulaColourantDao.delete(formulaColourant);
    }

    @Override
    public void saveFormulaColourant(FormulaColourant formulaColourant) {
        formulaColourantDao.persist(formulaColourant);
    }

    @Override
    public List<ProductBaseCan> findFormulaProductBaseCanByFormulaId(Long productBaseId) {
        return productBaseCanDao.findByProductBase(productBaseId);
    }
}
