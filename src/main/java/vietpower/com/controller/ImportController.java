package vietpower.com.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vietpower.com.model.*;
import vietpower.com.model.Collection;
import vietpower.com.service.*;
import vietpower.com.utils.SecurityUtils;

import java.io.*;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.*;
import java.awt.Color;
/**
 * Created by HauKute on 11/25/2018.
 */
@Controller
@RequestMapping("/admin")
public class ImportController implements Serializable {
    private static final String FILE_EXCEL = "DB_Specifications.xlsx";

    private static String UPLOADED_FOLDER = "F://temp//";
    @Autowired
    ColourantService colourantService;
    @Autowired
    BaseService baseService;
    @Autowired
    ProductService productService;
    @Autowired
    CollectionService collectionService;
    @Autowired
    FormulaService formulaService;

    @RequestMapping("/import/upload")
    public String uploadForm(ModelMap modelMap){
//        clearData();
        Boolean isSuperAdmin = SecurityUtils.isUserHasRole("ROLE_SUPER-ADMIN");
        modelMap.addAttribute("isSuperAdmin", isSuperAdmin);
        return "import/import";
    }

    @RequestMapping("/clearData")
    public void clearData(){
        formulaService.clearAllData();
    }

    @RequestMapping(value="/import/savefile", method=RequestMethod.POST )
    public String singleSave(@RequestParam("file") MultipartFile file, ModelMap modelMap, RedirectAttributes redirectAttributes){
        if (!file.isEmpty()) {
            try {
                File convFile = new File( file.getOriginalFilename());
                file.transferTo(convFile);
                return validateFileExcel(convFile, modelMap, redirectAttributes);

            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", true);

                return "redirect:/admin/import/upload";
            }
        } else {
            return "redirect:/admin/import/upload";
        }
    }

