package com.gamezone.common.dao.base;

import com.gamezone.common.model.PurchaseOrder;

public interface IPurchaseDao {

    void create(PurchaseOrder order);
    
    PurchaseOrder findById(Long id);
    
    PurchaseOrder update(PurchaseOrder order);

}
