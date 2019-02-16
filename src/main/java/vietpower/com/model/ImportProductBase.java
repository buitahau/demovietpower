package vietpower.com.model;

public class ImportProductBase {
    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public ProductBase getProductBase() {
        return productBase;
    }

    public void setProductBase(ProductBase productBase) {
        this.productBase = productBase;
    }

    private Integer row;
    private Integer column;
    private String errors;
    private ProductBase productBase;

    public ImportProductBase(Integer row, Integer column, String errors, ProductBase productBase) {
        this.row = row;
        this.column = column;
        this.errors = errors;
        this.productBase = productBase;
    }
}
