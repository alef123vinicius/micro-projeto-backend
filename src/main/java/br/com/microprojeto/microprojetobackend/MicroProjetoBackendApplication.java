package br.com.microprojeto.microprojetobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * No método tradicional é necessario criar o @Configuration, @EnableConfiguration e @ComponentScan
 * O @SprintBootApplication resume essas três configurações, uma vez que o servidor web já vem incorporado
 * e pronto para uso, graças ao tomcat configurado. A anotação ja faz todo o restante e sobre o projeto
 * direto pelo tomcat na porta 80
 */
@SpringBootApplication
public class MicroProjetoBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroProjetoBackendApplication.class, args);
	}

}
