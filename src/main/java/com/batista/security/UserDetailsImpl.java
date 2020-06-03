package com.batista.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.batista.domain.Usuario;
import com.batista.domain.UsuarioRepository;

// esse value é um apelido p/ indicar no securityConfig através do @Qualifier qual classe deve ser injetada
@Service (value = "UserDetailsService")
public class UserDetailsImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    // verifica se o usuário com o login passado existe no BD se não ele retorna uma exceção
		Usuario usuario = repository.findByLogin(username);		
	    if(usuario == null) {
		   throw new UsernameNotFoundException("Usuário Não encontrado");
	    } 
	    // se o usuário existir ele retorna o usuario encontrado
        return usuario;

}

}
