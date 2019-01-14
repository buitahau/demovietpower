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
import vietpower.com.utils.SecurityUtils;

import java.util.List;

/**
 * Created by HauKute on 1/4/2019.
 */
@Controller
@RequestMapping("/admin")
public class CollectionController {
    @Autowired
    CollectionService collectionService;
    @Autowired
    MachineService machineService;
    @Autowired
    FormulaService formulaService;

    @RequestMapping(value = "/collection/list")
    public String listCollection(Collection collection, ModelMap modelMap){
        Long userId = SecurityUtils.getPrincipal().getUserId();
        List<Collection> res = collectionService.find(collection);
        modelMap.addAttribute("listCollections", res);
        modelMap.addAttribute("listMachines", machineService.findAllMachine());
        return "collection/list";
    }

    @RequestMapping(value = "/collection/{collectionId}/formula")
    public String detailFormula(@PathVariable Long collectionId, ModelMap modelMap){
        Collection collection = collectionService.findById(collectionId);
        if(collection == null){
            return "redirect:/admin/collection/list";
        }
        List<Formula> listFormulas = formulaService.findByCollection(collectionId);

        modelMap.addAttribute("listFormulas", listFormulas);
        modelMap.addAttribute("collection", collection);
        return "collection/collection_formula";
    }
}
