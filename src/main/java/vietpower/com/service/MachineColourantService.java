package vietpower.com.service;

import vietpower.com.model.Base;
import vietpower.com.model.MachineColourant;
import vietpower.com.model.ProductBase;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
public interface MachineColourantService {

    List<MachineColourant> findByMachineId(Long machineId);
}
