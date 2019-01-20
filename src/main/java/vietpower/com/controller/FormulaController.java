package vietpower.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import vietpower.com.model.Collection;
import vietpower.com.model.Colourant;
import vietpower.com.model.Formula;
import vietpower.com.model.FormulaColourant;
import vietpower.com.service.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    ProductBaseService productBaseService;

    @Autowired
    ColourantService colourantService;

    @Autowired
    FormulaColorantService formulaColorantService;

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
        modelMap.addAttribute("listProductBase", productBaseService.findAll());
        modelMap.addAttribute("listCollection", collectionService.findAll());

        List<FormulaColourant> listFormulaColourant = formulaColorantService.findByFormulaId(formula.getFormulaId());
        Map<Long, Double> mapColourantQuantity = new HashMap<>();
        for(FormulaColourant formulaColourant : listFormulaColourant){
            mapColourantQuantity.put(formulaColourant.getColourant().getColourantId(), formulaColourant.getQuantity());
        }

        List<Colourant> colourants = colourantService.findAll();
        for(Colourant colourant : colourants){
            if(mapColourantQuantity.get(colourant.getColourantId()) == null){
                FormulaColourant formulaColourant = new FormulaColourant();
                formulaColourant.setColourant(colourant);
                formulaColourant.setFormula(formula);
                formulaColourant.setQuantity(null);

                listFormulaColourant.add(formulaColourant);
            }
        }
        modelMap.addAttribute("listFormulaColourant", listFormulaColourant);

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
