package vietpower.com.model;

import java.util.List;

public class FormulaModel {
    private Formula formula;
    private List<FormulaColourant> listFormulaColourant;

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public List<FormulaColourant> getListFormulaColourant() {
        return listFormulaColourant;
    }

    public void setListFormulaColourant(List<FormulaColourant> listFormulaColourant) {
        this.listFormulaColourant = listFormulaColourant;
    }
}
