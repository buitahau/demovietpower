package vietpower.com.model;

import java.util.List;

public class SavingFormulaProductBase {
    private FormulaProductBase formulaProductBase;
    private Machine machine;
    private List<FormulaColourant> formulaColourantList;

    public FormulaProductBase getFormulaProductBase() {
        return formulaProductBase;
    }

    public void setFormulaProductBase(FormulaProductBase formulaProductBase) {
        this.formulaProductBase = formulaProductBase;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public List<FormulaColourant> getFormulaColourantList() {
        return formulaColourantList;
    }

    public void setFormulaColourantList(List<FormulaColourant> formulaColourantList) {
        this.formulaColourantList = formulaColourantList;
    }
}
