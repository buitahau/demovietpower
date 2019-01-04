package vietpower.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import vietpower.com.model.Collection;
import vietpower.com.service.CollectionService;
import vietpower.com.service.MachineService;

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

    @RequestMapping(value = "/collection/list")
    public String listCollection(Collection collection, ModelMap modelMap){
        List<Collection> res = collectionService.find(collection);
        modelMap.addAttribute("listCollections", res);
        modelMap.addAttribute("listMachines", machineService.findAllMachine());
        return "collection/list";
    }
}
