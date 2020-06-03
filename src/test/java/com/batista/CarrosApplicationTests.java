package com.batista;
// esse import já engloba todas as classes de testes
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.batista.domain.Carro;
import com.batista.domain.CarroService;

@SpringBootTest
class CarrosApplicationTests {
    /*
	@Autowired
	 private CarroService service;
	
	@Test
	public void testeInserirCarro() {
	  Carro carro = new Carro();
	  carro.setNome("Ferrari");
	  carro.setTipo("esportivos");
	 
	  // testa se o retrono do  carro inserido ñ está retornando nulo
	  Carro c = this.service.salvarCarro(carro);	 
	  assertNotNull(c);
	  
	   // verifia se o id do carro inserido ñ é nulo 
	  Long id= c.getId();
	  assertNotNull(id);
	  
	  // busca o carro inserido no banco, usando o findById e verifica se existe
	  Optional<Carro> optional = this.service.getCarroById(id);
	  assertTrue(optional.isPresent());
	  
	  // testa se os valores dos atributos do objeto retornado sãos os mesmos q foram definidos
	  c = optional.get();
	  assertEquals("Ferrari", c.getNome());
	  assertEquals("esportivos", c.getTipo());
	  
	  //caso esteja usando o mesmo BD de desenvolvimento p/ o ambiente de testes, é importante
	  //deletar o objeto criado durante os testes
	  this.service.delete(id);
	  
	  // verifica se o objeto foi de fato deletado do banco, o retorno nesse caso tem q ser falso
	  assertFalse(this.service.getCarroById(id).isPresent());	  
	}
	
	@Test
	public void testeListagemCarros() {
		// recupera a lista de carros e verifica se a quantidade de carros no banco é igual a 36 registros:
		List<Carro> lista_carros = this.service.getCarros();
		assertEquals(30, lista_carros.size());
	
	}
  @Test
  public void testeBuscaCarroPeloId() {
	  // Busca carro especific, verifica se está presente, e verifica se é o Ferrai FF
	  Optional<Carro> opt = this.service.getCarroById(11L);
	  assertTrue(opt.isPresent());
	  
	  Carro c = opt.get();
	  assertEquals(c.getNome(), "Ferrari FF");
  }

  @Test
  public void testeBucaCarroPeloTipo() {
	  // Busca carro pelos tipos luxo, esportivos e clássicos, verificando se há exatamente 10 de cada
	  // em seguida verifica que o númeor de carros do tipo "X" é igual a zero, já q ñ existe esse tipo de registro
	  assertEquals(10, this.service.getCarroByTipo("luxo").size());
	  assertEquals(10, this.service.getCarroByTipo("esportivos").size());
	  assertEquals(10, this.service.getCarroByTipo("classicos").size());
	  
	  assertEquals(0, this.service.getCarroByTipo("X").size());
  }
  */
}
