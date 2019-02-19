package vietpower.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.*;
import vietpower.com.model.Formula;
import vietpower.com.model.FormulaColourant;
import vietpower.com.model.FormulaProductBase;
import vietpower.com.model.ProductBaseCan;
import vietpower.com.service.FormulaService;

import java.sql.Timestamp;
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

    @Autowired
    FormulaCustomerDao formulaCustomerDao;

    @Autowired
    MachineFormulaProductBaseDao machineFormulaProductBaseDao;

    @Autowired
    MachineColourantLogDao machineColourantLogDao;

    @Autowired
    MachineColourantDao machineColourantDao;

    @Autowired
    ProductBaseDao productBaseDao;

    @Autowired
    CollectionDao collectionDao;

    @Autowired
    BaseDao baseDao;

    @Autowired
    ProductDao productDao;

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

    @Override
    public List<Formula> findByCollection(Long collectionId) {
        return formulaDao.findByCollection(collectionId);
    }

    @Override
    public void update(Formula formula) {
        formulaDao.update(formula);
    }

    @Override
    public Formula findById(Long formulaId) {
        return formulaDao.findById(formulaId);
    }

    @Override
    public Formula saveOrUpdate(Formula formula, List<FormulaColourant> listFormulaColourant) {
        String mode = formula.getFormulaId() != null && formula.getFormulaId() > 0 ? "update": "save";

        if(mode.equalsIgnoreCase("update")){
            Formula dbItem = this.formulaDao.findById(formula.getFormulaId());
            dbItem.setFormulaCode(formula.getFormulaCode());
            dbItem.setFormulaName(formula.getFormulaName());
            dbItem.setCollection(formula.getCollection());
            dbItem.setBaseOnCan(formula.getBaseOnCan());
            dbItem.setApproximateColor(formula.getApproximateColor());
            dbItem.setSubstrate(formula.getSubstrate());
            dbItem.setComment(formula.getComment());

            this.formulaDao.update(dbItem);
            formula = dbItem;
        } else {
            formula.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            this.formulaDao.persist(formula);
        }

        if(mode.equalsIgnoreCase("update")){
            List<FormulaColourant> formulaColourantListDBItems = this.formulaColourantDao.findByFormulaId(formula.getFormulaId());
            for(FormulaColourant formulaColourant : formulaColourantListDBItems){
                this.formulaColourantDao.delete(formulaColourant);
            }
        }

        for(FormulaColourant formulaColourant : listFormulaColourant){
            if(formulaColourant.getQuantity() != null && formulaColourant.getQuantity() > 0){
                FormulaColourant formulaColourantDBItem = new FormulaColourant();
                formulaColourantDBItem.setFormula(formula);
                formulaColourantDBItem.setColourant(formulaColourant.getColourant());
                formulaColourantDBItem.setQuantity(formulaColourant.getQuantity());
                this.formulaColourantDao.persist(formulaColourantDBItem);
            }
        }

        return formula;
    }

    @Override
    public void clearAllData() {
        // delete FormulaCustomer
        formulaCustomerDao.deleteAll();
        // delete MachineColourantLog
        machineColourantLogDao.deleteAll();
        // delete MachineFormulaProductBase
        machineFormulaProductBaseDao.deleteAll();
        // delete MachineColourant
        machineColourantDao.deleteAll();
        // delete FormulaProductBase
        formulaProductBaseDao.deleteAll();
        // delete FormulaColourant
        formulaColourantDao.deleteAll();
        // delete ProductBaseCan
        productBaseCanDao.deleteAll();
        // delete ProductBase
        productBaseDao.deleteAll();
        // delete Base
        baseDao.deleteAll();
        // delete Product
        productDao.deleteAll();
        // delete Formula
        formulaDao.deleteAll();
        // delete Collection
        collectionDao.deleteAll();
    }
}
