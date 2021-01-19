package org.maku;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public abstract class QueryBuilder {

    public Query getQuery()
    {
        return query;
    }

    public void sql(Session session, String name,List<String>names) {

    }

    public void addParametrs(List<String> names,List<String> paramters){

    }

    protected Query query = null;
}
