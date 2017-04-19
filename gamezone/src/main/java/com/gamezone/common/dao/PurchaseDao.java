package com.gamezone.common.dao;

import org.springframework.stereotype.Repository;

import com.gamezone.common.dao.base.AbstractJpaDAO;
import com.gamezone.common.dao.base.IPurchaseDao;
import com.gamezone.common.model.PurchaseOrder;

@Repository
public class PurchaseDao extends AbstractJpaDAO<PurchaseOrder> implements IPurchaseDao {
    public PurchaseDao() {
        super();
        setClazz(PurchaseOrder.class);
    }
}
