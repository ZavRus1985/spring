package org.ruslan.springbootplusspringdatajpa.repository;

import org.ruslan.springbootplusspringdatajpa.entity.Employee;
import org.ruslan.springbootplusspringdatajpa.model.EmployeeAgregation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface EmployeeAgregationRepository extends JpaRepository<Employee, Integer> {

    @Query(
            """
                select new org.ruslan.springbootplusspringdatajpa.model.EmployeeAgregation(e.department, avg(e.salary))
                from Employee e
                group by e.department
            """)
    List<EmployeeAgregation> findAllAverageBySalary();

    @Query(
            """
                select new org.ruslan.springbootplusspringdatajpa.model.EmployeeAgregation(e.department, cast( max(e.salary) as double ))
                from Employee e
                group by e.department
            """)
    List<EmployeeAgregation> findAllAverageByMax();

    @Query(
            """
                select new org.ruslan.springbootplusspringdatajpa.model.EmployeeAgregation(e.department, cast( min (e.salary) as double ))
                from Employee e
                group by e.department
            """)
    List<EmployeeAgregation> findAllAverageByMin();

    @Query(
            """
                select new org.ruslan.springbootplusspringdatajpa.model.EmployeeAgregation(e.department, cast( sum (e.salary) as double ))
                from Employee e
                group by e.department
            """)
    List<EmployeeAgregation> findAllAverageBySum();

//    public List<Object[]> aggregationByAverageSalary() {
//        EntityManager entityManager = null;
//        try {
//            entityManager = entityManagerFactory.createEntityManager();
//            entityManager.getTransaction().begin();
//
//            List<Object[]> averageList = entityManager.createQuery("""
//                    SELECT department, AVG(salary) AS average
//                    FROM Employee
//                    GROUP BY department
//                    """, Object[].class).getResultList();
//            System.out.println(averageList);
//            entityManager.getTransaction().commit();
//            return averageList;
//        }
//        catch (Exception ex) {
//            if (entityManager != null) {
//                entityManager.getTransaction().rollback();
//            }
//            throw new RuntimeException(ex);
//        }
//        finally {
//            if (entityManager != null) {
//                entityManager.close();
//            }
//        }
//    }
//
//    public List<Object[]> aggregationByMaxSalary() {
//        EntityManager entityManager = null;
//        try {
//            entityManager = entityManagerFactory.createEntityManager();
//            entityManager.getTransaction().begin();
//
//            List<Object[]> maxList = entityManager.createQuery("""
//                    SELECT department, Max(salary) AS average
//                    FROM Employee
//                    GROUP BY department
//                    """, Object[].class).getResultList();
//            System.out.println(maxList);
//            entityManager.getTransaction().commit();
//            return maxList;
//        }
//        catch (Exception ex) {
//            if (entityManager != null) {
//                entityManager.getTransaction().rollback();
//            }
//            throw new RuntimeException(ex);
//        }
//        finally {
//            if (entityManager != null) {
//                entityManager.close();
//            }
//        }
//    }
//
//    public List<Object[]> aggregationByMinSalary() {
//
//        EntityManager entityManager = null;
//        try {
//            entityManager = entityManagerFactory.createEntityManager();
//            entityManager.getTransaction().begin();
//
//            List<Object[]> minList = entityManager.createQuery("""
//                    SELECT department, Min(salary) AS average
//                    FROM Employee
//                    GROUP BY department
//                    """, Object[].class).getResultList();
//            System.out.println(minList);
//            entityManager.getTransaction().commit();
//            return minList;
//        }
//        catch (Exception ex) {
//            if (entityManager != null) {
//                entityManager.getTransaction().rollback();
//            }
//            throw new RuntimeException(ex);
//        }
//        finally {
//            if (entityManager != null) {
//                entityManager.close();
//            }
//        }
//    }
//
//    public List<Object[]> aggregationBySumSalary() {
//
//        EntityManager entityManager = null;
//        try {
//            entityManager = entityManagerFactory.createEntityManager();
//            entityManager.getTransaction().begin();
//
//            List<Object[]> sumList = entityManager.createQuery("""
//                    SELECT department, Sum(salary) AS average
//                    FROM Employee
//                    GROUP BY department
//                    """, Object[].class).getResultList();
//            System.out.println(sumList);
//            entityManager.getTransaction().commit();
//            return sumList;
//        }
//        catch (Exception ex) {
//            if (entityManager != null) {
//                entityManager.getTransaction().rollback();
//            }
//            throw new RuntimeException(ex);
//        }
//        finally {
//            if (entityManager != null) {
//                entityManager.close();
//            }
//        }
//    }
}
