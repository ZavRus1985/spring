package org.ruslan.web.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.ruslan.web.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository {

    private final EntityManagerFactory emf;

    @Autowired
    public EmployeeRepository(EntityManagerFactory managerFactory) {
        emf = managerFactory;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public List<Employee> getAllEmployees() {
        EntityManager entityManager = null;
        try {
            entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();

            List<Employee> employees = entityManager
                    .createQuery("from Employee", Employee.class)
                    .getResultList();

            entityManager.getTransaction().commit();
            return employees;
        } catch (Exception ex) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public Optional<Employee> getEmployeeById(Integer id) {
        EntityManager entityManager = null;
        try {
            entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();

            Employee employee = entityManager.createQuery(
                            """
                               select e from Employee e                               
                               where e.id = :id
                               """, Employee.class)
                    .setParameter("id", id)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            return Optional.ofNullable(employee);
        } catch (Exception ex) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public void saveEmployee(Employee employee) {
        EntityManager entityManager = null;
        try {
            entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();

            if (employee.getId() == null) {
                entityManager.persist(employee);
            } else {
                entityManager.merge(employee);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public void updateOrder(Employee employee, Integer id) {
        EntityManager entityManager = null;
        try {
            entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();

            Employee updatableEmployee = entityManager.find(Employee.class, id);
            if(updatableEmployee != null){
                updatableEmployee.setName(employee.getName());
                updatableEmployee.setDepartment(employee.getDepartment());
                updatableEmployee.setSalary(employee.getSalary());
            }

            entityManager.getTransaction().commit();
        }
        catch (Exception ex) {
            if(entityManager != null){
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

    public void deleteEmployee(Integer id) {
        EntityManager entityManager = null;
        try {
            entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();

            Employee deletedEmployee = entityManager.find(Employee.class, id);
            if (deletedEmployee != null) {
                entityManager.remove(deletedEmployee);
            }

            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public List<Object[]> aggregationByAverageSalary() {
        EntityManager entityManager = null;
        try {
            entityManager = emf.createEntityManager();
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


}
