package vietpower.com.model;

import javax.persistence.*;

/**
 * Created by HauKute on 11/25/2018.
 */
@Entity
@Table(name="FormulaProductBaseCan")
public class FormulaProductBaseCan {
    @Id
    @Column(name = "formulaProductBaseCanId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formulaProductBaseCanId;

    @ManyToOne
    @JoinColumn(name="formulaId")
    private Formula formula;

    @ManyToOne
    @JoinColumn(name="productBaseCanId")
    private ProductBaseCan productBaseCan;

    public Long getFormulaProductBaseCanId() {
        return formulaProductBaseCanId;
    }

    public void setFormulaProductBaseCanId(Long formulaProductBaseCanId) {
        this.formulaProductBaseCanId = formulaProductBaseCanId;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public ProductBaseCan getProductBaseCan() {
        return productBaseCan;
    }

    public void setProductBaseCan(ProductBaseCan productBaseCan) {
        this.productBaseCan = productBaseCan;
    }
}
