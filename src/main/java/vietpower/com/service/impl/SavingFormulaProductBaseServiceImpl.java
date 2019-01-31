package vietpower.com.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.*;
import vietpower.com.model.*;
import vietpower.com.service.SavingFormulaProductBaseService;

import java.sql.Timestamp;
import java.util.Date;
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
    CollectionDao collectionDao;

    @Autowired
    FormulaDao formulaDao;

    @Autowired
    FormulaColourantDao formulaColourantDao;

    @Autowired
    FormulaCustomerDao formulaCustomerDao;

    @Override
    public FormulaProductBase saveOrUpdateFormulaProductBase(SavingFormulaProductBase savingFormulaProductBase){
        FormulaProductBase savingItem = saveOrUpdateFormulaProductBase(savingFormulaProductBase.getFormulaProductBase(), savingFormulaProductBase.getMachine());
        saveOrUpdateListColourant(savingFormulaProductBase.getFormulaColourantList(), savingItem.getFormula());
        saveOrUpdateListCustomer(savingItem, savingFormulaProductBase.getListCustomer());
        return savingItem;
    }

    private void saveOrUpdateListCustomer(FormulaProductBase savingItem, List<Long> listCustomer) {
        List<FormulaCustomer> formulaCustomerList = this.formulaCustomerDao.findByFormulaId(savingItem.getFormula().getFormulaId());
        if(formulaCustomerList != null && formulaCustomerList.size() > 0){
            for(FormulaCustomer formulaCustomer : formulaCustomerList){
                this.formulaCustomerDao.delete(formulaCustomer);
            }
        }

        for(Long customerId : listCustomer){
            Customer customer = new Customer();
            customer.setCustomerId(customerId);
            FormulaCustomer formulaCustomer = new FormulaCustomer();
            formulaCustomer.setFormula(savingItem.getFormula());
            formulaCustomer.setCustomer(customer);

            this.formulaCustomerDao.persist(formulaCustomer);
        }
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

        if(formula.getFormulaCode().equalsIgnoreCase("TEMPLATE FORMULA")){
            Collection collection = formula.getCollection();

            if (collection == null || collection.getCollectionId() == null) {

                Collection dbItemCollection = this.collectionDao.findByCodeAndMachine("TEMPLATE COLLECTION", machine.getMachineId());
                if(dbItemCollection != null && dbItemCollection.getCollectionId() != null && dbItemCollection.getCollectionId() > 0){
                    collection = dbItemCollection;
                } else {
                    collection = new Collection();
                    collection.setCollectionName("TEMPLATE COLLECTION");
                    collection.setDescription("TEMPLATE COLLECTION");
                    collection.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                    collection.setMachine(machine);
                    this.collectionDao.persist(collection);
                }
            }

            newFormula = this.formulaDao.findByCode(formula.getFormulaCode(), machine.getMachineId());

            // update for template
            if(newFormula != null && newFormula.getFormulaId() > 0){
                newFormula.setFormulaName(StringUtils.isNotBlank(formula.getFormulaName()) ? formula.getFormulaName() : "TEMPLATE FORMULA");
                newFormula.setCollection(collection);
                newFormula.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                newFormula.setBaseOnCan(formula.getBaseOnCan());
                newFormula.setApproximateColor(formula.getApproximateColor());
                newFormula.setSubstrate(formula.getSubstrate());
                newFormula.setComment(formula.getComment());

                this.formulaDao.update(newFormula);

                FormulaProductBase dbItem = this.formulaProductBaseDao.findByFormulaIdAndProductBase(newFormula.getFormulaId(), productBase.getProductBaseId());
                if(dbItem != null && dbItem.getFormulaProductBaseId() > 0){
                    dbItem.setFormula(newFormula);
                    dbItem.setProductBase(formulaProductBase.getProductBase());
                    formulaProductBaseDao.update(dbItem);
                    return dbItem;
                } else {
                    // save formula product base
                    formulaProductBase.setFormula(newFormula);
                    formulaProductBase.setProductBase(productBase);
                    this.formulaProductBaseDao.persist(formulaProductBase);
                    return formulaProductBase;
                }
            } else {
                newFormula = formula;
                if(StringUtils.isBlank(newFormula.getFormulaName())){
                    newFormula.setFormulaName("TEMPLATE FORMULA");
                }
                newFormula.setCollection(collection);

                // save formula
                newFormula.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                newFormula.setMachine(machine);
                this.formulaDao.persist(newFormula);

                // save formula product base
                formulaProductBase.setFormula(newFormula);
                formulaProductBase.setProductBase(productBase);
                this.formulaProductBaseDao.persist(formulaProductBase);
                return formulaProductBase;
            }

        } else {
            if(formulaProductBase.getFormulaProductBaseId() != null && formulaProductBase.getFormulaProductBaseId() > 0){
                FormulaProductBase dbItem = this.formulaProductBaseDao.findById(formulaProductBase.getFormulaProductBaseId());
                newFormula = updateFormula(formula, dbItem.getFormula(), machine);

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
        dbItem.setComment(formula.getComment());
        dbItem.setSubstrate(formula.getSubstrate());
        dbItem.setApproximateColor(formula.getApproximateColor());
        this.formulaDao.update(dbItem);
        return dbItem;
    }
}
