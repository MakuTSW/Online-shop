package org.maku.observer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.maku.QueryBuilderView;
import org.maku.QueryDirector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class OrderManager {
        private List<Order> mailObservers;
        public boolean isWorking=false;

        public OrderManager() {
            mailObservers = new ArrayList<>();
            checkOrders();
        }

        private void checkOrders()
        {
            SessionFactory sf= new Configuration().configure().buildSessionFactory();
            Session session= sf.openSession();
            QueryDirector director= new QueryDirector();
            Transaction t=session.beginTransaction();
            director.setBuilder(new QueryBuilderView());
            List<String> listOfNames = new ArrayList<>();
            listOfNames.add("user_login");
            listOfNames.add("name");
            listOfNames.add("product_id");
            listOfNames.add("order_id");
            director.construct(session,"not_realized_orders",listOfNames,null);

            List<Object> objectList = (List<Object>) director.getQuery().getResultList();
            Iterator itr = objectList.iterator();
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                Order orderObserver=new Order(String.valueOf(obj[0]),String.valueOf(obj[1]),Integer.parseInt(String.valueOf(obj[2])),Integer.parseInt(String.valueOf(obj[3])));
                boolean flag=true;
                for(Order o:mailObservers)
                {
                    if(o.equals(orderObserver.getOrderId())) {
                        flag = false;
                        break;
                    }
                }
                if(flag)
                mailObservers.add(orderObserver);
            }
            t.commit();
            session.close();
        }



        public void startWork() {
            Thread thread = new Thread(() -> {
                isWorking=true;

                while (Thread.currentThread().isAlive()) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    checkOrders();
                    List <OrderObserver> list = new ArrayList<>();
                    for (OrderObserver observer : mailObservers) {
                        if(observer.sendMail()) {
                            list.add(observer);
                        }
                    }
                    for (OrderObserver observer : list) {
                        System.out.println(observer);
                        if(mailObservers.remove(observer)) System.out.println("Delete order from list");
                        else System.out.println("Not delete order from list :c");
                    }
                    //if(mailObservers.size()==0)Thread.currentThread().stop();
                }
                isWorking=false;
            });
            thread.start();
        }

}