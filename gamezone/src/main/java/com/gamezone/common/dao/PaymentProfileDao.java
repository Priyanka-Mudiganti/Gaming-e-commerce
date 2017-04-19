package com.gamezone.common.dao;

import com.gamezone.common.dao.base.AbstractJpaDAO;
import com.gamezone.common.dao.base.IPaymentProfileDao;
import com.gamezone.common.model.PaymentProfile;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentProfileDao extends AbstractJpaDAO<PaymentProfile> implements IPaymentProfileDao {
    public PaymentProfileDao() {
        super();
        setClazz(PaymentProfile.class);
    }
}
