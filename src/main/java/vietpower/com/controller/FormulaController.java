package vietpower.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vietpower.com.model.Collection;
import vietpower.com.model.Formula;
import vietpower.com.service.CollectionService;
import vietpower.com.service.FormulaService;
import vietpower.com.service.MachineService;
import vietpower.com.service.ProductService;
import vietpower.com.utils.SecurityUtils;

import java.util.List;

/**
 * Created by HauKute on 1/4/2019.
 */
@Controller
@RequestMapping("/admin")
public class FormulaController {
    @Autowired
    CollectionService collectionService;

    @Autowired
    ProductService productService;

    @Autowired
    FormulaService formulaService;

    @RequestMapping(value = "/formula/list")
    public String listFormula(Formula formula, ModelMap modelMap){
        List<Formula> res = formulaService.findAll();
        modelMap.addAttribute("listFormula", res);
        modelMap.addAttribute("listProduct", productService.findAll());
        modelMap.addAttribute("listCollection", collectionService.findAll());
        return "formula/list";
    }

    @RequestMapping(value = {"/formula/add", "/formula/edit"})
    public String addCollection(Formula formula, ModelMap modelMap){
        if(formula.getFormulaId() != null && formula.getFormulaId() > 0){
          formula = this.formulaService.findById(formula.getFormulaId());
        }
        modelMap.addAttribute("formula", formula);
        return "formula/add";
    }

    @RequestMapping(value = "/formula/insert-or-update")
    public String insertOrUpdate(Collection collection, ModelMap modelMap){
        System.out.println(collection);
        modelMap.addAttribute("collection", collection);
        collection = collectionService.saveOrUpdate(collection);
        return "redirect:/admin/formula/list";
    }
}
