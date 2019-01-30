package vietpower.com.dao;

import vietpower.com.model.MachineFormulaProductBase;

import java.util.List;

/**
 * Created by HauKute on 12/20/2018.
 */
public interface MachineFormulaProductBaseDao {
    void persist(MachineFormulaProductBase machineFormulaProductBase);

    List<MachineFormulaProductBase> getByMachine(Long machineId);

    MachineFormulaProductBase findById(Long taskId);

    void deleteAll();
}
