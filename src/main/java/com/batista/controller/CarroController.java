package com.batista.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.batista.domain.Carro;
import com.batista.domain.CarroDTO;
import com.batista.domain.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {
   
	 @Autowired
	 CarroService service;
	
	 // retorna uma lista de carros
	@GetMapping()
	public ResponseEntity<List<Carro>> getCarros() {
		return ResponseEntity.ok(this.service.getCarros());
	} 
	 
	// retorna uma lista de carros DTO
	@GetMapping("/dto")
	public ResponseEntity<List<CarroDTO>> getCarrosDto() {
		// Ok = 200 sucesso
		return new ResponseEntity<>(this.service.getCarrosDto(), HttpStatus.OK);
		
	}
	// esse método retorna um carro pelo id
	//o optional é um tipo retornado pelo jpa e pelo spring q qnd ñ retorna nada ele envia
	// um valor null na tela
	@GetMapping("/{id}")
	public ResponseEntity<Carro> getCarroById(@PathVariable Long id) {
		Optional<Carro> optional = this.service.getCarroById(id);
		// primeira forma usando if/else
		if(optional.isPresent()) {
			// código 200 ok
			return ResponseEntity.ok(optional.get()); // usar o get ele dá build automaticamente
			
		} else {
			// código 404 not found
			return ResponseEntity.notFound().build();
		}
	 // segunda forma usando lambdas
		/*
		   return optional
		            .map(c -> ResponseEntity.ok(c))
		            .orElse(ResponseEntity.notFound().build());
		*/
		
	}
	
	// retorna um única carro DTO pelo id
	@GetMapping("/dto/{id}")
	public ResponseEntity<CarroDTO> getCarroIdDto(@PathVariable ("id") Long id) {
		CarroDTO carro = this.service.getCarroByIdDto(id);
	  return ResponseEntity.ok(carro);
	
	} 
	
	// Retorna uma lista de carros pelo tipo
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<Carro>> getCarroByTipo(@PathVariable("tipo") String tipo) {
		  List<Carro> carros = this.service.getCarroByTipo(tipo);  
		  if(carros.isEmpty()) {
			// no content = código 204, qnd o array retornado é vazio ou null
			  return ResponseEntity.noContent().build();
		  } else {
			  return ResponseEntity.ok(carros);
		  }
	}
	
	// retorna uma lista de carros DTO pelo tipo
	@GetMapping("/dto/tipo/{tipo}")
	public ResponseEntity<List<CarroDTO>> getCarroByTipoDto(@PathVariable String tipo) {
		
		List<CarroDTO> carros = this.service.getCarroByTipoDto(tipo);
		if (carros.isEmpty())
			
			return ResponseEntity.noContent().build();
		else
			return ResponseEntity.ok(carros);
		
	}
   
	// salva um carro no BD	
	@PostMapping
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<Carro> salvarCarro(@RequestBody Carro carro) {
		// se der certo ele cria o novo recurso (201 created) e gera nos headers a uri do novo recurso criado
	    Carro c = this.service.salvarCarro(carro);
		URI location = this.getUri(c.getId());
		return ResponseEntity.created(location).build();		
	    // se o id for informado e/ou um atributo oribigatorio ñ for enviado, ele chama a classe de error Handler
		// q captura o IlegalArgumentException e  retorna erro 400 bad request
  
	
	}
	// retorna a uri do novo recurso criado
	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
	
	 
	// atualiza carro
   @PutMapping("/{id}")
   public ResponseEntity<Carro> put(@PathVariable Long id, @RequestBody Carro carro) {  
     // Se o id informado for inexistente ele retorna um null e a mensagem not found 404
     // se der tudo certo ele retorna ok  
	   Carro c =  this.service.update(id, carro);
	  return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();  

   }
   
   // Apaga carro
   @DeleteMapping("/{id}")
   public ResponseEntity delete(@PathVariable Long id) {
	  // se ele encontrar o carro c/ o id informado no BD ele deleta o carro e rretona o status 200 ok
	  // caso ñ encontre, ele retorna uma exceção capturada pelo ErrorHandler c/ o codigo not found 404
	   this.service.delete(id);
	   return ResponseEntity.ok().build();
   }
}
