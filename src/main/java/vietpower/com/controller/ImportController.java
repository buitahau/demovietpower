package vietpower.com.controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import vietpower.com.model.*;
import vietpower.com.model.Collection;
import vietpower.com.service.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import java.awt.Color;
/**
 * Created by HauKute on 11/25/2018.
 */
@Controller
public class ImportController implements Serializable {
    private static final String FILE_EXCEL = "../resources/DB_Specifications.xlsx";

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

    @RequestMapping("/import/upload.html")
    public String uploadForm(){
        parseFileExcel();
        return "import/import";
    }

    @RequestMapping(value="/import/savefile.html", method=RequestMethod.POST )
    public  String singleSave(@RequestParam("file") MultipartFile file, @RequestParam("desc") String desc ){
        System.out.println("File Description:" + desc);
        String fileName = null;
        if (!file.isEmpty()) {
            try {
                fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream =
                        new BufferedOutputStream(new FileOutputStream(new File("F:/cp/" + fileName)));
                buffStream.write(bytes);
                buffStream.close();
                return "You have successfully uploaded " + fileName;
            } catch (Exception e) {
                return "You failed to upload " + fileName + ": " + e.getMessage();
            }
        } else {
            return "Unable to upload. File is empty.";
        }
    }

    private void parseFileExcel(){
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_EXCEL));
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
        }catch (Exception e) {
            e.printStackTrace();
        }
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

                String formulaName = null;
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
                if(mapFormulas.get(formulaName) == null){
                    Formula formula = new Formula();
                    formula.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                    formula.setCollection(mapCollections.get(collectionName));
                    formula.setFormulaCode(formulaName);
                    formula.setFormulaName(formulaName);
                    formulaService.save(formula);
                    mapFormulas.put(formulaName, formula);
                }

                Formula formula = mapFormulas.get(formulaName);
                List<FormulaProductBase> listFormulaProductBaseExists = formulaService.findFormulaProductBaseByFormulaId(formula.getFormulaId());
                Map<String, FormulaProductBase> mapFormulaProductBaseExists = new HashMap<>();
                for(FormulaProductBase fpb : listFormulaProductBaseExists){
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
                        if(mapFormulaProductBaseExists.get(key) == null){
                            FormulaProductBase fpb = new FormulaProductBase();
                            fpb.setFormula(formula);
                            fpb.setProductBase(mapProductBase.get(key));
                            formulaService.saveFormulaProductBase(fpb);
                        }else{
                            mapFormulaProductBaseExists.remove(key);
                        }
                    }
                }
                for(String keyRemove : mapFormulaProductBaseExists.keySet()){
                    formulaService.deleteFormulaProductBase(mapFormulaProductBaseExists.get(keyRemove));
                }

                List<FormulaColourant> listFormulaColourantExists = formulaService.findFormulaColourantByFormulaId(formula.getFormulaId());
                Map<String, FormulaColourant> mapFormulaColourantExists = new HashMap<>();
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
                for(String keyRemove : mapFormulaColourantExists.keySet()){
                    formulaService.deleteFormulaColourant(mapFormulaColourantExists.get(keyRemove));
                }
            }
            row ++;
        }
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
    private String convertRBG2Hex(Integer red, Integer green, Integer blue){
        Color color = new Color(red, green ,blue);
        String hex = Integer.toHexString(color.getRGB() & 0xffffff);
        if (hex.length() < 6) {
            hex = "0" + hex;
        }
        return "#" + hex;
    }
}
