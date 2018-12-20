package vietpower.com.dao.impl;

import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.MachineColourantLogDao;
import vietpower.com.model.MachineColourantLog;

/**
 * Created by HauKute on 12/20/2018.
 */
@Repository("machineColourantLogDao")
public class MachineColourantLogDaoImpl extends AbstractDao<Integer, MachineColourantLog> implements MachineColourantLogDao {
}
