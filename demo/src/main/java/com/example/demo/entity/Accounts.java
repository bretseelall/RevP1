package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="account")
public class Accounts {

    // Creating Id for accounts when created
    @Column(name="accountId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountId;

    // Username column for users, must be UNIQUE and not blank
    private String username;

    // Password column must not be blank
    private String password;

    // Column to check if a user is a manager, will store a boolean
    private Boolean manager = false;


    /* Here are the different constructors for creating  */
    public Accounts(){

    }

    public Accounts(String username, String password){
        this.username = username;
        this.password = password;
    }

    public Accounts(String username, String password, Boolean manager){
        this.username = username;
        this.password = password;
        this.manager = manager;
    }

    public Accounts(Integer accountId, String username, String password){
        this.accountId = accountId;
        this.username = username;
        this.password = password;
    }

    // Getter for account Id
    public Integer getAccountId(){
        return accountId;
    }

    // Setter for account Id
    public void setAccountId(Integer accountId){
        this.accountId = accountId;
    }

    // Getter for username
    public String getUsername(){
        return username;
    }

    // Setter for username
    public void setUsername(String username){
        this.username = username;
    }

    // Getter for password
    public String getPassword(){
        return password;
    }

    // Setter for password
    public void setPassword(String password){
        this.password = password;
    }

    // Getter for manager
    public Boolean getManager(){
        return manager;
    }

    // Setter for manager
    public void setManager(Boolean manager){
        this.manager = manager;
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Accounts other = (Accounts) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
