package vietpower.com.model;

import javax.persistence.*;

/**
 * Created by HauKute on 11/25/2018.
 */
@Entity
@Table(name="FormulaCustomer")
public class FormulaCustomer {
    @Id
    @Column(name = "formulaCustomerId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formulaCustomerId;

    @ManyToOne
    @JoinColumn(name="formulaId")
    private Formula formula;

    @ManyToOne
    @JoinColumn(name="customerId")
    private Customer customer;

    public Long getFormulaCustomerId() {
        return formulaCustomerId;
    }

    public void setFormulaCustomerId(Long formulaCustomerId) {
        this.formulaCustomerId = formulaCustomerId;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
