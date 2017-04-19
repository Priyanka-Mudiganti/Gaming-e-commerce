package com.gamezone.common.dao.base;

import com.gamezone.common.model.Subscription;

public interface ISubscriptionDao {

    void create(Subscription subscription);
    
    Subscription findById(Long id);
    
    Subscription update(Subscription order);

}
