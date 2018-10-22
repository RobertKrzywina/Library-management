package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients", schema = "libraryJPA")
public class Client implements Serializable {
    private static final long serialVersionUID = 101L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "client_id")
    private Long id;
    @Column(name = "client_firstname",
            nullable = false)
    private String firstName;
    @Column(name = "client_lastName",
            nullable = false)
    private String lastName;
    @Column(name = "client_age")
    private int age;
    @Column(name = "client_is_student")
    private boolean isStudent;
    @Column(name = "client_money_debts")
    private double moneyDebts;
    @OneToMany(mappedBy = "client",
               fetch = FetchType.EAGER)
    private List<Book> books;

    public Client() {}

    public Client(String firstName, String lastName, int age, boolean isStudent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.isStudent = isStudent;
        moneyDebts = 0.0;
        books = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public double getMoneyDebts() {
        return moneyDebts;
    }

    public void setMoneyDebts(double moneyDebts) {
        this.moneyDebts = moneyDebts;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", isStudent=" + isStudent +
                ", moneyDebts=" + moneyDebts +
                ", books=" + books +
                '}';
    }
}
