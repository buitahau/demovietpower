package vietpower.com.controller;



import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vietpower.com.model.*;
import vietpower.com.service.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by HauKute on 12/3/2018.
 */
@RestController
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

    @RequestMapping(value = "/server/api/formula/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List getAllFormula(){
        return formulaService.findAll();
    }

    @RequestMapping(value = "/server/api/formula_product_base/getAll", method = RequestMethod.GET)
    public List getAllFormulaProductBase(){
        return formulaService.findFormulaProductBaseByFormulaId(null);
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
}
