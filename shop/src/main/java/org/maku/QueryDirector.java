package org.maku;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class QueryDirector {
    QueryBuilder builder=null;
    public void setBuilder(QueryBuilder b)
    {
        builder=b;
    }
    public Query getQuery()
    {
        return builder.getQuery();
    }
    public void construct(Session session, String functionName, List<String> names, List<String> parametrs)
    {
        builder.sql(session,functionName,names);
        builder.addParametrs(names,parametrs);
    }
}
