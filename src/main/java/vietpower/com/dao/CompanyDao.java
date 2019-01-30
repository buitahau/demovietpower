package vietpower.com.dao;

import vietpower.com.model.Base;
import vietpower.com.model.Company;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface CompanyDao {
    List<Company> findAll();

    List<Company> findByProperties(Company company);

    Company findById(Long companyId);

    void persist(Company company);

    void update(Company company);
}
