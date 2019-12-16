/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergon.dao;

import com.emergon.entities.Role;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role findRoleByName(String roleName) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Role> q = currentSession.createQuery("from Role where rname=:roleName", Role.class);
        q.setParameter("roleName", roleName);
        Role theRole = null;
        try {
            theRole = q.getSingleResult();
        } catch (Exception e) {
            theRole = null;
        }
        return theRole;
    }

    @Override
    public List<Role> findAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query q = currentSession.createQuery("from Role");
        List<Role> list = q.getResultList();
        return list;
    }

    @Override
    public Role findById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Role role = currentSession.byId(Role.class).load(id);
        return role;
    }

}
