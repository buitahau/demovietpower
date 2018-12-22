package vietpower.com.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vietpower.com.model.*;
import vietpower.com.service.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HauKute on 12/3/2018.
 */
@RestController
@CrossOrigin
public class RestAPIController implements Serializable{
    @Autowired
    FormulaService formulaService;

    @Autowired
    BaseService baseService;

    @Autowired
    ColourantService colourantService;

    @Autowired
    CollectionService collectionService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductBaseService productBaseService;

    @Autowired
    FormulaProductBaseService formulaProductBaseService;

    @Autowired
    FormulaColorantService formulaColorantService;

    @Autowired
    UserService userService;


    @Autowired
    MachineColourantService machineColourantService;

    @Autowired
    MachineService machineService;

    @RequestMapping(value = "/server/api/formula/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List getAllFormula(){
        return formulaService.findAll();
    }

    @RequestMapping(value = "/server/api/formula/get/{formulaId}", method = RequestMethod.GET)
    @ResponseBody
    public Object getFormula(@PathVariable String formulaId){
        return formulaId;
    }

    @RequestMapping(value = "/server/api/formula_product_base/getAll", method = RequestMethod.GET)
    public List getAllFormulaProductBase(){
        return formulaService.findFormulaProductBaseByFormulaId(null);
    }

    @RequestMapping(value = "/server/api/formula_product_base/findById/{formulaProductBaseId}", method = RequestMethod.GET)
    public FormulaProductBase getAllFormulaProductBase(@PathVariable Long formulaProductBaseId){
        return formulaProductBaseService.findById(formulaProductBaseId);
    }

    @RequestMapping(value = "/server/api/formula_product_base/findByFormula/{formulaId}", method = RequestMethod.GET)
    public List getFormulaProductBaseByFormulaId(@PathVariable Long formulaId){
        return formulaService.findFormulaProductBaseByFormulaId(formulaId);
    }


    @RequestMapping(value = "/server/api/product_base_can/findById/{productBaseCanId}", method = RequestMethod.GET)
    public List getProductBaseCan(@PathVariable Long productBaseCanId){
        return formulaService.findFormulaProductBaseCanByFormulaId(productBaseCanId);
    }

    @RequestMapping(value = "/server/api/formula/getColourants/{formulaId}", method = RequestMethod.GET)
    public List getFormulaColorant(@PathVariable Long formulaId){
        return formulaColorantService.findByFormulaId(formulaId);
    }

    @RequestMapping(value = "/server/api/base/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List getAllBase(){
        return baseService.findAll();
    }

    @RequestMapping(value = "/server/api/colourant/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List getAllColourant(){
        List<Colourant> colourants = colourantService.findAll();
        return colourants;
    }

    @RequestMapping(value = "/server/api/collection/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List getAllCollection(){
        List<Collection> collections = collectionService.findAll();
        return collections;
    }

    @RequestMapping(value = "/server/api/product/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List getAllProduct(){
        List<Product> listtItems = productService.findAll();
        return listtItems;
    }

    @RequestMapping(value="/server/api/product/view-detail/{productId}", method=RequestMethod.GET)
    @ResponseBody
    public List byParameter(@PathVariable Long productId) {
        List<ProductBase> result = productBaseService.findByProductId(productId);
        return result;
    }

    @RequestMapping(value = "/server/api/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestBody String userInfo){
        System.out.println(userInfo);
        return userInfo;
    }

    @RequestMapping(value = "/server/api/login_test", method = RequestMethod.POST)
    @ResponseBody
    public Object login1(@RequestBody User userInfo){
        User loginUser = userService.findByUserName(userInfo.getUserName());
        if(loginUser.getPassword().equals(userInfo.getPassword())){
            return loginUser;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/server/api/machine/getColourants/{machineId}", method = RequestMethod.GET)
    public List getMachineColourants(@PathVariable Long machineId){
        return machineColourantService.findByMachineId(machineId);
    }

    @RequestMapping(value = "/server/api/machine_colour/subtraction", method = RequestMethod.POST)
    @ResponseBody
    public Object machineColour(@RequestBody MachineColourant machineColourant){
        machineService.subtractColour(machineColourant);
        return machineColourant;
    }

    @RequestMapping(value = "/server/api/machine_colour/update", method = RequestMethod.POST)
    @ResponseBody
    public List updateMachineColourantAmount(@RequestBody MachineColourant machineColourant){
        machineService.updateMachineColourant(machineColourant);
        return machineColourantService.findByMachineId(machineColourant.getMachine().getMachineId());
    }

    @RequestMapping(value = "/server/api/machine_formula/record", method = RequestMethod.POST)
    @ResponseBody
    public Object machineFormula(@RequestBody MachineFormulaProductBase machineFormulaProductBase){
        machineService.saveFormulaProductBase(machineFormulaProductBase);
        return machineFormulaProductBase;
    }
}
