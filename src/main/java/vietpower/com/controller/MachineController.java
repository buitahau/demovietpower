package vietpower.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vietpower.com.service.MachineService;

/**
 * Created by HauKute on 12/20/2018.
 */
@Controller
public class MachineController {

    @Autowired
    MachineService machineService;

    @RequestMapping(value = "/machine/list")
    public String listMachine(ModelMap modelMap){
        modelMap.addAttribute("listMachines", machineService.findAllMachine());
        return "machine/list";
    }

    @RequestMapping(value = "/machine/{machineId}/colour")
    public String machineColour(@PathVariable Long machineId,  ModelMap modelMap){
        return "machine/detail_colour";
    }

    @RequestMapping(value = "/machine/{machineId}/formula")
    public String machineFormula(@PathVariable Long machineId,  ModelMap modelMap){
        return "machine/detail_formula";
    }
}
