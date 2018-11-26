package vietpower.com.model;

import javax.persistence.*;

/**
 * Created by HauKute on 11/25/2018.
 */
@Entity
@Table(name="FormulaProductBase")
public class FormulaProductBase {
    @Id
    @Column(name = "formulaProductBaseId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formulaProductBaseId;

    @ManyToOne
    @JoinColumn(name="formulaId")
    private Formula formula;

    @ManyToOne
    @JoinColumn(name="productBaseId")
    private ProductBase productBase;

    public Long getFormulaProductBaseId() {
        return formulaProductBaseId;
    }

    public void setFormulaProductBaseId(Long formulaProductBaseId) {
        this.formulaProductBaseId = formulaProductBaseId;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public ProductBase getProductBase() {
        return productBase;
    }

    public void setProductBase(ProductBase productBase) {
        this.productBase = productBase;
    }
}
