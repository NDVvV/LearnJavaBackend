package S.A101.model;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private String firstName;
    private String lastName;

//    @ManyToOne
//    private Department idDepartment;

    public Employee() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    public Department getIdDepartment() {
//        return idDepartment;
//    }
//
//    public void setIdDepartment(Department idDepartment) {
//        this.idDepartment = idDepartment;
//    }
}
