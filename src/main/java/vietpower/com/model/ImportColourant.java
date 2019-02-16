package vietpower.com.model;

public class ImportColourant {
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

    public Colourant getColourant() {
        return colourant;
    }

    public void setColourant(Colourant colourant) {
        this.colourant = colourant;
    }

    private Integer row;
    private Integer column;
    private String errors;
    private Colourant colourant;

    public ImportColourant(Integer row, Integer column, String errors, Colourant colourant) {
        this.row = row;
        this.column = column;
        this.errors = errors;
        this.colourant = colourant;
    }
}
