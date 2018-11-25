package vietpower.com.model;

import javax.persistence.*;

/**
 * Created by HauKute on 11/25/2018.
 */
@Entity
@Table(name="FormulaColourant")
public class FormulaColourant {
    @Id
    @Column(name = "formulaColourantId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formulaColourantId;

    @ManyToOne
    @JoinColumn(name="formulaId")
    private Formula formula;

    @ManyToOne
    @JoinColumn(name="colourantId")
    private Colourant colourant;

    @Column(name = "quantity")
    private Float quantity;

    public Long getFormulaColourantId() {
        return formulaColourantId;
    }

    public void setFormulaColourantId(Long formulaColourantId) {
        this.formulaColourantId = formulaColourantId;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public Colourant getColourant() {
        return colourant;
    }

    public void setColourant(Colourant colourant) {
        this.colourant = colourant;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }
}
