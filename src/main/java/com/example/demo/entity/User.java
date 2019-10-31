package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
		@UniqueConstraint(columnNames = { "email" }) })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 40)
	private String name;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NaturalId
	@NotBlank
	@Size(max = 40)
	@Email
	private String email;

	@NotBlank
	@Size(max = 200)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@JsonManagedReference
	@OneToMany(mappedBy = "user", targetEntity = File.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<File> files = new HashSet<>();

	public User(Long id, @NotBlank @Size(max = 40) String name, @NotBlank @Size(max = 20) String username,
			@NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(max = 200) String password, Set<Role> roles,
			Set<File> files) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.files = files;
	}

	public User(Long id, @NotBlank @Size(max = 40) String name, @NotBlank @Size(max = 20) String username,
			@NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(max = 200) String password,
			Set<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public User(Long id, @NotBlank @Size(max = 40) String name, @NotBlank @Size(max = 20) String username,
			@NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(max = 200) String password) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public User(@NotBlank @Size(max = 40) String name, @NotBlank @Size(max = 20) String username,
			@NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(max = 200) String password) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public User() {
		super();
	}

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getUsername() {
		return username;
	}

	public final void setUsername(String username) {
		this.username = username;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	public final Set<Role> getRoles() {
		return roles;
	}

	public final void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public final Set<File> getFiles() {
		return files;
	}

	public final void setFiles(Set<File> files) {
		this.files = files;
	}

}
