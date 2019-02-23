package vietpower.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.*;
import vietpower.com.model.*;
import vietpower.com.service.MachineService;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by HauKute on 12/20/2018.
 */
@Service("machineService")
@Transactional
public class MachineServiceImpl implements MachineService{
    @Autowired
    MachineDao machineDao;
    @Autowired
    MachineColourantDao machineColourantDao;
    @Autowired
    ColourantDao colourantDao;
    @Autowired
    MachineColourantLogDao machineColourantLogDao;
    @Autowired
    MachineFormulaProductBaseDao machineFormulaProductBaseDao;

    @Override
    public List<Machine> findAllMachine() {
        return machineDao.findAll();
    }

    @Override
    public void subtractColour(MachineColourant dto) {
        Long machineId = dto.getMachine().getMachineId();
        Long colourantId = dto.getColourant().getColourantId();

        Machine machine = machineDao.findById(machineId);
        Colourant colourant = colourantDao.findById(colourantId);

        MachineColourant machineColourant = machineColourantDao.findByMachineAndColour(machineId, colourantId);
        if(machineColourant == null){
            machineColourant = new MachineColourant();
            machineColourant.setMachine(machine);
            machineColourant.setColourant(colourant);
            machineColourant.setQuantity(0d);
            machineColourantDao.persist(machineColourant);
        }

        MachineColourantLog log = new MachineColourantLog();
        log.setQuantity(dto.getQuantity());
        log.setAction("subtract");
        log.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        log.setMachineColourant(machineColourant);
        if(dto.getMachineFormulaProductBaseId() != null){
            MachineFormulaProductBase mf = new MachineFormulaProductBase();
            mf.setMachineFormulaProductBaseId(dto.getMachineFormulaProductBaseId());
            log.setMachineFormulaProductBase(mf);
        }
        machineColourantLogDao.persist(log);

        machineColourant.setQuantity(machineColourant.getQuantity() - dto.getQuantity());
        machineColourantDao.update(machineColourant);
    }

    @Override
    public MachineFormulaProductBase saveFormulaProductBase(MachineFormulaProductBase machineFormulaProductBase) {
        if (machineFormulaProductBase.getMachineFormulaProductBaseId() != null && machineFormulaProductBase.getMachineFormulaProductBaseId() > 0) {
            MachineFormulaProductBase dbItem = this.machineFormulaProductBaseDao.findById(machineFormulaProductBase.getMachineFormulaProductBaseId());
            dbItem.setStatus(machineFormulaProductBase.getStatus());

            if ("DONE".equals(dbItem.getStatus())) {
                dbItem.setFinishedDate(new Timestamp(System.currentTimeMillis()));
            }
            machineFormulaProductBaseDao.persist(dbItem);
            return dbItem;
        } else {
            machineFormulaProductBase.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            machineFormulaProductBaseDao.persist(machineFormulaProductBase);
            return machineFormulaProductBase;
        }
    }

    @Override
    public List<MachineColourant> getAllMachineColourant(Long machineId) {
        return machineColourantDao.findByMachineId(machineId);
    }

    @Override
    public List<MachineColourantLog> getAllMachineColourantLog(Long machineColourantLogId) {
        return machineColourantLogDao.getByMachineColourant(machineColourantLogId);
    }

    @Override
    public List<MachineFormulaProductBase> getAllMachineFormulaProductBase(Long machineId) {
        return machineFormulaProductBaseDao.getByMachine(machineId);
    }

    @Override
    public void updateMachineColourant(MachineColourant machineColourant) {
        Long machineId = machineColourant.getMachine().getMachineId();
        Long colourantId = machineColourant.getColourant().getColourantId();

        Machine machine = machineDao.findById(machineId);
        Colourant colourant = colourantDao.findById(colourantId);

        MachineColourant dbItem = machineColourantDao.findByMachineAndColour(machineId, colourantId);
        if(dbItem == null){
            dbItem.setMachine(machine);
            dbItem.setColourant(colourant);
            dbItem.setQuantity(machineColourant.getQuantity());
            machineColourantDao.persist(dbItem);
        }

        dbItem.setQuantity(dbItem.getQuantity() + machineColourant.getQuantity());
        dbItem.setRefillFactor(machineColourant.getRefillFactor() != null && machineColourant.getRefillFactor() > 0 ? machineColourant.getRefillFactor()  : 100 );

        machineColourantDao.update(dbItem);

        MachineColourantLog log = new MachineColourantLog();
        log.setQuantity(machineColourant.getQuantity());
        log.setAction("add");
        log.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        log.setMachineColourant(dbItem);
        machineColourantLogDao.persist(log);
    }

    @Override
    public Machine findById(Long machineId) {
        return this.machineDao.findById(machineId);
    }

    @Override
    public MachineFormulaProductBase getMachineFormulaProductBaseLog(Long taskId) {
        return machineFormulaProductBaseDao.findById(taskId);
    }

    @Override
    public Machine updateMachine(Machine machine) {
        Machine dbItem = this.machineDao.findById(machine.getMachineId());
        dbItem.setMinQuantity(machine.getMinQuantity());
        dbItem.setWarningQuantity(machine.getWarningQuantity());
        machineDao.update(dbItem);
        return dbItem;
    }

    @Override
    public Machine saveOrUpdate(Machine machine) {
        if(machine.getMachineId() != null && machine.getMachineId() > 0){
            this.machineDao.update(machine);
        } else {
            this.machineDao.persist(machine);
            List<Colourant> colourants = this.colourantDao.findAll();
            for(Colourant colourant : colourants){
                MachineColourant machineColourant = new MachineColourant();
                machineColourant.setMachine(machine);
                machineColourant.setColourant(colourant);
                machineColourant.setQuantity(0d);
                machineColourant.setRefillFactor(100d);

                this.machineColourantDao.persist(machineColourant);
            }
        }
        return machine;
    }
}
