package org.ruslan.web.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeAgregationRepository {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public EmployeeAgregationRepository(EntityManagerFactory managerFactory) {
        entityManagerFactory = managerFactory;
    }
    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public List<Object[]> aggregationByAverageSalary() {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            List<Object[]> averageList = entityManager.createQuery("""
                    SELECT department, AVG(salary) AS average
                    FROM Employee
                    GROUP BY department
                    """, Object[].class).getResultList();
            System.out.println(averageList);
            entityManager.getTransaction().commit();
            return averageList;
        }
        catch (Exception ex) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public List<Object[]> aggregationByMaxSalary() {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            List<Object[]> maxList = entityManager.createQuery("""
                    SELECT department, Max(salary) AS average
                    FROM Employee
                    GROUP BY department
                    """, Object[].class).getResultList();
            System.out.println(maxList);
            entityManager.getTransaction().commit();
            return maxList;
        }
        catch (Exception ex) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public List<Object[]> aggregationByMinSalary() {

        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            List<Object[]> minList = entityManager.createQuery("""
                    SELECT department, Min(salary) AS average
                    FROM Employee
                    GROUP BY department
                    """, Object[].class).getResultList();
            System.out.println(minList);
            entityManager.getTransaction().commit();
            return minList;
        }
        catch (Exception ex) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public List<Object[]> aggregationBySumSalary() {

        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            List<Object[]> sumList = entityManager.createQuery("""
                    SELECT department, Sum(salary) AS average
                    FROM Employee
                    GROUP BY department
                    """, Object[].class).getResultList();
            System.out.println(sumList);
            entityManager.getTransaction().commit();
            return sumList;
        }
        catch (Exception ex) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
