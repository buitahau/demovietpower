package vietpower.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.MachineColourantDao;
import vietpower.com.model.Colourant;
import vietpower.com.model.Machine;
import vietpower.com.model.MachineColourant;
import vietpower.com.service.ColourantService;
import vietpower.com.service.MachineColourantService;
import vietpower.com.service.MachineService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Service("machineColourantService")
@Transactional
public class MachineColourantServiceImpl implements MachineColourantService {
    @Autowired
    private MachineColourantDao machineColourantDao;

    @Autowired
    private ColourantService colourantService;

    @Autowired
    private MachineService machineService;

    @Override
    public List<MachineColourant> findByMachineId(Long machineId) {
        List<MachineColourant> listResult =  machineColourantDao.findByMachineId(machineId);
        if(listResult == null || listResult.size() == 0){
            Machine machine = this.machineService.findById(machineId);
            List<Colourant> listColourant = this.colourantService.findAll();
            List<MachineColourant> initSet = new ArrayList<>();

            for(Colourant colourant : listColourant){
                MachineColourant machineColourant = new MachineColourant();
                machineColourant.setMachine(machine);
                machineColourant.setColourant(colourant);
                machineColourant.setQuantity(0d);

                machineColourantDao.persist(machineColourant);

                initSet.add(machineColourant);
            }
            return initSet;
        } else {
            return listResult;
        }
    }
}
