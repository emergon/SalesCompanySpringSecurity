package com.emergon.dao;

import com.emergon.entities.Role;
import com.emergon.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findByUsername(String username) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<User> q = currentSession.createQuery("from User where username=:username", User.class);
        q.setParameter("username", username);
        User user = null;
        try {
            user = q.getSingleResult();
        } catch (Exception e) {
            user = null;
        }
        return user;
    }

    @Override
    public void save(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(user);
    }

}
