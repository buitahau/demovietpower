package vietpower.com.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by HauKute on 12/17/2018.
 */
@Entity
@Table(name="MachineFormulaProductBase")
public class MachineFormulaProductBase {
    @Id
    @Column(name = "machineId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long machineFormulaProductBaseId;

    @ManyToOne
    @JoinColumn(name="machineId")
    private Machine machine;

    @ManyToOne
    @JoinColumn(name="formulaProductBaseId")
    private FormulaProductBase formulaProductBase;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "createdDate")
    private Timestamp createdDate;

    public Long getMachineFormulaProductBaseId() {
        return machineFormulaProductBaseId;
    }

    public void setMachineFormulaProductBaseId(Long machineFormulaProductBaseId) {
        this.machineFormulaProductBaseId = machineFormulaProductBaseId;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public FormulaProductBase getFormulaProductBase() {
        return formulaProductBase;
    }

    public void setFormulaProductBase(FormulaProductBase formulaProductBase) {
        this.formulaProductBase = formulaProductBase;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
