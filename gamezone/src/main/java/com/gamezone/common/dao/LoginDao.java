package com.gamezone.common.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.gamezone.common.dao.base.AbstractJpaDAO;
import com.gamezone.common.dao.base.ILoginDao;
import com.gamezone.common.model.User;

@Repository
public class LoginDao extends AbstractJpaDAO<User> implements ILoginDao {

    @PersistenceContext
    protected EntityManager entityManager;
    
    public LoginDao() {
        super();
        setClazz(User.class);
    }

	@Override
	public User findByName(String name) {
		TypedQuery<User> findQuery =
  		      entityManager.createQuery("SELECT u FROM User u WHERE u.name = :userName", User.class);
		findQuery.setParameter("userName", name);
		try{
			return findQuery.getSingleResult();
		}catch(Exception e){
			return null;
		}
	}
}
