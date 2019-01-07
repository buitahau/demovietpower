package vietpower.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.*;
import vietpower.com.model.*;
import vietpower.com.service.SavingFormulaProductBaseService;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Service("savingFormulaProductBaseService")
@Transactional
public class SavingFormulaProductBaseServiceImpl implements SavingFormulaProductBaseService {
    @Autowired
    FormulaProductBaseDao formulaProductBaseDao;

    @Autowired
    FormulaDao formulaDao;

    @Autowired
    FormulaColourantDao formulaColourantDao;

    @Override
    public FormulaProductBase saveOrUpdateFormulaProductBase(SavingFormulaProductBase savingFormulaProductBase){
        FormulaProductBase savingItem = saveOrUpdateFormulaProductBase(savingFormulaProductBase.getFormulaProductBase(), savingFormulaProductBase.getMachine());
        saveOrUpdateListColourant(savingFormulaProductBase.getFormulaColourantList(), savingItem.getFormula());
        return savingItem;
    }

    private void saveOrUpdateListColourant(List<FormulaColourant> newFormulaColourants, Formula formula){
        List<FormulaColourant> formulaColourants = this.formulaColourantDao.findByFormulaId(formula.getFormulaId());

        // clear old list
        if(formulaColourants != null && formulaColourants.size() > 0){
            for(FormulaColourant formulaColourant : formulaColourants){
                this.formulaColourantDao.delete(formulaColourant);
            }
        }

        // save new list (ask Hau Bui revise the function by add delete list
        if(newFormulaColourants != null && newFormulaColourants.size() > 0){
            for(FormulaColourant formulaColourant : newFormulaColourants){
                if(formulaColourant.getQuantity() > 0){
                    FormulaColourant dbItem = new FormulaColourant();
                    dbItem.setColourant(formulaColourant.getColourant());
                    dbItem.setFormula(formula);
                    dbItem.setQuantity(formulaColourant.getQuantity());

                    this.formulaColourantDao.persist(dbItem);
                }
            }
        }
    }

    private FormulaProductBase saveOrUpdateFormulaProductBase(FormulaProductBase formulaProductBase, Machine machine) {
        Formula formula = formulaProductBase.getFormula();
        ProductBase productBase = formulaProductBase.getProductBase();

        Formula newFormula = null;
//        ProductBase newProductBase = null;

        if(formulaProductBase.getFormulaProductBaseId() != null && formulaProductBase.getFormulaProductBaseId() > 0){
            FormulaProductBase dbItem = this.formulaProductBaseDao.findById(formulaProductBase.getFormulaProductBaseId());
            newFormula = updateFormula(formula, dbItem.getFormula(), machine);
//            if(productBase.getProductBaseId() != null && productBase.getProductBaseId() > 0){
//                newProductBase = productBase;
//            } else {
//                newProductBase = saveProductBase(productBase);
//            }
            dbItem.setFormula(newFormula);
            dbItem.setProductBase(formulaProductBase.getProductBase());
            formulaProductBaseDao.update(dbItem);
            return dbItem;
        } else {
            newFormula = saveFormula(formula, machine);
            formulaProductBase.setFormula(newFormula);
            formulaProductBase.setProductBase(productBase);
            this.formulaProductBaseDao.persist(formulaProductBase);
            return formulaProductBase;
        }
    }

    private Formula saveFormula(Formula formula, Machine machine){
        formula.setMachine(machine);
        formula.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        this.formulaDao.persist(formula);
        return formula;
    }

    private Formula updateFormula(Formula formula, Formula dbItem, Machine machine){
        dbItem.setFormulaCode(formula.getFormulaCode());
        dbItem.setFormulaName(formula.getFormulaName());
        dbItem.setCollection(formula.getCollection());
        dbItem.setBaseOnCan(formula.getBaseOnCan());
        dbItem.setMachine(machine);
        this.formulaDao.update(dbItem);
        return dbItem;
    }
}
