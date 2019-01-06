package vietpower.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietpower.com.dao.ProductBaseCanDao;
import vietpower.com.dao.ProductBaseDao;
import vietpower.com.model.Base;
import vietpower.com.model.ProductBase;
import vietpower.com.model.ProductBaseCan;
import vietpower.com.service.ProductBaseService;

import java.util.*;

/**
 * Created by HauKute on 11/25/2018.
 */
@Service("productBaseService")
@Transactional
public class ProductBaseServiceImpl implements ProductBaseService {
    @Autowired
    private ProductBaseDao productBaseDao;

    @Autowired
    private ProductBaseCanDao productBaseCanDao;

    @Override
    public List<ProductBase> findByProductId(Long productId){
        List<ProductBase>  result = this.productBaseDao.findByProductId(productId);

        return result;
    }

    @Override
    public List<Base> findByProduct(Long productId) {
        List<ProductBase>  result = this.productBaseDao.findByProductId(productId);
        Set<Base> resultBase = new HashSet<>();
        for(ProductBase productBase : result){
            resultBase.add(productBase.getBase());
        }
        return new ArrayList<>(resultBase);
    }
}
