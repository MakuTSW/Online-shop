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
        private List<OrderObserver> mailObservers;
        public boolean isWorking=false;

        public OrderManager() {
            mailObservers = new ArrayList<>();
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
                OrderObserver orderObserver=new Order(String.valueOf(obj[0]),String.valueOf(obj[1]),Integer.parseInt(String.valueOf(obj[2])),Integer.parseInt(String.valueOf(obj[3])));
                mailObservers.add(orderObserver);
            }
            t.commit();
        }

        public void subscribe(OrderObserver observers) {
            this.mailObservers.add(observers);
        }

        public void startWork() {
            Thread thread = new Thread(() -> {
                isWorking=true;
                while (Thread.currentThread().isAlive()) {
                    int randomDelay = new Random().nextInt(5000);
                    try {
                        Thread.sleep(randomDelay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (OrderObserver observer : mailObservers) {
                        observer.sendMail();
                    }
                }
                isWorking=false;
            });
            thread.start();
        }

}