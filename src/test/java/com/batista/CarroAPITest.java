package com.batista;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.batista.domain.Carro;
import com.batista.domain.CarroService;

// 2 param: o primeiro indica a classe principal da aplicação + e o segundo é o responsável por subir a aplicação spring Web p/
// fazer os testes
@SpringBootTest(classes = CarrosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarroAPITest {
    // responsável por fazer as requisições http p/ testar a API
	@Autowired
    protected TestRestTemplate rest;
	
	// classe de serviço p/ acessar o métodos CRUD 
	 @Autowired
	 private CarroService service;
	 
	 // retorna uma carro
	 private ResponseEntity<Carro> getCarro(String url) {
		 // passa 2 param: a url da api q quer testar + o objeto esperado na resposta
		return  this.rest.withBasicAuth("everton", "123").getForEntity(url, Carro.class);
	 }
     // retorna uma lista de carros
	 private ResponseEntity<List<Carro>> getCarros(String url) {
		 return rest.withBasicAuth("everton", "123").exchange(
				 url,
				 HttpMethod.GET,
				 null,
				 new ParameterizedTypeReference<List<Carro>>() {});
				
	 }
	 
	 // Classes de testes
	 
	 @Test
	 public void testeLista() {
		 List<Carro> carros = this.getCarros("/api/v1/carros").getBody();
		 assertNotNull(carros); // verifica se a lista foi retornada com sucesso, ou seja, ñ é null
		 assertEquals(30, carros.size()); // verifica se o numero de itens da lista é igual a 30
	 }

	 @Test
	 public void testeListaPorTipo() {
		 // testa se a lista de carros por tipo tem 10 carros de cada tipo
		 assertEquals(10, this.getCarros("/api/v1/carros/tipo/classicos").getBody().size());
		 assertEquals(10, this.getCarros("/api/v1/carros/tipo/luxo").getBody().size());
		 assertEquals(10, this.getCarros("/api/v1/carros/tipo/esportivos").getBody().size());
		 
		 // testa um tipo inexistente ele deve retornar o status 204 - no content
		 assertEquals(HttpStatus.NO_CONTENT, this.getCarros("/api/v1/carros/tipo/xxx").getStatusCode());
		 
	 }
	 
	 @Test
	 public void testeCarroEspecifico() {
		 // busca por um carro especifico pelo id e verifica se retorna um código 200 ok
		 ResponseEntity<Carro> resposta = this.getCarro("/api/v1/carros/11");
		 assertEquals(resposta.getStatusCode(), HttpStatus.OK);
		 // depois testa se o nome do carro retornado é Ferrari FF
		 Carro c = resposta.getBody();
		 assertEquals("Ferrari FF", c.getNome());
		 
		 // depois testa um carro que ñ existe e verifixa se retorna o código 404 Not Found
		 ResponseEntity<Carro> resposta2 = this.getCarro("/api/v1/carros/1000");
		 assertEquals(resposta2.getStatusCode(), HttpStatus.NOT_FOUND);
	
	 }
	 
	 @Test
	 public void TesteInserindoCarro() {
		 // cria uma instancia de carro
		 Carro carro = new Carro();
		 carro.setNome("Fusca");
		 carro.setTipo("classicos");
		 
		 // faz a requisição http salvando um carro no BD
		 ResponseEntity<Carro> resposta = this.rest.withBasicAuth("admin", "123").postForEntity("/api/v1/carros", carro, null);
		 
		 //testa se foi inserido com sucesso aguardando o código 201 - created
		 assertEquals(resposta.getStatusCode(), HttpStatus.CREATED);
		 
		 // o objeto inserido retorna o location q é a url do novo recurso criado
		 String location = resposta.getHeaders().get("Location").get(0);
		 
		 // a partir da url pegue o objeto inserido a partir do método privado getCarro
		 Carro carro_inserido = this.getCarro(location).getBody();
		 
		 //Verifica se oobjeto ñ é nulo
		 assertNotNull(carro_inserido);
		 
		 //verifica se os valroes dos atributos sao os esperados
		 assertEquals("Fusca", carro_inserido.getNome());
		 assertEquals("classicos", carro_inserido.getTipo());
				 
		 // Deleta o novo objeto criado e verifica se deletou
		 rest.withBasicAuth("admin", "123").delete(location);
		 assertEquals(this.getCarro(location).getStatusCode(), HttpStatus.NOT_FOUND);
	 }	 
} 
