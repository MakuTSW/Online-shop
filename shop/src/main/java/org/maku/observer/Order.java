package org.maku.observer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.maku.QueryBuilderProcedure;
import org.maku.QueryBuilderView;
import org.maku.QueryDirector;

import java.util.ArrayList;
import java.util.List;

public class Order implements OrderObserver{
    String login;
    String productName;
    int productId;
    int orderId;
    public Order(String login,String productName,int productId,int orderId)
    {
        this.login=login;
        this.productName=productName;
        this.productId=productId;
        this.orderId=orderId;
    }
    @Override
    public void sendMail() {
        SessionFactory sf= new Configuration().configure().buildSessionFactory();
        Session session= sf.openSession();
        QueryDirector director= new QueryDirector();
        Transaction t=session.beginTransaction();
        director.setBuilder(new QueryBuilderProcedure());
        List<String> listOfNames = new ArrayList<>();
        listOfNames.add("order_id");
        List<String> listOfParametrs = new ArrayList<>();
        listOfParametrs.add(Integer.toString(orderId));
        director.construct(session,"realize_order",listOfNames,listOfParametrs);
        if(director.getQuery().executeUpdate()==1)
        System.out.println("Produkt " + productName +" został zakupiony przez " + login);
        else System.out.println("Produkt " + productName + " niedostępny");
        t.commit();
        session.close();
    }

    public int getOrderId() {
        return orderId;
    }
}