    private void parseFileExcel(){
        try {
            parseFileExcel(new File(this.getClass().getClassLoader().getResource(FILE_EXCEL).getPath()));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseFileExcel(File file) throws IOException {
        FileInputStream excelFile = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(excelFile);
        Map<String, Colourant> mapColourants = new HashMap<>();
        Map<String, Base> mapBases = new HashMap<>();
        Map<String, Product> mapProducts = new HashMap<>();
        Map<String, ProductBase> mapProductBase = new HashMap<>();
        Map<String, ProductBaseCan> mapProductBaseCan = new HashMap<>();
        parseSheet_1(mapColourants, workbook.getSheetAt(0));
        parseSheet_2(mapBases, mapProducts, mapProductBase, workbook.getSheetAt(1));
        parseSheet_3(mapProductBase, mapProductBaseCan, workbook.getSheetAt(2));
        parseSheet_4(mapColourants, mapProductBase, workbook.getSheetAt(3));
    }

    private String validateFileExcel(File file, ModelMap modelMap, RedirectAttributes redirectAttributes) throws IOException {
        FileInputStream excelFile = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(excelFile);

        Map<String, Colourant> mapColourants = new HashMap<>();
        Map<String, Base> mapBases = new HashMap<>();
        Map<String, Product> mapProducts = new HashMap<>();
        Map<String, ProductBase> mapProductBase = new HashMap<>();
        Map<String, ProductBaseCan> mapProductBaseCan = new HashMap<>();

        Map<String, Collection> mapCollection = new HashMap<>();
        Map<String, Formula> mapFormula = new HashMap<>();
        Map<String, FormulaProductBase> mapFormulaProductBase = new HashMap<>();


        List<ImportColourant> colorantErrors = validateSheet1(mapColourants, workbook.getSheetAt(0));
        List<ImportProductBase> productBaseErrors = validateSheet2(mapBases, mapProducts, mapProductBase, workbook.getSheetAt(1));
        List<ImportProductBaseCan> productBaseCanErrors = validateSheet3(mapBases, mapProducts, mapProductBase, mapProductBaseCan, workbook.getSheetAt(2));
        List<ImportFormula> formulaErrors = validateSheet4(
                mapColourants,
                mapCollection, mapFormula, mapFormulaProductBase,
                mapBases, mapProducts, mapProductBase, mapProductBaseCan,
                workbook.getSheetAt(3));

        if(colorantErrors.isEmpty() && productBaseErrors.isEmpty() && productBaseCanErrors.isEmpty() && formulaErrors.isEmpty()){
            formulaService.clearAllData();
            parseFileExcel(file);
            redirectAttributes.addFlashAttribute("success", true);
            return "redirect:/admin/import/upload";

        } else {
            modelMap.addAttribute("hasErrors", true);

            if(! colorantErrors.isEmpty()){
                modelMap.addAttribute("colorantErrors", colorantErrors);
            }

            if(! productBaseErrors.isEmpty()){
                modelMap.addAttribute("productBaseErrors", productBaseErrors);
            }

            if(! productBaseCanErrors.isEmpty()){
                modelMap.addAttribute("productBaseCanErrors", productBaseCanErrors);
            }

            if(! formulaErrors.isEmpty()){
                modelMap.addAttribute("formulaErrors", formulaErrors);
            }

            return "import/import";
        }
    }

    private List<ImportFormula> validateSheet4(
            Map<String, Colourant> mapColourants,
            Map<String, Collection> mapCollection, Map<String, Formula> mapFormula, Map<String, FormulaProductBase> mapFormulaProductBase,
            Map<String, Base> mapBases, Map<String, Product> mapProducts, Map<String, ProductBase> mapProductBase, Map<String, ProductBaseCan> mapProductBaseCan,
            Sheet datatypeSheet) {

        List<ImportFormula> listErrors = new ArrayList<>();

        Iterator<Row> iterator = datatypeSheet.iterator();
        int row = 0;
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            if (row > 0) {
                try{
                    String collectionName = currentRow.getCell(0).getStringCellValue();
                    String [] listProductCode = currentRow.getCell(1).getStringCellValue().split(",");
                    String formulaName = currentRow.getCell(2).getCellType() == CellType.NUMERIC
                            ? String.valueOf(currentRow.getCell(2).getNumericCellValue()).replace(".0", "")
                            : currentRow.getCell(2).getStringCellValue();
                    Double baseOnCan = currentRow.getCell(3).getNumericCellValue();
                    String baseCode = currentRow.getCell(5).getStringCellValue();

                    List<FormulaColourant> colourantFormulaList = new ArrayList<>();

                    if(currentRow.getCell(6) != null && currentRow.getCell(7) != null) {
                        FormulaColourant formulaColourant01 = getColourantFormula(currentRow.getCell(6).getStringCellValue(), currentRow.getCell(7).getNumericCellValue());
                        if(formulaColourant01 != null){
                            colourantFormulaList.add(formulaColourant01);
                        }
                    }

                    if(currentRow.getCell(8) != null && currentRow.getCell(9) != null) {
                        FormulaColourant formulaColourant02 = getColourantFormula(currentRow.getCell(8).getStringCellValue(), currentRow.getCell(9).getNumericCellValue());
                        if(formulaColourant02 != null){
                            colourantFormulaList.add(formulaColourant02);
                        }
                    }

                    if(currentRow.getCell(10) != null && currentRow.getCell(11) != null) {
                        FormulaColourant formulaColourant03 = getColourantFormula(currentRow.getCell(10).getStringCellValue(), currentRow.getCell(11).getNumericCellValue());
                        if(formulaColourant03 != null){
                            colourantFormulaList.add(formulaColourant03);
                        }
                    }

                    if(currentRow.getCell(12) != null && currentRow.getCell(13) != null) {
                        FormulaColourant formulaColourant04 = getColourantFormula(currentRow.getCell(12).getStringCellValue(), currentRow.getCell(13).getNumericCellValue());
                        if(formulaColourant04 != null){
                            colourantFormulaList.add(formulaColourant04);
                        }
                    }

                    if(currentRow.getCell(14) != null && currentRow.getCell(15) != null) {
                        FormulaColourant formulaColourant05 = getColourantFormula(currentRow.getCell(14).getStringCellValue(), currentRow.getCell(15).getNumericCellValue());
                        if(formulaColourant05 != null){
                            colourantFormulaList.add(formulaColourant05);
                        }
                    }

                    if(currentRow.getCell(16) != null && currentRow.getCell(17) != null) {
                        FormulaColourant formulaColourant06 = getColourantFormula(currentRow.getCell(16).getStringCellValue(), currentRow.getCell(17).getNumericCellValue());
                        if(formulaColourant06 != null){
                            colourantFormulaList.add(formulaColourant06);
                        }
                    }

                    Date createDate = currentRow.getCell(18).getDateCellValue();

                    String approximateColor = convertDecimalToColor((int) currentRow.getCell(20).getNumericCellValue());
                    String comment = currentRow.getCell(21).getStringCellValue();
                    String substrate = currentRow.getCell(22).getStringCellValue();


                    // add collection
                    Collection collection = mapCollection.get(collectionName);
                    if(collection == null){
                        collection = new Collection();
                        collection.setCollectionName(collectionName);
                        collection.setDescription(collectionName);
                        collection.setMachine(null);
                        collection.setCreatedDate(new Timestamp(System.currentTimeMillis()));

                        mapCollection.put(collectionName, collection);
                    }

                    Formula formula = new Formula();
                    formula.setCollection(collection);
                    formula.setFormulaCode(formulaName);
                    formula.setFormulaName(formulaName);

                    formula.setBaseOnCan(baseOnCan);
                    formula.setApproximateColor(approximateColor);
                    formula.setComment(comment);
                    formula.setSubstrate(substrate);

                    if(createDate != null ){
                        formula.setCreatedDate(new Timestamp(createDate.getTime()));
                    } else {
                        formula.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                    }

                    List<String> errorsMessage = new ArrayList<>();

                    if(mapFormula.get(formulaName) != null){
                        errorsMessage.add("Formula " + formulaName + " have been exits !");
                    } else {
                        mapFormula.put(formulaName, formula);
                    }

                    for(String productCode : listProductCode){
                        String productBasKey  = buildProductBase(baseCode, productCode);
                        ProductBase productBase = mapProductBase.get((productBasKey));

                        if(productBase == null){
                            errorsMessage.add("Formula " + formulaName + " have been exits !");
                        } else {
                            FormulaProductBase formulaProductBase = new FormulaProductBase();
                            formulaProductBase.setFormula(formula);
                            formulaProductBase.setProductBase(productBase);

                            String formulaProductBaseKey = buildFormulaProductBase(formulaName, productBase.getProduct().getProductCode(), productBase.getBase().getBaseCode());
                            if(mapFormulaProductBase.get(formulaProductBaseKey) != null){
                                errorsMessage.add("Formula Product Base " + formulaName + " " + productBasKey + " " +" have been exits !");
                            } else {
                                mapFormulaProductBase.put(formulaProductBaseKey, formulaProductBase);
                            }
                        }
                    }

                    for(FormulaColourant formulaColourant : colourantFormulaList){
                        Colourant colourant = mapColourants.get(formulaColourant.getColourant().getColourantCode());

                        if(colourant == null){
                            errorsMessage.add("Colourant " + formulaColourant.getColourant().getColourantCode() + " in formula " + formulaName + " is not exits!");
                        } else {
                            formulaColourant.setColourant(colourant);
                        }
                    }

                    if(errorsMessage != null && errorsMessage.size() > 0){
                        ImportFormula error = new ImportFormula();
                        error.setRow(row);
                        error.setColumn(1);
                        error.setErrors(StringUtils.join(errorsMessage, " <br /> "));
                        listErrors.add(error);
                    }
                } catch(Exception e){
                    ImportFormula error = new ImportFormula();
                    error.setRow(row);
                    error.setColumn(1);
                    error.setErrors("Cannot parse data of the row. The format isn't correctly. <span style='display: none'>" + e.getMessage() + "</span>");
                    listErrors.add(error);
                }
            }
            row ++;
        }

        return listErrors;
    }

    private FormulaColourant getColourantFormula(String colourantCode, Double quantity){
        if(StringUtils.isNotBlank(colourantCode) && quantity != null && quantity > 0){
            FormulaColourant formulaColourant = new FormulaColourant();

            Colourant colourant = new Colourant();
            colourant.setColourantCode(colourantCode);
            colourant.setColourantName(colourantCode);

            formulaColourant.setColourant(colourant);
            formulaColourant.setQuantity(quantity);

            return formulaColourant;
        } else {
            return null;
        }
    }

    private List<ImportProductBaseCan> validateSheet3(
            Map<String, Base> mapBases,
            Map<String, Product> mapProducts,
            Map<String, ProductBase> mapProductBase,
            Map<String, ProductBaseCan> mapProductBaseCan,
            Sheet datatypeSheet) {

        List<ImportProductBaseCan> listErrors = new ArrayList<>();

        Iterator<Row> iterator = datatypeSheet.iterator();
        int row = 0;
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            if (row > 0) {
                try{
                    String baseCode = currentRow.getCell(0).getStringCellValue();
                    String productCode = currentRow.getCell(1).getStringCellValue();
                    Double can = Double.valueOf(currentRow.getCell(2).getStringCellValue().toLowerCase().replace("lt", "").trim());
                    Double pricePerCan = currentRow.getCell(4).getNumericCellValue();
                    Integer percentage = Double.valueOf(currentRow.getCell(5).getNumericCellValue()).intValue();
                    String barCode = Double.valueOf(currentRow.getCell(6).getNumericCellValue()).toString();

                    String productBaseKey = buildProductBase(baseCode, productCode);

                    ProductBaseCan productBaseCan = new ProductBaseCan();
                    productBaseCan.setProductBase(mapProductBase.get(productBaseKey));
                    productBaseCan.setCan(can);
                    productBaseCan.setPricePerCan(pricePerCan);
                    productBaseCan.setPercentage(percentage);
                    productBaseCan.setBarCode(barCode);

                    String errorsMessage = null;

                    if(mapBases.get(baseCode) == null){
                        errorsMessage = "The base " + baseCode + " isn't exits in sheet 2!";
                    }

                    if(mapProducts.get(productCode) == null){
                        errorsMessage = "The product " + productCode + " isn't exits in sheet 2!";
                    }

                    if(mapProductBase.get(productBaseKey) == null){
                        errorsMessage = "The product base (" + baseCode + " " + productCode + ") isn't exits in sheet 2!";
                    }

                    String productBaseCanKey = buildKey(productBaseCan);
                    if(mapProductBaseCan.get(productBaseCanKey) != null){
                        errorsMessage = "The product base can (" + baseCode + " " + productCode + " " + can + ") is exits!";
                    }

                    if(StringUtils.isNotBlank(errorsMessage)){
                        ImportProductBaseCan error = new ImportProductBaseCan();
                        error.setColumn(1);
                        error.setRow(row);
                        error.setErrors(errorsMessage);
                        error.setProductBaseCan(productBaseCan);
                        listErrors.add(error);
                    } else {
                        mapProductBaseCan.put(productBaseCanKey, productBaseCan);
                    }
                } catch(Exception e){
                    ImportProductBaseCan error = new ImportProductBaseCan();
                    error.setRow(row);
                    error.setErrors("Cannot parse data of the row. The format isn't correctly.<span style='display: none'>" + e.getMessage() + "</span>");
                    listErrors.add(error);
                }
            }

            row ++;
        }
        return listErrors;
    }

