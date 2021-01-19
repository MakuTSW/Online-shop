package org.maku.observer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.maku.QueryBuilderFunction;
import org.maku.QueryBuilderProcedure;
import org.maku.QueryDirector;

import java.util.ArrayList;
import java.util.List;

public class Order implements OrderObserver{
    final private String login;
    final private String productName;
    final private int productId;
    final private int orderId;
    public Order(String login,String productName,int productId,int orderId)
    {
        this.login=login;
        this.productName=productName;
        this.productId=productId;
        this.orderId=orderId;
    }
    @Override
    public boolean sendMail() {
        SessionFactory sf= new Configuration().configure().buildSessionFactory();
        Session session= sf.openSession();
        QueryDirector director= new QueryDirector();
        Transaction t=session.beginTransaction();
        director.setBuilder(new QueryBuilderFunction());
        List<String> listOfNames = new ArrayList<>();
        listOfNames.add("order_id");
        List<String> listOfParametrs = new ArrayList<>();
        listOfParametrs.add(Integer.toString(orderId));
        director.construct(session,"before_realize_order",listOfNames,listOfParametrs);
        int result=(int)director.getQuery().getSingleResult();
        director.setBuilder(new QueryBuilderProcedure());
        director.construct(session,"realize_order",listOfNames,listOfParametrs);
        director.getQuery().executeUpdate();
        System.out.println(orderId);
        if(result == 1) {
            System.out.println("Produkt " + productName + " został zakupiony przez " + login);
            t.commit();
            session.close();
            return true;
        }
        else {
            System.out.println("Produkt " + productName + " niedostępny");
            return false;
        }
    }


    @Override
    public int getOrderId() {
        return orderId;
    }
    @Override
    public boolean equals(int order) {
        return this.orderId == order;
    }
}
