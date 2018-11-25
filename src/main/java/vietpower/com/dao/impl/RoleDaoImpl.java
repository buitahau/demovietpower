package vietpower.com.dao.impl;

import org.springframework.stereotype.Repository;
import vietpower.com.dao.AbstractDao;
import vietpower.com.dao.RoleDao;
import vietpower.com.model.Role;

/**
 * Created by HauKute on 11/25/2018.
 */
@Repository("roleDao")
public class RoleDaoImpl extends AbstractDao<Integer, Role> implements RoleDao {
}
