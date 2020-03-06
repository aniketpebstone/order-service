package no.tusla.orderservice;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
 	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
	private int id;
 	@Column(name = "user_name", nullable = false)
	private String userName;
	private String password;
	@Column(name = "wallet_balance", nullable = false)
	private int walletBalance;
}
