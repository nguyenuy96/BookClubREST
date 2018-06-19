package model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "user")

public class User {
	/*******************************************************/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private int userId;

	/*******************************************************/

	@Column(name = "username")
	private String username;

	/*******************************************************/

	@Column(name = "password")
	private String password;

	/*******************************************************/
	
	@Column(name = "fullname")
	private String fullname;
	
	/*******************************************************/

	@Column(name = "address")
	private String address;
	
	/*******************************************************/
	
	@JsonManagedReference
	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Book> books;
	
	/*******************************************************/
	
	public User() {
	}

	public User(int userId) {
		this.userId = userId;
	}

	public User(int userId, String username, String password, String fullname, String address) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.address = address;
	}

	/********************************************************/

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(int userid) {
		this.userId = userid;
	}

	/*********************************************************/

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/********************************************************/

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	/*******************************************************/
	
	public String getFullname() {
		return fullname;
	}
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/********************************************************/
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	/*******************************************************/
	

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	/*******************************************************/
	
	
	public boolean equalUser(User user) {
		return (user.getUsername().equals(this.getUsername()) && user.getPassword().equals(this.getPassword()));
	}
}
