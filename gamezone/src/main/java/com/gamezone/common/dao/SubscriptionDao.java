package com.gamezone.common.dao;

import org.springframework.stereotype.Repository;

import com.gamezone.common.dao.base.AbstractJpaDAO;
import com.gamezone.common.dao.base.ISubscriptionDao;
import com.gamezone.common.model.Subscription;

@Repository
public class SubscriptionDao extends AbstractJpaDAO<Subscription> implements ISubscriptionDao {
    public SubscriptionDao() {
        super();
        setClazz(Subscription.class);
    }
}
