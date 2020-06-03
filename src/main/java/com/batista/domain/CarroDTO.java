package com.batista.domain;

import lombok.*;

//Essa classe representa um fragmento da classe Carro, nesse caso ele terá os mesmos atributos de Carro pois
// Carro tem poucos atributos neste exemplo;
@Data
public class CarroDTO {
	
	private Long id;
	private String nome;
	private String tipo;
	
    // recebe um model Carro no construtor
	public CarroDTO(Carro c ) {
		this.id = c.getId();
		this.nome = c.getNome();
		this.tipo = c.getTipo();
	}
	
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	 
}
