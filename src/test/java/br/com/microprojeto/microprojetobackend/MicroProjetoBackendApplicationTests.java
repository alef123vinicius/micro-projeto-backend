package br.com.microprojeto.microprojetobackend;

import br.com.microprojeto.microprojetobackend.entidades.Estado;
import br.com.microprojeto.microprojetobackend.repositorio.RepositorioEstados;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class MicroProjetoBackendApplicationTests {

	@Autowired
	private RepositorioEstados repository;

	@Test
	public void testInicial() {
		repository.save(new Estado(1, "SP"));
	}

}
