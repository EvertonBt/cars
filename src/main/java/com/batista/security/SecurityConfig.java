package com.batista.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration // indica q é uma classe de configuração
@EnableWebSecurity // habilita a classe como definidora de configuração do spring security
@EnableGlobalMethodSecurity(securedEnabled = true) // habilita restrição individual por método
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("UserDetailsService")
	private UserDetailsService userDetailsService;
	
   @Override
protected void configure(HttpSecurity http) throws Exception {

	   http
		.authorizeRequests()
		.anyRequest().authenticated()        // faz c/ q todas as rotas exijam autenticação
			.and()
	 //      .formLogin().and()              // habilita autenticação via form authentication
		.httpBasic()                         // habilita autenticação via basic authentication
	   .and().csrf().disable();              // desabilita a proteção contra csrf, pois ñ é util em APIs
}
   
   @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//super.configure(auth);
	   // obrigatório p/ definir o tipo de criptografia c/ BCrypt ( agora é obrigatorio indicar isso)
	   BCryptPasswordEncoder codificador = new BCryptPasswordEncoder();
	   
	   // buscandos as credenicias no BD
	   auth.userDetailsService(this.userDetailsService).passwordEncoder(codificador);
	   
 
	   // depois q passei a customiza a classe UserDetails ñ usei mais a autenticaçao em memória
//	   auth.inMemoryAuthentication().passwordEncoder(codificador) // define q é autenticação em memória e q usará a criptografia o Bcrytpt
//	   .withUser("user").password(codificador.encode("user")).roles("USER") // define login, senha e perfil do usuário user
//	   .and()
//	   .withUser("admin").password(codificador.encode("admin")).roles("USER", "ADMIN"); // define login, senha e perfil do usuario admin
//	   
	}
	
}
