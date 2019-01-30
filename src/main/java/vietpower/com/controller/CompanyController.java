package vietpower.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import vietpower.com.model.Company;
import vietpower.com.service.CompanyService;

import java.util.List;

/**
 * Created by HauKute on 1/4/2019.
 */
@Controller
@RequestMapping("/admin")
public class CompanyController {
    @Autowired
    CompanyService companyService;


    @RequestMapping(value = "/company/list")
    public String listCollection(Company company, ModelMap modelMap){
        List<Company> res = companyService.findAll();
        modelMap.addAttribute("listCompany", res);
        modelMap.addAttribute("company", company);
        return "company/list";
    }

    @RequestMapping(value = {"/company/add", "/company/edit"})
    public String addCollection(Company company, ModelMap modelMap){
        if(company.getCompanyId() != null && company.getCompanyId() > 0){
            company = this.companyService.findById(company.getCompanyId());
        }
        modelMap.addAttribute("company", company);
        return "company/add";
    }

    @RequestMapping(value = "/company/insert-or-update")
    public String insertOrUpdate(Company company, ModelMap modelMap){
        modelMap.addAttribute("company", company);
        company = companyService.saveOrUpdate(company);
        return "redirect:/admin/company/list";
    }
}
