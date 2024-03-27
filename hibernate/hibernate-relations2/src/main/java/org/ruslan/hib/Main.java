package org.ruslan.hib;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ruslan.hib.config.AppConfig;
import org.ruslan.hib.entity.manytomany.Order;
import org.ruslan.hib.entity.manytomany.Product;
import org.ruslan.hib.entity.onetomany.Category;
import org.ruslan.hib.entity.onetomany.Producer;
import org.ruslan.hib.entity.onetoone.Customer;
import org.ruslan.hib.entity.onetoone.AccountCustomer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        SessionFactory sessionFactory = context.getBean(SessionFactory.class);
        Session session = sessionFactory.getCurrentSession();

        session.getTransaction().begin();
//-------------------------------------------------------------------------------------------------------
//        Customer customer1 = new Customer(null, "Anna", "89109005552");
//        Customer customer2 = new Customer(null, "Bob", "89169084321");
//        Customer customer3 = new Customer(null, "Joe", "89129099876");
//
//        AccountCustomer accCustomer1 = new AccountCustomer(null, "AnnLogin", "123");
//        AccountCustomer accCustomer2 = new AccountCustomer(null, "BobLogin", "345");
//        AccountCustomer acctCustomer3 = new AccountCustomer(null, "JoeLogin", "567");
//
//        accCustomer1.setCustomer(customer1);
//        session.persist(accCustomer1);
//
//        accCustomer2.setCustomer(customer2);
//        session.persist(accCustomer2);
//
//        acctCustomer3.setCustomer(customer3);
//        session.persist(acctCustomer3);

 //       session.remove(customer1);
//-------------------------------------------------------------------------------------------------------
//        Producer producer1 = new Producer(null, "factory1");
//        Producer producer2 = new Producer(null, "factory2");
//        Producer producer3 = new Producer(null, "factory3");
//
//        Category category1 = new Category(null, "category1");
//        Category category2 = new Category(null, "category2");

//-------------------------------------------------------------------------------------------------------

//        Product product1 = new Product(null, "product1");
//        Product product2 = new Product(null, "product2");
//        Product product3 = new Product(null, "product3");
//        Product product4 = new Product(null, "product4");
//        Product product5 = new Product(null, "product5");
//        Product product6 = new Product(null, "product6");
//
//        Order order1 = new Order(null, "order1");
//        Order order2 = new Order(null, "order2");
//
//        List<Product> products = new ArrayList<>(List.of(product1, product2, product3, product4, product5, product6));
//        products.forEach(session::persist);

//-------------------------------------------------------------------------------------------------------
//        producer1.setProducts(List.of(product1, product2));
//        producer2.setProducts(List.of(product3, product4));
//        producer3.setProducts(List.of(product5, product6));
//
//        session.persist(producer1);
//        session.persist(producer2);
//        session.persist(producer3);
    //------------------------------------------------------
//        category1.setProducts(List.of(product1, product2));
//        category2.setProducts(List.of(product3, product4, product5, product6));
//
//        session.persist(category1);
//        session.persist(category2);
    //------------------------------------------------------
//        order1.setProducts(List.of(product1, product2));
//        order2.setProducts(List.of(product3, product4, product5, product6));
//
//        session.persist(order1);
//        session.persist(order2);

//8.	Протестировать LAZY загрузку.
//        Producer producer = session.get(Producer.class, 1);
 //       System.out.println("\n!!!!!!!!" + producer + "\n");

//-------------------------------------------------------------------------------------------------------
//9.	Протестировать LAZY загрузку на примере выгрузки только дочерней сущности. В чем проблема?
//        System.out.println(producer.getProducts());
/*
Hibernate:
    select
        p1_0.`producer_id`,
        p1_0.`name`
    from
        `producers` p1_0
    where
        p1_0.`producer_id`=?
Hibernate:
    select
        p1_0.`producer_id`,
        p1_0.`product_id`,
        c1_0.`category_id`,
        c1_0.`name`,
        p1_0.`name`
    from
        `products` p1_0
    left join
        `categories` c1_0
            on c1_0.`category_id`=p1_0.`category_id`
    where
        p1_0.`producer_id`=?
 */

//        Customer customer = session.get(Customer.class, 1); // oneToOne
//        System.out.println(customer.getAccountCustomer());
        /*
        Hibernate:
    select
        c1_0.`customer_id`,
        a1_0.`account_id`,
        a1_0.`login`,
        a1_0.`pass`,
        c1_0.`name`,
        c1_0.`phone`
    from
        `customers` c1_0
    left join
        `account_customers` a1_0
            on a1_0.`account_id`=c1_0.`account_id`
    where
        c1_0.`customer_id`=?
        */

//    10.	Привести пример с @ManyToMany, в котором возникают проблемы с EAGER загрузкой.
        Product product = session.get(Product.class, 1);
        System.out.println(product.getCategory());
/*
Hibernate:
    select
        p1_0.`product_id`,
        c1_0.`category_id`,
        c1_0.`name`,
        p1_0.`name`,
        o1_0.`product_id`,
        o1_1.`order_id`,
        c2_0.`customer_id`,
        c2_0.`account_id`,
        c2_0.`name`,
        c2_0.`phone`,
        o1_1.`name`,
        p2_0.`producer_id`,
        p2_0.`name`
    from
        `products` p1_0
    left join
        `categories` c1_0
            on c1_0.`category_id`=p1_0.`category_id`
    left join
        (`products_orders` o1_0
    join
        `orders` o1_1
            on o1_1.`order_id`=o1_0.`order_id`)
                on p1_0.`product_id`=o1_0.`product_id`
        left join
            `customers` c2_0
                on c2_0.`customer_id`=o1_1.`customer_id`
        left join
            `producers` p2_0
                on p2_0.`producer_id`=p1_0.`producer_id`
        where
            p1_0.`product_id`=?
 */



        session.getTransaction().commit();
        context.close();
    }
}
