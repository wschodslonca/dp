package com.project.dp.Filter;

import org.springframework.stereotype.Component;
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
