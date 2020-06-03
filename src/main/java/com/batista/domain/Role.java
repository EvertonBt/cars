package com.batista.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

// vc precisa implementar GrantedAuhtority p/ q possa sobrescrever a classe getAuthority
@Entity(name="role")
public class Role implements GrantedAuthority{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	// vc precisa sobrescrever esse método pois ele é necessário p/ invocar os perfis do usuario na classe Usuario
	@Override
	public String getAuthority() {
		return this.nome;
	}
	
	//geters e setters

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
	
}
