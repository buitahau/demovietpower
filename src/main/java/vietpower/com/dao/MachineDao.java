package vietpower.com.dao;

import vietpower.com.model.Machine;

import java.util.List;

/**
 * Created by HauKute on 12/14/2018.
 */
public interface MachineDao {
    List<Machine> findAll();

    Machine findById(Long machineId);
}
