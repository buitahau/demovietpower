package vietpower.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.BaseDao;
import vietpower.com.dao.RoleDao;
import vietpower.com.model.Role;
import vietpower.com.service.RoleService;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
