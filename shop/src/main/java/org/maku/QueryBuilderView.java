package org.maku;

import org.hibernate.Session;

import java.util.Iterator;
import java.util.List;

public class QueryBuilderView extends QueryBuilder{
    @Override
    public void sql(Session session, String name,List<String> names)
    {
        String sql="select ";
        for(int i=0;i<names.size();i++)
        {
            if(i!=0)
                sql+=", ";
            sql+=names.get(i);
        }
        sql+=" from " + name;
        query=session.createSQLQuery(sql);
    }
}

