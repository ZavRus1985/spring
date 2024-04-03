package org.ruslan.jpa.entity.onetoone;

import jakarta.persistence.*;

@Entity
@Table(name = "account_customers")
public class AccountCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String pass;

    public AccountCustomer() {
    }

    public AccountCustomer(Integer id, String login, String pass) {
        this.id = id;
        this.login = login;
        this.pass = pass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
