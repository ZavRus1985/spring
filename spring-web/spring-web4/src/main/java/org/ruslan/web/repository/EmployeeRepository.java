package org.ruslan.web.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.ruslan.web.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public EmployeeRepository(EntityManagerFactory managerFactory) {
        entityManagerFactory = managerFactory;
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }


    public List<Employee> getAllEmployees() {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            List<Employee> employees = entityManager
                    .createQuery("from Employee", Employee.class)
                    .getResultList();

            entityManager.getTransaction().commit();
            return employees;
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

    public Optional<Employee> getEmployeeById(Integer id) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Employee employee = entityManager.find(Employee.class, id);

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
            entityManager = entityManagerFactory.createEntityManager();
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
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Employee updatableEmployee = entityManager.find(Employee.class, id);
            if(updatableEmployee != null){
                updatableEmployee.setName(employee.getName());
                updatableEmployee.setDepartment(employee.getDepartment());
                updatableEmployee.setSalary(employee.getSalary());
            }
            System.out.println(updatableEmployee.getName());
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
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Employee deletedEmployee = entityManager.find(Employee.class, id);
            if (deletedEmployee != null) {
                entityManager.remove(deletedEmployee);
            }

            entityManager.getTransaction().commit();
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
