import model.Department;
import model.Employee;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("testjpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            // **Thêm một đối tượng không có quan hệ**
//            Department dp = new Department();
//            dp.setId(2);
//            dp.setName("Alo");
//            entityManager.persist(dp);

            // **Thêm một đối tượng có quan hệ**
//            Department dp = entityManager.find(Department.class, 2L);
//            Employee employee = new Employee();
//            employee.setId(1);
//            employee.setName("Viet");
//            employee.setIdDepartment(dp);
//
//            entityManager.persist(employee);

            // **Lấy đối tượng**
//            String jpql = "select o from Department o";
//            TypedQuery<Department> query = entityManager.createQuery(jpql, Department.class);
//            List<Department> list = query.getResultList();
//            for (Department dp : list)
//            {
//                System.out.println("Department name: " + dp.getName());
//            }

            // **Lọc đối tượng
//            String jpql = "select o from Department o where o.name=:name";
//            TypedQuery<Department> query = entityManager.createQuery(jpql, Department.class);
//            query.setParameter("name","ACB");
//            List<Department> list = query.getResultList();
//            for (Department dp : list)
//            {
//                System.out.println("Department Id: " + dp.getId());
//            }

            // **Sửa đối tượng
//            Department dp = entityManager.find(Department.class, 3L);
//            dp.setName("ACB3");
//            entityManager.merge(dp);

            // **Xóa đối tượng
            Department dp = entityManager.find(Department.class, 2L);
            entityManager.remove(dp);

            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
