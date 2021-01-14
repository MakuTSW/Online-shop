package org.maku;

import org.hibernate.Session;

import java.util.Iterator;
import java.util.List;

public class QueryBuilderProcedure extends QueryBuilder{
    @Override
    public void sql(Session session, String name,List<String> names)
    {
        String sql="exec "+name;
        for(int i=0;i<names.size();i++)
        {
            if(i==0)
                sql+=" :";
            else sql+=",:";
            sql+=names.get(i);
        }
        query=session.createSQLQuery(sql);
    }

    @Override
    public void addParametrs(List<String> names,List<String> parametrs)
    {
        for(int i=0;i<names.size();i++)
        {
            query.setParameter(names.get(i),parametrs.get(i));
        }
    }
}
