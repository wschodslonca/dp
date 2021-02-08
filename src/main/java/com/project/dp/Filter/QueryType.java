package com.project.dp.Filter;

import com.project.dp.Management.BaseManagement;
import com.project.dp.Management.RoleStrategy;
import com.project.dp.Management.StrategyContext;
import com.project.dp.Management.UserStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeOverride;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class QueryType {

    @PersistenceContext
    EntityManager entityManager;

    @ACLSecu
    public List<Object[]> query(String s, Long user_id) {
        Query q = entityManager.createNativeQuery(s);
        return q.getResultList();
    }
}
