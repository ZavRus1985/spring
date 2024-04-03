package org.ruslan.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.ruslan.jpa.config.JpaConfig;

import org.ruslan.jpa.entity.manytomany.Order;
import org.ruslan.jpa.entity.manytomany.Product;
import org.ruslan.jpa.entity.onetomany.Category;
import org.ruslan.jpa.entity.onetomany.Producer;
import org.ruslan.jpa.entity.onetoone.AccountCustomer;
import org.ruslan.jpa.entity.onetoone.Customer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
        EntityManager em = emf.createEntityManager();
//------------------------------------------------------------------------------------
        //  1.   Привести пример каскадного сохранения.

//        em.getTransaction().begin();
//
//        Customer customer1 = new Customer(null, "Anna", "89109005552");
//
//        AccountCustomer accCustomer1 = new AccountCustomer(null, "AnnLogin", "123");
//
//        customer1.setAccountCustomer(accCustomer1);
//        em.persist(customer1);
//
//        em.getTransaction().commit();
//        em.close();

//------------------------------------------------------------------------------------
        //   2.	 Привести пример каскадного обновления.

//        em.getTransaction().begin();
//
//        Customer customer1 = new Customer(null, "Bob", "89109005552");
//
//        AccountCustomer accCustomer1 = new AccountCustomer(null, "BobLogin", "555");
//
//        customer1.setAccountCustomer(accCustomer1);
//        em.merge(customer1);
//
//        em.getTransaction().commit();
//        em.close();

// ----------------------------------------------------------------------------------
        //   3.	 Привести корректный пример каскадного удаления.

//        em.getTransaction().begin();
//
//        Customer customer1 = new Customer(null, "Bob", "89109005552");
//
//        AccountCustomer accCustomer1 = new AccountCustomer(null, "BobLogin", "555");
//
//        customer1.setAccountCustomer(accCustomer1);
//        em.remove(customer1);
//
//        em.getTransaction().commit();
//        em.close();

// ----------------------------------------------------------------------------------
        //  4.  Привести некорректный пример каскадного удаления при связи @ManyToMany.

        /* 1 step добавить записи в БД

        em.getTransaction().begin();

        Product product1 = new Product(null, "product1");
        Product product2 = new Product(null, "product2");
        Product product3 = new Product(null, "product3");
        Product product4 = new Product(null, "product4");
        Product product5 = new Product(null, "product5");
        Product product6 = new Product(null, "product6");

        Order order1 = new Order(null, "order1");
        Order order2 = new Order(null, "order2");

        List<Product> products = new ArrayList<>(List.of(product1, product2, product3, product4, product5, product6));
        products.forEach(em::persist);
        order1.setProducts(List.of(product1, product2));
        order2.setProducts(List.of(product1, product2, product3, product4, product5, product6));

        em.persist(order1);
        em.persist(order2);

        em.getTransaction().commit();
        em.close();

        */
                 //---------------<property name="hibernate.hbm2ddl.auto" value="update"/>---------------------//

        /* 2 step удалились все записи из обеих таблиц

        em.getTransaction().begin();

        Order order = em.find(Order.class,1);
        em.remove(order);

        em.getTransaction().commit();
        em.close();
        */


// ----------------------------------------------------------------------------------
        //  5.  Привести некорректный пример каскадного удаления при связи @OneToMany. Исправить с помощью orphanRemoval.

        /* step 1

        em.getTransaction().begin();

        Product product1 = new Product(null, "product1");
        Product product2 = new Product(null, "product2");
        Product product3 = new Product(null, "product3");

        Category category1 = new Category(null, "category1");

        category1.setProducts(List.of(product1, product2, product3));
        List<Product> products = new ArrayList<>(List.of(product1, product2, product3));
        products.forEach(em::persist);

        em.persist(category1);

        em.getTransaction().commit();
        em.close();

        */

        /* step 2
        @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)
        List<Product> products = new ArrayList<>();

        em.getTransaction().begin();

        Category category = em.find(Category.class,1);
        category.getProducts().remove(0);


        em.getTransaction().commit();
        em.close();

         */
// 6. Даны сущности Отдел и Работник. Связь – один ко многим. Добавить новый отдел с уже существующими работниками
// (сохранить только новый отдел).
// Добавить новых работников в уже существующий отдел (сохранить только новых работников).

        /*  STEP 1

        em.getTransaction().begin();

        Product product1 = new Product(null, "product1");
        Product product2 = new Product(null, "product2");
        Product product3 = new Product(null, "product3");

        Category category1 = new Category(null, "category1");// @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
        category1.setProducts(List.of(product1, product2, product3));

        em.persist(category1);

//Hibernate: insert into categories (name) values (?)
//Hibernate: insert into products (category_id, name, producer_id) values (?, ?, ?)
//Hibernate: insert into products (category_id, name, producer_id) values (?, ?, ?)
//Hibernate: insert into products (category_id, name, producer_id) values (?, ?, ?)

        em.getTransaction().commit();
        em.close();
        */

        /*  STEP 2

        em.getTransaction().begin();

        Product product4 = new Product(null, "product4");
        Category category = em.find(Category.class, 1);

        category.setProducts(List.of(product4));
        em.persist(product4);

//Cannot invoke "org.ruslan.jpa.entity.onetomany.Category.setProducts(java.util.List)" because "category" is null

        em.getTransaction().commit();
        em.close();
        */

    }
}
