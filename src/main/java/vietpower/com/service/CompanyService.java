package vietpower.com.service;

import vietpower.com.model.Company;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface CompanyService {
    List<Company> findAll();

    Company findById(Long company);

    List<Company> searchByProperties(Company company);

    Company saveOrUpdate(Company company);
}
