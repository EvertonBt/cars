package com.batista.domain;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

// o CrudRepository recebe 2 param, o primeiro é tipo do objeto e o segundo é o tipo do id dele 
public interface CarroRepository extends JpaRepository<Carro, Long> {

   // note q só fazendo isso o repository consegue criar um método automátiamente	
	 List<Carro> findByTipo(String tipo);

}