    private List<ImportProductBase> validateSheet2(
            Map<String, Base> mapBases,
            Map<String, Product> mapProducts,
            Map<String, ProductBase> mapProductBase, Sheet datatypeSheet){

        List<ImportProductBase> listErrors = new ArrayList<>();

        Iterator<Row> iterator = datatypeSheet.iterator();
        int row = 0;
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            if (row > 0) { // bo qua row 1
                try{
                    String baseCode = currentRow.getCell(0).getStringCellValue();
                    String productCode = currentRow.getCell(1).getStringCellValue();
                    Double density = currentRow.getCell(2).getNumericCellValue();
                    String color = convertRBG2Hex(Double.valueOf(currentRow.getCell(3).getNumericCellValue()).intValue(),
                            Double.valueOf(currentRow.getCell(4).getNumericCellValue()).intValue(),
                            Double.valueOf(currentRow.getCell(5).getNumericCellValue()).intValue());

                    if(StringUtils.isNotBlank(baseCode) && StringUtils.isNotBlank(productCode) && density > 0 && StringUtils.isNotBlank(color)){
                        Base base = new Base();
                        base.setBaseCode(baseCode);
                        base.setBaseName(baseCode);
                        base.setCreatedDate(new Timestamp(System.currentTimeMillis()));

                        Product product = new Product();
                        product.setProductCode(productCode);
                        product.setProductName(productCode);
                        product.setCreatedDate(new Timestamp(System.currentTimeMillis()));

                        ProductBase productBase = new ProductBase();
                        productBase.setBase(base);
                        productBase.setProduct(product);
                        productBase.setDensity(density);
                        productBase.setRbgHex(color);

                        if(mapBases.get(baseCode) == null){
                            mapBases.put(baseCode,  base);
                        }

                        if(mapProducts.get(productCode) == null){
                            mapProducts.put(productCode, product);
                        }

                        String productBaseKey = buildProductBase(baseCode, productCode);

                        if(mapProductBase.get(productBaseKey) != null){
                            ImportProductBase errors = new ImportProductBase(row, 1,
                                    "Product Base (" + baseCode + ", " + productCode + ") has been exits !", productBase);
                            listErrors.add(errors);
                        } else {
                            mapProductBase.put(productBaseKey, productBase);
                        }
                    }
                } catch (Exception e){
                    ImportProductBase error = new ImportProductBase(row, 0, "Cannot parse data of the row. The format isn't correctly.<span style='display: none'>" + e.getMessage() + "</span>", null);
                    listErrors.add(error);
                }
            }
            row++;
        }

