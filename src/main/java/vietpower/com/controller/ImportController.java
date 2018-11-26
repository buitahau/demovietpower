package vietpower.com.controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vietpower.com.model.*;
import vietpower.com.service.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.awt.Color;
/**
 * Created by HauKute on 11/25/2018.
 */
@Controller
public class ImportController implements Serializable {

    private static final String FILE_EXCEL = "X:\\ws_learning\\demovietpower\\db\\DB_Specifications.xlsx";

    @Autowired
    ColourantService colourantService;
    @Autowired
    BaseService baseService;
    @Autowired
    ProductService productService;

    @RequestMapping(value = "/sort/bubble.html", method = RequestMethod.GET)
    public String sortBubble(){
        parseFileExcel();
        return "sort/bubble";
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
        Map<String, Formula> mapFormula = new HashMap<>();
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
                        pbc.setBarCode(currentRow.getCell(6).getStringCellValue());
                        productService.saveProductBaseCan(pbc);
                        mapProductBaseCan.put(key, pbc);
                    }
                }

            }
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
            mapProductBase.put(productBase.getBase().getBaseCode() + productBase.getProduct().getProductCode(), productBase);
        }

        Iterator<Row> iterator = datatypeSheet.iterator();
        int row = 0;
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            if (row > 0) { // bo qua row 1
                String baseCode = currentRow.getCell(0).getStringCellValue().trim();
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
