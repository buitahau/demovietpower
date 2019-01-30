package vietpower.com.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.BaseDao;
import vietpower.com.dao.CompanyDao;
import vietpower.com.model.Base;
import vietpower.com.model.Company;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Repository("companyDao")
public class CompanyDaoImpl extends AbstractDao<Integer, Company> implements CompanyDao {

    @Override
    public List<Company> findAll() {
        return (List<Company>) createEntityCriteria().list();
    }

    @Override
    public Company findById(Long companyId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("companyId", companyId));
        Company company = (Company)crit.uniqueResult();
        return company;
    }

    @Override
    public List<Company> findByProperties(Company company) {
        Criteria crit = createEntityCriteria();
        if (company != null){
            if(StringUtils.isNotBlank(company.getCode())){
                crit.add(Restrictions.ilike("code", "%" + company.getCode() + "%"));
            }

            if(StringUtils.isNotBlank(company.getName())){
                crit.add(Restrictions.ilike("name", "%" + company.getName() + "%"));
            }

            if(StringUtils.isNotBlank(company.getPhone())){
                crit.add(Restrictions.ilike("phone", "%" + company.getPhone() + "%"));
            }

            if(StringUtils.isNotBlank(company.getWebsite())){
                crit.add(Restrictions.ilike("website", "%" + company.getWebsite() + "%"));
            }
        }
        return (List<Company>) crit.list();
    }
}
