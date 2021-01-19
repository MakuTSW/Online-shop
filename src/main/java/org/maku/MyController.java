package org.maku;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.maku.classes.Product;
import org.maku.observer.OrderManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Controller
public class MyController {

    OrderManager manager = new OrderManager();

    @RequestMapping("/add")
    public ModelAndView singUp(@RequestParam("login") String login,@RequestParam("password") String password,@RequestParam("email") String email)
    {
        observer();
        SessionFactory sf= new Configuration().configure().buildSessionFactory();
        Session session= sf.openSession();
        QueryDirector director= new QueryDirector();
        Transaction t=session.beginTransaction();

        director.setBuilder(new QueryBuilderFunction());
        List<String> listOfParameters= new ArrayList<>();
        listOfParameters.add(login);
        listOfParameters.add(password);
        listOfParameters.add(email);
        List<String> listOfNames= new ArrayList<>();
        listOfNames.add("login");
        listOfNames.add("password");
        listOfNames.add("email");

        director.construct(session,"function_add_user",listOfNames,listOfParameters);

        int x=(int)director.getQuery().getSingleResult();

        String str;
        if(x==0) {
            director.setBuilder(new QueryBuilderProcedure());
            director.construct(session,"add_user",listOfNames,listOfParameters);
            director.getQuery().executeUpdate();
            str="Utworzono konto";
        }
        else
        {
            if(x==1) str="Istnieje konto o podanym loginie";
            else str="istnieje konto o podanym emailu";
        }
        t.commit();
        session.close();

        System.out.println("rejestracja");
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("resultReg",str);
        return  modelAndView;
    }
    @RequestMapping("/login")
    public ModelAndView singIn(@RequestParam("login") String login,@RequestParam("password") String password,HttpServletRequest request)
    {
        observer();
        SessionFactory sf= new Configuration().configure().buildSessionFactory();
        Session session= sf.openSession();
        QueryDirector director=new QueryDirector();
        Transaction t=session.beginTransaction();

        /*String sql= "select dbo.signin (:login,:password)";
        Query query=session.createSQLQuery(sql);
        query.setParameter("login",login);
        query.setParameter("password",password);*/

        director.setBuilder(new QueryBuilderFunction());
        List<String> listOfParameters= new ArrayList<>();
        listOfParameters.add(login);
        listOfParameters.add(password);
        List<String> listOfNames= new ArrayList<>();
        listOfNames.add("login");
        listOfNames.add("password");
        director.construct(session,"signin",listOfNames,listOfParameters);
        int x=(int)director.getQuery().getSingleResult();

        System.out.println("logowanie");
        ModelAndView modelAndView= new ModelAndView();
        String str;
        if(x==1)
        {
            request.getSession().setAttribute("user",login);
            displayProducts(request);
            modelAndView=displayProducts(request);
            str="Witaj " + login + "!";
            listOfNames=new ArrayList<>();
            listOfNames.add("login");
            listOfParameters=new ArrayList<>();
            listOfParameters.add(login);
            director.setBuilder(new QueryBuilderProcedure());
            director.construct(session,"sign_in",listOfNames,listOfParameters);
            director.getQuery().executeUpdate();
            t.commit();
            session.close();
        }else {
            modelAndView.setViewName("index");
            if(x==0)str="Nie istnieje konto o podanym loginie";
            else str="Błędne hasło";
        }
        modelAndView.addObject("resultLog", str);
        //modelAndView.addObject("result","<script>location.href=\"http://\"+location.hostname+\":8081/shop/\";</script>");
        return  modelAndView;
    }
    @RequestMapping("toAddProduct")
    public ModelAndView toAddProduct()
    {
        SessionFactory sf= new Configuration().configure().buildSessionFactory();
        Session session= sf.openSession();
        QueryDirector director=new QueryDirector();
        Transaction t=session.beginTransaction();
        ModelAndView modelAndView=new ModelAndView();

        List<String> listOfNames = new ArrayList<>();
        listOfNames.add("name");
        director.setBuilder(new QueryBuilderView());
        director.construct(session,"categories_view",listOfNames,null);

        List<Object> listOfCategories = (List<Object>) director.getQuery().getResultList();
        Iterator itr = listOfCategories.iterator();
        String  categories="";
        while(itr.hasNext()){
            String obj = String.valueOf(itr.next());
            categories+="<option value=\""+ obj + "\">" + obj +"</option>";
        }
        modelAndView.addObject("categories",categories);


        director.construct(session,"suppliers_view",listOfNames,null);
        List<Object> listOfSuppliers = (List<Object>) director.getQuery().getResultList();
        itr = listOfSuppliers.iterator();
        String  suppliers="";
        while(itr.hasNext()){
            String obj = String.valueOf(itr.next());
            suppliers+="<option value=\""+ obj + "\">" + obj +"</option>";
        }
        System.out.println(suppliers);
        modelAndView.addObject("suppliers",suppliers);

        t.commit();
        modelAndView.setViewName("AddingProduct");
        return modelAndView;
    }
    @RequestMapping("toAddSupplier")
    public ModelAndView toAddSupplier()
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("AddingSupplier");
        return modelAndView;
    }
    @RequestMapping("toAddCategory")
    public ModelAndView toAddCategory()
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("AddingCategory");
        return modelAndView;
    }
    @RequestMapping("toAddEmployee")
    public ModelAndView toAddEmployee()
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("AddingEmployee");
        return modelAndView;
    }
    @RequestMapping("addProduct")
    public ModelAndView addProduct(@RequestParam("product") String product,@RequestParam("category") String category,@RequestParam("supplier") String supplier,@RequestParam("unitPrice") String unitPrice,@RequestParam("unitAmount") String unitAmount,HttpServletRequest request)
    {
        observer();
        SessionFactory sf= new Configuration().configure().buildSessionFactory();
        Session session= sf.openSession();
        QueryDirector director=new QueryDirector();
        Transaction t=session.beginTransaction();

        System.out.println("dodawanie produktu");
        director.setBuilder(new QueryBuilderProcedure());
        List<String> listOfParameters= new ArrayList<>();
        listOfParameters.add(product);
        listOfParameters.add(category);
        listOfParameters.add(supplier);
        listOfParameters.add(unitPrice);
        listOfParameters.add(unitAmount);
        List<String> listOfNames= new ArrayList<>();
        listOfNames.add("product");
        listOfNames.add("category");
        listOfNames.add("supplier");
        listOfNames.add("unitPrice");
        listOfNames.add("unitAmount");
        director.construct(session,"add_product",listOfNames,listOfParameters);
        director.getQuery().executeUpdate();
        t.commit();
        session.close();
        return displayProducts(request);
    }
    @RequestMapping("addSupplier")
    public ModelAndView addSupplier(@RequestParam("name") String name,@RequestParam("country") String country,@RequestParam("city") String city,@RequestParam("phone") String phone,@RequestParam("email") String email,HttpServletRequest request)
    {
        observer();
        SessionFactory sf= new Configuration().configure().buildSessionFactory();
        Session session= sf.openSession();
        QueryDirector director=new QueryDirector();
        Transaction t=session.beginTransaction();

        System.out.println("dodawanie produktu");
        director.setBuilder(new QueryBuilderProcedure());
        List<String> listOfParameters= new ArrayList<>();
        listOfParameters.add(name);
        listOfParameters.add(country);
        listOfParameters.add(city);
        listOfParameters.add(phone);
        listOfParameters.add(email);
        List<String> listOfNames= new ArrayList<>();
        listOfNames.add("name");
        listOfNames.add("country");
        listOfNames.add("city");
        listOfNames.add("phone");
        listOfNames.add("email");
        director.construct(session,"add_supplier",listOfNames,listOfParameters);
        director.getQuery().executeUpdate();
        t.commit();
        session.close();
        return displayProducts(request);
    }
    @RequestMapping("addCategory")
    public ModelAndView addCategory(@RequestParam("name") String name,@RequestParam("description") String description,HttpServletRequest request)
    {
        observer();
        SessionFactory sf= new Configuration().configure().buildSessionFactory();
        Session session= sf.openSession();
        QueryDirector director=new QueryDirector();
        Transaction t=session.beginTransaction();

        System.out.println("dodawanie produktu");
        director.setBuilder(new QueryBuilderProcedure());
        List<String> listOfParameters= new ArrayList<>();
        listOfParameters.add(name);
        listOfParameters.add(description);
        List<String> listOfNames= new ArrayList<>();
        listOfNames.add("name");
        listOfNames.add("description");
        director.construct(session,"add_category",listOfNames,listOfParameters);
        director.getQuery().executeUpdate();
        t.commit();
        session.close();
        return displayProducts(request);
    }
    @RequestMapping("addEmployee")
    public ModelAndView addEmployee(@RequestParam("login") String login,@RequestParam("password") String password,@RequestParam("email") String email,@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("salary") String salary,HttpServletRequest request)
    {
        observer();
        SessionFactory sf= new Configuration().configure().buildSessionFactory();
        Session session= sf.openSession();
        QueryDirector director=new QueryDirector();
        Transaction t=session.beginTransaction();

        System.out.println("dodawanie produktu");
        director.setBuilder(new QueryBuilderProcedure());
        List<String> listOfParameters= new ArrayList<>();
        listOfParameters.add(login);
        listOfParameters.add(password);
        listOfParameters.add(email);
        listOfParameters.add(firstName);
        listOfParameters.add(lastName);
        listOfParameters.add(salary);
        List<String> listOfNames= new ArrayList<>();
        listOfNames.add("login");
        listOfNames.add("password");
        listOfNames.add("email");
        listOfNames.add("firstName");
        listOfNames.add("lastName");
        listOfNames.add("salary");
        director.construct(session,"add_employee",listOfNames,listOfParameters);
        director.getQuery().executeUpdate();
        t.commit();
        session.close();
        return displayProducts(request);
    }

    @RequestMapping("showProduct")
    public ModelAndView showProduct(@RequestParam("id") int id,HttpServletRequest request)
    {
        observer();
        request.getSession().setAttribute("product",id);
        SessionFactory sf= new Configuration().configure().buildSessionFactory();
        Session session= sf.openSession();
        QueryDirector director=new QueryDirector();
        Transaction t=session.beginTransaction();

        List<String> listOfNames = new ArrayList<>();
        listOfNames.add("product_id");
        List<String> listOfParametrs = new ArrayList<>();
        listOfParametrs.add(Integer.toString(id));
        director.setBuilder(new QueryBuilderTableValuedFunction());
        director.construct(session,"product_view_with_details",listOfNames,listOfParametrs);


        Object[] obj = (Object[])  director.getQuery().getSingleResult();
        String name=String.valueOf(obj[0]);
        String unit_price=String.valueOf(obj[1]);
        String category_name=String.valueOf(obj[2]);
        String category_description=String.valueOf(obj[3]);
        String unit_amount=String.valueOf(obj[4]);
        String company_name=String.valueOf(obj[5]);
        String country=String.valueOf(obj[6]);
        String city=String.valueOf(obj[7]);



        Integer.parseInt(String.valueOf(obj[1]));

        String str="<span>Nazwa produktu: " + name + "</span><br />"
                + "<span>Cena za jednostkę: " + unit_price + "</span><br />"
                + "<span>Nazwa kategorii: " + category_name + "</span><br />";
                if(category_description!=null)str+= "<span>Opis kategorii: " + category_description + "</span><br />";
                str+= "<span>Ilość produktów w magazynie: " + unit_amount + "</span><br />"
                + "<span>Nazwa dostawcy: " + company_name + "</span><br />";
                if(country!=null && city!=null) str+= "<span>Pochodzenie: " + country + ", " + city + "</span><br />";
        t.commit();
        session.close();

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("product",str);
        modelAndView.setViewName("productWithDetails");
        return modelAndView;

    }

    @RequestMapping("addOrder")
    public ModelAndView addOrder(HttpServletRequest request,@RequestParam("unitAmount") String unitAmount)
    {
        observer();
        int productId=(int)request.getSession().getAttribute("product");
        String user=(String)request.getSession().getAttribute("user");
        if(user==null)return displayProducts(request);
        SessionFactory sf= new Configuration().configure().buildSessionFactory();
        Session session= sf.openSession();
        QueryDirector director=new QueryDirector();
        Transaction t=session.beginTransaction();

        List<String> listOfParameters=new ArrayList<>();
        List<String> listOfNames = new ArrayList<>();
        listOfNames.add("product_id");
        listOfNames.add("unit_amount");
        listOfParameters.add(Integer.toString(productId));
        listOfParameters.add(unitAmount);
        director.setBuilder(new QueryBuilderFunction());
        director.construct(session,"before_add_order",listOfNames,listOfParameters);
        int result = (int)director.getQuery().getSingleResult();


        director.setBuilder(new QueryBuilderProcedure());
        listOfNames.add("user_login");
        listOfParameters.add(user);
        director.construct(session,"add_order",listOfNames,listOfParameters);
        director.getQuery().executeUpdate();
        t.commit();
        session.close();
        ModelAndView modelAndView = displayProducts(request);
        if(result==1) modelAndView.addObject("orderMessage","<br>Zakupiono produkt");
        else modelAndView.addObject("orderMessage","<br>Zamówiono produkt");
        return modelAndView;
    }

    public ModelAndView displayProducts(HttpServletRequest request)
    {
        HttpSession httpSession=request.getSession();
        String login = (String)httpSession.getAttribute("user");
        System.out.println("zalogowany: " + login);
        SessionFactory sf= new Configuration().configure().buildSessionFactory();
        Session session= sf.openSession();
        QueryDirector director=new QueryDirector();
        Transaction t=session.beginTransaction();
        ModelAndView modelAndView=new ModelAndView();

        List<String> listOfParameters;
        List<String> listOfNames = new ArrayList<>();
        listOfNames.add("name");
        listOfNames.add("unit_price");
        listOfNames.add("category_name");
        listOfNames.add("unit_amount");
        listOfNames.add("product_id");
        director.setBuilder(new QueryBuilderView());
        director.construct(session,"products_view",listOfNames,null);

        List<Object> products = (List<Object>) director.getQuery().getResultList();
        Iterator itr = products.iterator();
        String result="<table> <tr> "
                + "<th> Nazwa </th>"
                + "<th> Cena (zł) </th>"
                + "<th> Nazwa kategorii </th>"
                + "<th> Ilość produktów (szt) </th> "
                + "<th> Szczegóły </th> </tr>";
        Product product=new Product();
        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();
            product.setName(String.valueOf(obj[0]));
            product.setUnitprice(Integer.parseInt(String.valueOf(obj[1])));
            product.setCategoryName(String.valueOf(obj[2]));
            product.setUnitAmount(Integer.parseInt(String.valueOf(obj[3])));
            if(obj[4]!=null)product.setProductId(Integer.parseInt(String.valueOf(obj[4])));
            result+=product.toString();
        }
        result+="</table>";
        //System.out.println(result);
        modelAndView.addObject("result",result);
        modelAndView.setViewName("afterSignIn");
        if(login==null)return modelAndView.addObject("resultLog","Nie jeteś zalogowany");

        listOfNames=new ArrayList<>();
        listOfNames.add("login");
        listOfParameters=new ArrayList<>();
        listOfParameters.add(login);
        director.setBuilder(new QueryBuilderFunction());
        director.construct(session,"show_role",listOfNames,listOfParameters);
        int role=(int) director.getQuery().getSingleResult();
        System.out.println("Role of "+ login + ": " + role);
        if(role>0)
        {
            String str="<div style=\"float: right\"><form action=\"toAddProduct\"><input type=\"submit\" value=\"Dodaj produkt\"></form></div>" +
                    "<div style=\"float: right\"><form action=\"toAddCategory\"><input type=\"submit\" value=\"Dodaj kategorię\"></form></div>" +
                    "<div style=\"float: right\"><form action=\"toAddSupplier\"><input type=\"submit\" value=\"Dodaj dostawcę\"></form></div>";
                    if(role>1) str+="<div style=\"float: right\"><form action=\"toAddEmployee\"><input type=\"submit\" value=\"Dodaj pracownika\"></form></div>";
                    str+="<div style=\"clear:both\"></div>";
            modelAndView.addObject("addProduct",str);
        }
        t.commit();
        session.close();
        modelAndView.addObject("resultLog","Zalogowany jako " + login);
        return modelAndView;
    }

    public void observer()
    {
        if(!manager.isWorking)manager.startWork();
    }
}
