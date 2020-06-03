package com.batista.domain;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name="usuario")
public class Usuario implements UserDetails {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String login;
	private String senha;
	private String email;
	
	// define um relacionamento N:N entre a tabela Usuario e a tabela Role q criará uma tabela intermdiária chamada usuario_roles
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

	// métodos implmentados da interface UserDetails
	@Override // retorna os perfis
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override// retorna a senha 
	public String getPassword() {
		return this.senha;
	}

	@Override // retorna o login
	public String getUsername() {
		return this.login;
	}

	@Override //verifica se a conta está expirada
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override// verifica se a conta está bloqueada
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override//verifica se as credenciais são válidas
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override //verifica se está habilitada
	public boolean isEnabled() {
		return true;
	}
	
	// getters e setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


  
	
	
}
