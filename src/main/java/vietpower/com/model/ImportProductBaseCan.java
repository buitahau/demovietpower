package vietpower.com.model;

public class ImportProductBaseCan {
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

    public ProductBaseCan getProductBaseCan() {
        return productBaseCan;
    }

    public void setProductBaseCan(ProductBaseCan productBaseCan) {
        this.productBaseCan = productBaseCan;
    }

    private Integer row;
    private Integer column;
    private String errors;
    private ProductBaseCan productBaseCan;
}