        return listErrors;
    }

    private List<ImportColourant> validateSheet1(Map<String, Colourant> mapResult, Sheet datatypeSheet){
        List<ImportColourant> listErrors = new ArrayList<>();

        Iterator<Row> iterator = datatypeSheet.iterator();
        int row = 0;
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            if(row > 0) {
                try{
                    Colourant colorant = new Colourant(
                            currentRow.getCell(0).getStringCellValue(),
                            currentRow.getCell(1).getStringCellValue(),
                            convertRBG2Hex(Double.valueOf(currentRow.getCell(3).getNumericCellValue()).intValue(),
                                    Double.valueOf(currentRow.getCell(4).getNumericCellValue()).intValue(),
                                    Double.valueOf(currentRow.getCell(5).getNumericCellValue()).intValue()),
                            currentRow.getCell(2).getNumericCellValue(),
                            currentRow.getCell(8).getNumericCellValue(),
                            Double.valueOf(currentRow.getCell(9).getNumericCellValue()).intValue(),
                            currentRow.getCell(10).getStringCellValue());

                    if(mapResult.get(colorant.getColourantCode()) != null){
                        ImportColourant error = new ImportColourant(row, 0, "The colourant code " + colorant.getColourantCode() + " has been existed!", colorant);
                        listErrors.add(error);
                    } else {
                        mapResult.put(colorant.getColourantCode(), colorant);
                    }
                } catch(Exception e){
                    ImportColourant error = new ImportColourant(row, 0, "Cannot parse data of the row. The format isn't correctly. <span style='display: none'>" + e.getMessage() + "</span>", null);
                    listErrors.add(error);
                }
            }
            row++;
        }
        return listErrors;
    }

    private void parseSheet_4(Map<String, Colourant> mapColourants,
                              Map<String, ProductBase> mapProductBase,
                              Sheet datatypeSheet) {
        Map<String, Collection> mapCollections = new HashMap<>();
        List<Collection> listCollectionExists = collectionService.findAll();
        for(Collection c : listCollectionExists){
            mapCollections.put(c.getCollectionName(), c);
        }

        Map<String, Formula> mapFormulas = new HashMap<>();
        List<Formula> listFormulaExists = formulaService.findAll();
        for(Formula f : listFormulaExists){
            mapFormulas.put(f.getFormulaName(), f);
        }

        Iterator<Row> iterator = datatypeSheet.iterator();
        int row = 0;
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            if (row > 0) { // bo qua row 1
                String collectionName = currentRow.getCell(0).getStringCellValue();
                if(mapCollections.get(collectionName) == null){
                    Collection c = new Collection();
                    c.setCollectionName(collectionName);
                    c.setDescription(collectionName);
                    c.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                    collectionService.save(c);
                    mapCollections.put(collectionName, c);
                }

                String formulaName = null; // tìm formula
                if(currentRow.getCell(2).getCellType() == CellType.NUMERIC){
                    formulaName = String.valueOf(currentRow.getCell(2).getNumericCellValue());
                }else if(currentRow.getCell(2).getCellType() == CellType.STRING){
                    formulaName = currentRow.getCell(2).getStringCellValue();
                }else{
                    System.out.println("**-----------------------------");
                    System.out.println("Can not find formula name at row " + row);
                    row++;
                    continue;
                }
                formulaName = formulaName.replace(".0", "");
                if(mapFormulas.get(formulaName) == null){ // init nếu chua tồn tại
                    Formula formula = new Formula();
                    formula.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                    formula.setFormulaCode(formulaName);
                    formula.setFormulaName(formulaName);
                    mapFormulas.put(formulaName, formula);
                }

                Formula formula = mapFormulas.get(formulaName);
                formula.setCollection(mapCollections.get(collectionName));
                formula.setBaseOnCan(currentRow.getCell(3).getNumericCellValue());
                formula.setApproximateColor(convertDecimalToColor((int) currentRow.getCell(20).getNumericCellValue()));
                formula.setComment(currentRow.getCell(21).getStringCellValue());
                formula.setSubstrate(currentRow.getCell(22).getStringCellValue());
                if(formula.getFormulaId() == null){ // save or update
                    formulaService.save(formula);
                }else{
                    formulaService.update(formula);
                }


                List<FormulaProductBase> listFormulaProductBaseExists = formulaService.findFormulaProductBaseByFormulaId(formula.getFormulaId());
                Map<String, FormulaProductBase> mapFormulaProductBaseExists = new HashMap<>();
                for(FormulaProductBase fpb : listFormulaProductBaseExists){ // map formula product base cũ
                    mapFormulaProductBaseExists.put(fpb.getProductBase().getBase().getBaseCode() + "_" + fpb.getProductBase().getProduct().getProductCode() , fpb);
                }

                String base = currentRow.getCell(5).getStringCellValue();
                String[] arrayProductCodes = currentRow.getCell(1).getStringCellValue().split(",");
                for(String productCode : arrayProductCodes){
                    String key = base + "_" + productCode;
                    if(mapProductBase.get(key) == null){
                        System.out.println("**-----------------------------");
                        System.out.println("Can not find product base - " + key);
                    }else{
                        if(mapFormulaProductBaseExists.get(key) == null){ // save mới formula product base
                            FormulaProductBase fpb = new FormulaProductBase();
                            fpb.setFormula(formula);
                            fpb.setProductBase(mapProductBase.get(key));
                            formulaService.saveFormulaProductBase(fpb);
                        }else{
                            mapFormulaProductBaseExists.remove(key); // nếu có sẵn thì xóa key trong map
                        }
                    }
                }
                for(String keyRemove : mapFormulaProductBaseExists.keySet()){ // key formula product base nào cũ còn lại thì xóa đi
                    formulaService.deleteFormulaProductBase(mapFormulaProductBaseExists.get(keyRemove));
                }

                List<FormulaColourant> listFormulaColourantExists = formulaService.findFormulaColourantByFormulaId(formula.getFormulaId());
                Map<String, FormulaColourant> mapFormulaColourantExists = new HashMap<>(); // tương tự cho formula colourant
                for(FormulaColourant fc : listFormulaColourantExists){
                    mapFormulaColourantExists.put(fc.getColourant().getColourantCode(), fc);
                }

                if(currentRow.getCell(6) != null) {
                    addColourant(currentRow.getCell(6).getStringCellValue(),
                            currentRow.getCell(7).getNumericCellValue(),
                            mapColourants,
                            mapFormulaColourantExists,
                            formula);
                }

                if(currentRow.getCell(8) != null) {
                    addColourant(currentRow.getCell(8).getStringCellValue(),
                            currentRow.getCell(9).getNumericCellValue(),
                            mapColourants,
                            mapFormulaColourantExists,
                            formula);
                }

                if(currentRow.getCell(10) != null) {
                    addColourant(currentRow.getCell(10).getStringCellValue(),
                            currentRow.getCell(11).getNumericCellValue(),
                            mapColourants,
                            mapFormulaColourantExists,
                            formula);
                }

                if(currentRow.getCell(12) != null) {
                    addColourant(currentRow.getCell(12).getStringCellValue(),
                            currentRow.getCell(13).getNumericCellValue(),
                            mapColourants,
                            mapFormulaColourantExists,
                            formula);
                }

                if(currentRow.getCell(14) != null) {
                    addColourant(currentRow.getCell(14).getStringCellValue(),
                            currentRow.getCell(15).getNumericCellValue(),
                            mapColourants,
                            mapFormulaColourantExists,
                            formula);
                }

                if(currentRow.getCell(16) != null) {
                    addColourant(currentRow.getCell(16).getStringCellValue(),
                            currentRow.getCell(17).getNumericCellValue(),
                            mapColourants,
                            mapFormulaColourantExists,
                            formula);
                }
                for(String keyRemove : mapFormulaColourantExists.keySet()){ // xóa key formula colourant cũ còn tồn tại đi
                    formulaService.deleteFormulaColourant(mapFormulaColourantExists.get(keyRemove));
                }
            }
            row ++;
        }
    }

    private static String convertDecimalToColor(int decimalNumber){
        Color color = new Color(decimalNumber);
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        return convertRBG2Hex(red, green, blue);
    }

    private void addColourant(String colorantCode,
                              Double quantity,
                              Map<String, Colourant> mapColourants,
                              Map<String, FormulaColourant> mapFormulaColourantExists,
                              Formula formula){
        if(colorantCode != null && colorantCode.trim().length() > 0){
            if(mapColourants.get(colorantCode) == null){
                System.out.println("**-----------------------------");
                System.out.println("Can not find colourant - " + colorantCode);
            }else if (quantity == null){
                System.out.println("**-----------------------------");
                System.out.println("Can not parseQuantity - " + colorantCode);
            }else{
                if(mapFormulaColourantExists.get(colorantCode) == null){
                    FormulaColourant formulaColourant = new FormulaColourant();
                    formulaColourant.setFormula(formula);
                    formulaColourant.setColourant(mapColourants.get(colorantCode));
                    formulaColourant.setQuantity(quantity);
                    formulaService.saveFormulaColourant(formulaColourant);
                }else{
                    mapFormulaColourantExists.remove(colorantCode);
                }
            }
        }
    }

    private void parseSheet_3(Map<String, ProductBase> mapProductBase,
                              Map<String, ProductBaseCan> mapProductBaseCan,
                              Sheet datatypeSheet) {
        List<ProductBaseCan> listPBCExists = productService.findAllProductBaseCan();
        for(ProductBaseCan pbc : listPBCExists){
            mapProductBaseCan.put(buildKey(pbc), pbc);
        }

        Iterator<Row> iterator = datatypeSheet.iterator();
        int row = 0;
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            if (row > 0) { // bo qua row 1
                String baseCode = currentRow.getCell(0).getStringCellValue();
                String productcode = currentRow.getCell(1).getStringCellValue();
                Double can = Double.valueOf(currentRow.getCell(2).getStringCellValue().toLowerCase().replace("lt", "").trim());
                String key = buildKey(baseCode, productcode, can);

                if(mapProductBaseCan.get(key) == null){
                    ProductBaseCan pbc = new ProductBaseCan();
                    String keyProductBase = baseCode + "_" + productcode;
                    if(mapProductBase.get(keyProductBase) == null){
                        System.out.println("****-----------------------------");
                        System.out.println("Can not find product base - " + keyProductBase);
                    }else{
                        pbc.setProductBase(mapProductBase.get(keyProductBase));
                        pbc.setCan(can);
                        pbc.setPricePerCan(currentRow.getCell(4).getNumericCellValue());
                        pbc.setPercentage(Double.valueOf(currentRow.getCell(5).getNumericCellValue()).intValue());
                        pbc.setBarCode(Double.valueOf(currentRow.getCell(6).getNumericCellValue()).toString());
                        productService.saveProductBaseCan(pbc);
                        mapProductBaseCan.put(key, pbc);
                    }
                }

            }
            row ++;
        }
    }

    private String buildProductBase (String baseCode, String productCode){
        return baseCode + "_" + productCode;
    }

    private String buildFormulaProductBase(String formulaCode, String productCode, String baseCode){
        return formulaCode + "_" + productCode + "_" + baseCode;
    }

    private String buildKey(ProductBaseCan pbc){
        return buildKey(pbc.getProductBase().getBase().getBaseCode(), pbc.getProductBase().getProduct().getProductCode(), pbc.getCan());
    }

    private String buildKey(String baseCode, String productCode, Double can){
        return (baseCode + "_" + productCode + "_" + can).toLowerCase().replace("lt", "").trim();
    }

    /**
     * Parse sheet thung son
     * @param mapBases
     * @param mapProducts
     * @param datatypeSheet
     */
    private void parseSheet_2(Map<String, Base> mapBases,
                              Map<String, Product> mapProducts,
                              Map<String, ProductBase> mapProductBase,
                              Sheet datatypeSheet){
        List<Base> listBaseExists = baseService.findAll();
        for(Base base : listBaseExists){
            mapBases.put(base.getBaseCode(), base);
        }

        List<Product> listProductExists = productService.findAll();
        for(Product product : listProductExists){
            mapProducts.put(product.getProductCode(), product);
        }

        List<ProductBase> listProductBaseExists = baseService.findAllProductBases();
        for(ProductBase productBase : listProductBaseExists){
            mapProductBase.put(productBase.getBase().getBaseCode() + "_" + productBase.getProduct().getProductCode(), productBase);
        }

        Iterator<Row> iterator = datatypeSheet.iterator();
        int row = 0;
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            if (row > 0) { // bo qua row 1

                if(currentRow == null || currentRow.getCell(0) == null){
                    System.out.println("Row " + row);
                    continue;
                }
                String baseCode = currentRow.getCell(0).getStringCellValue();

                baseCode = baseCode.trim();
                if(mapBases.get(baseCode) == null){
                    Base base = new Base();
                    base.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                    base.setBaseCode(baseCode);
                    base.setBaseName(baseCode);
                    baseService.save(base);
                    mapBases.put(baseCode, base);
                }

                String productCode = currentRow.getCell(1).getStringCellValue().trim();
                if(mapProducts.get(productCode) == null){
                    Product product = new Product();
                    product.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                    product.setProductCode(productCode);
                    product.setProductName(productCode);
                    productService.save(product);
                    mapProducts.put(productCode, product);
                }

                String key = baseCode + "_"+ productCode;
                ProductBase pb;
                if(mapProductBase.get(key) == null){
                    pb = new ProductBase();
                    pb.setProduct(mapProducts.get(productCode));
                    pb.setBase(mapBases.get(baseCode));
                }else{
                    pb = mapProductBase.get(key);
                }

                pb.setDensity(currentRow.getCell(2).getNumericCellValue());
                pb.setRbgHex(convertRBG2Hex(Double.valueOf(currentRow.getCell(3).getNumericCellValue()).intValue(),
                        Double.valueOf(currentRow.getCell(4).getNumericCellValue()).intValue(),
                        Double.valueOf(currentRow.getCell(5).getNumericCellValue()).intValue()));

                if(pb.getProductBaseId() == null){
                    baseService.saveProductBase(pb);
                    mapProductBase.put(key, pb);
                }else{
                    baseService.updateProductBase(pb);
                }
            }
            row++;
        }
    }

    /**
     * Parse sheet tinh mau
     * @param datatypeSheet
     * @return
     */
    private Map<String, Colourant> parseSheet_1(Map<String, Colourant> mapResult, Sheet datatypeSheet){
        List<Colourant> listExists = colourantService.findAll();
        for(Colourant colourant : listExists){
            mapResult.put(colourant.getColourantCode(), colourant);
        }

        Iterator<Row> iterator = datatypeSheet.iterator();
        int row = 0;
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            if(row > 0) { // bo qua row 1
                String colourantcode = currentRow.getCell(0).getStringCellValue();
                Colourant colourant;
                if(mapResult.get(colourantcode) != null){
                    colourant = mapResult.get(colourantcode);
                }else{
                    colourant = new Colourant();
                    colourant.setColourantCode(colourantcode);
                }
                colourant.setColourantName(currentRow.getCell(1).getStringCellValue());
                colourant.setDensity(currentRow.getCell(2).getNumericCellValue());
                colourant.setRbgHex(convertRBG2Hex(Double.valueOf(currentRow.getCell(3).getNumericCellValue()).intValue(),
                        Double.valueOf(currentRow.getCell(4).getNumericCellValue()).intValue(),
                        Double.valueOf(currentRow.getCell(5).getNumericCellValue()).intValue()));
                System.out.println("Colour " + colourant.getColourantName() + ' ' + colourant.getRbgHex());
                colourant.setDensity(currentRow.getCell(8).getNumericCellValue());
                colourant.setSurcharge(Double.valueOf(currentRow.getCell(9).getNumericCellValue()).intValue());
                colourant.setKind(currentRow.getCell(10).getStringCellValue());

                if(colourant.getColourantId() == null){
                    colourantService.save(colourant);
                    mapResult.put(colourantcode, colourant);
                }else{
                    colourantService.update(colourant);
                }
            }
            row++;
        }
        return mapResult;
    }

    /**
     * @param red
     * @param green
     * @param blue
     * @return
     */
    private static String convertRBG2Hex(Integer red, Integer green, Integer blue){
        String hex = String.format("#%02x%02x%02x", red, green, blue);
        return hex;
    }
}


