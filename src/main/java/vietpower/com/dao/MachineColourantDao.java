package vietpower.com.dao;

import vietpower.com.model.MachineColourant;

/**
 * Created by HauKute on 12/20/2018.
 */
public interface MachineColourantDao {
    MachineColourant findByMachineAndColour(Long machineId, Long colourantId);

    void persist(MachineColourant machineColourant);

    void update(MachineColourant machineColourant);
}
