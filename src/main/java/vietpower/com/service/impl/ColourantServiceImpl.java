package vietpower.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.ColourantDao;
import vietpower.com.model.Colourant;
import vietpower.com.service.ColourantService;

import java.util.List;

/**
 * Created by HauKute on 11/25/2018.
 */
@Service("colourantService")
@Transactional
public class ColourantServiceImpl implements ColourantService {
    @Autowired
    private ColourantDao colourantDao;

    @Override
    public List<Colourant> findAll() {
        return colourantDao.findAll();
    }

    @Override
    public void save(Colourant colourant) {
        colourantDao.save(colourant);
    }

    @Override
    public void update(Colourant colourant) {
        colourantDao.update(colourant);
    }
}
