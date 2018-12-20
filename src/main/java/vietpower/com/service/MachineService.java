package vietpower.com.service;

import vietpower.com.model.Machine;
import vietpower.com.model.MachineColourant;
import vietpower.com.model.MachineFormulaProductBase;

import java.util.List;

/**
 * Created by HauKute on 12/20/2018.
 */
public interface MachineService {
    List<Machine> findAllMachine();

    void subtractColour(MachineColourant machineColourant);

    void saveFormulaProductBase(MachineFormulaProductBase machineFormulaProductBase);
}
