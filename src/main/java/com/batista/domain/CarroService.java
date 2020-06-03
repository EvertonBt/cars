package com.batista.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batista.exception.ObjectNotFoundException;

// anotação necessária p/ fazer a injeção de dependencia
@Service
public class CarroService {

    @Autowired
	private CarroRepository repository;
	  
    // retorna uma lista de carros
    public List<Carro> getCarros() {
    	return this.repository.findAll();
    }
    // retorna uma lista de carros DTO
    public List<CarroDTO> getCarrosDto() {
		
    	// Pega a lista de carros retornada pelo método, depois varre a lista de Carros, criando várias instancias de CarroDTO e adicionanod
    	// um carro no seu construtor
    	List<Carro> carros = this.repository.findAll();
    
    	List<CarroDTO> dto = new ArrayList<>();
		 for(Carro c: carros) {
			dto.add(new CarroDTO(c));
		 }
		 
		 return dto; 
		  
	 // tudo o que foi feito acima, pode ser substituido por uma única linha
	 // return  this.repository.findAll().stream().map(c -> new CarroDTO(c)).collect(Collectors.toList());
	
	 
	}
	// retorna apenas um carro
    public Optional<Carro> getCarroById(Long id) {
    	return this.repository.findById(id);
    }
    // retorna apenas um carro DTO 
    public CarroDTO getCarroByIdDto(Long id) {
    	// o método retorna um Carro, mas a função map converte esse retorno num CarroDTO, se der algum erro
    	//ele lança uma exceção q será capturadda pela classe ObjectNotFoundException anotada com o ResponseStatus
    	return this.repository.findById(id).map(c -> new CarroDTO(c))
    			.orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
    }
    
 // retorna uma lista de  carros de acordo com o tipo passado
    public List<Carro> getCarroByTipo(String tipo) {
    	return this.repository.findByTipo(tipo);
    }
    
  
	// retorna uma lista de  carros DTO de acordo com o tipo passado
	public List<CarroDTO> getCarroByTipoDto(String tipo) {	
 	
	 return this.repository.findByTipo(tipo).stream().map(c -> new CarroDTO(c)).collect(Collectors.toList());
				
	}
	// salva um carro no BD e retorna-o em seguida
	public Carro salvarCarro(Carro carro) {
		// testa se o usuário enviou o id (o que ñ pode, já que o recurso ainda ñ existe e o id é gerado auto pelo BD)
		Assert.isNull(carro.getId(), "Não foi possivel inserir o carro, pois vc informou um campo inválido");
		return this.repository.save(carro);
	
	}
	
	// atualiza carro 
	public Carro update(Long id, Carro carro) {
		// busca carro no banco pelo id informado
		Optional<Carro> optional = this.repository.findById(id);
		
		// verifica se o banco retornou alguma coisa
		if(optional.isPresent()) {
			// usa o método get do optional p/ retornar um carro "puro"
			Carro db = optional.get();
			// modifica o objeto banco com os novos dados enviados e salva no banco
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Atualizado com sucesso" + db.getId()); 
			return this.repository.save(db);
			
		} else {
			 return null;
			//throw new RuntimeException("Não foi possível atualizar o registro");
		}			
	}
	
	// deleta carro
	public void delete(Long id) {
		// caso passe um id q ñ existe a exceção retornada será catpurada pela classe ExceptionHandler
		this.repository.deleteById(id);
	
	//throw new RuntimeException("Erro ao apagar carro");
		
	}
}
