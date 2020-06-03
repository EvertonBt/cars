package com.batista.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// classe que irá interceptar todas as exceptions, retornando um erro amigável
@RestControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler{

// dentro do ExceptionHanler vc indica quais exceções q quando executada irão chamar o metodo abaixo, retornado
// nesse métoddo se ao deletar um carro passar um id q ñ existe ele irá capturar essa exceção e retonar erro 404 not found
  @ExceptionHandler({
	  EmptyResultDataAccessException.class
  })
   public ResponseEntity errorNotFound(Exception ex) {
	   return ResponseEntity.notFound().build();
   }
  
  //nesse metodo ele captura a IlgelaArgumentException q ao salvar um recurso ñ pode informar um id, pois o recurso ainda ñ existe, caso seja passado um id ele 
  // retornará essa exceção q será captirada e retornará o erro bad request 400
 @ExceptionHandler({
	 IllegalArgumentException.class
 })
  public ResponseEntity errorBadRequest(Exception ex) {
	 return ResponseEntity.badRequest().build();
 }
 
 //método herdado de ReponseEntityExceptionHandler, q captura o erro Method ñ permitido (qnd vc digita um url q ñ existe na API):
 protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
          return new ResponseEntity<Object>("Operação não permitida", HttpStatus.METHOD_NOT_ALLOWED);

}
 // trata erro 403 forbiden (qnd vc tentar fazer uma requisição com credencias ñ autorizadas)
 @ExceptionHandler({
	 org.springframework.security.access.AccessDeniedException.class
 })
 public ResponseEntity acessDenied() {
	 return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error("Acesso negado, suas credenciais são autorizam acesso via POST"));
 }
 
}
 // só p/ passar as mensagens de erro, se vc passar assim através de um objeto em vez de uma string comum, ele gera
 // uma mensagem no formato json
 class Error {

 public String msgErro;

public Error (String mensagem){
	 this.msgErro = mensagem;
  
}
}