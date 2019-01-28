package vietpower.com.dao.impl;

import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.RoleDao;
import vietpower.com.model.Role;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Repository("roleDao")
public class RoleDaoImpl extends AbstractDao<Integer, Role> implements RoleDao {
    @Override
    public List<Role> findAll() {
        List<Role> listRole = (List<Role>) createEntityCriteria().list();
        return listRole;
    }
}
