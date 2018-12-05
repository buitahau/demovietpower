package vietpower.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import vietpower.com.model.Base;
import vietpower.com.model.Formula;
import vietpower.com.service.BaseService;
import vietpower.com.service.FormulaService;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HauKute on 12/3/2018.
 */
@RestController
public class RestAPIController implements Serializable{
    @Autowired
    FormulaService formulaService;
    @Autowired
    BaseService baseService;

    @RequestMapping(value = "/server/api/formula/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List getAllFormula(){
        List<Formula> formulars = formulaService.findAll();
        return formulars;
    }

    @RequestMapping(value = "/server/api/base/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List getAllBase(){
        List<Base> bases = baseService.findAll();
        return bases;
    }
}
