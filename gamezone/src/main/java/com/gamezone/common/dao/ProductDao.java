package com.gamezone.common.dao;

import com.gamezone.common.dao.base.AbstractJpaDAO;
import com.gamezone.common.dao.base.IProductDao;
import com.gamezone.common.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao extends AbstractJpaDAO<Product> implements IProductDao {
    public ProductDao() {
        super();
        setClazz(Product.class);
    }
}
