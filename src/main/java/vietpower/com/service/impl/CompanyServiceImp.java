package vietpower.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.CollectionDao;
import vietpower.com.dao.CompanyDao;
import vietpower.com.dao.MachineDao;
import vietpower.com.model.Collection;
import vietpower.com.model.Company;
import vietpower.com.model.Machine;
import vietpower.com.service.CollectionService;
import vietpower.com.service.CompanyService;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Service("companyService")
@Transactional
public class CompanyServiceImp implements CompanyService {
    @Autowired
    private CompanyDao companyDao;

    @Override
    public List<Company> findAll() {
        return companyDao.findAll();
    }

    @Override
    public Company findById(Long companyId) {
        return companyDao.findById(companyId);
    }

    @Override
    public List<Company> searchByProperties(Company company) {
        return companyDao.findByProperties(company);
    }

    @Override
    public Company saveOrUpdate(Company company) {
        if(company.getCompanyId() != null && company.getCompanyId() > 0){
            companyDao.update(company);
        } else {
            companyDao.persist(company);
        }
        return company;
    }
}
