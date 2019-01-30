package vietpower.com.model;

import javax.persistence.*;

/**
 * Created by HauKute on 12/17/2018.
 */
@Entity
@Table(name="MachineColourant")
public class MachineColourant {
    @Id
    @Column(name = "machineColourantId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long machineColourantId;

    @ManyToOne
    @JoinColumn(name="machineId")
    private Machine machine;

    @ManyToOne
    @JoinColumn(name="colourantId")
    private Colourant colourant;

    @Column
    private Double quantity;

    @Column
    private Double refillFactor;

    @Transient
    private Long machineFormulaProductBaseId;

    public Long getMachineColourantId() {
        return machineColourantId;
    }

    public void setMachineColourantId(Long machineColourantId) {
        this.machineColourantId = machineColourantId;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Colourant getColourant() {
        return colourant;
    }

    public void setColourant(Colourant colourant) {
        this.colourant = colourant;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getMachineFormulaProductBaseId() {
        return machineFormulaProductBaseId;
    }

    public void setMachineFormulaProductBaseId(Long machineFormulaProductBaseId) {
        this.machineFormulaProductBaseId = machineFormulaProductBaseId;
    }

    public Double getRefillFactor() {
        return refillFactor;
    }

    public void setRefillFactor(Double refillFactor) {
        this.refillFactor = refillFactor;
    }
}
