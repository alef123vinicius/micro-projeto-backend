# Micro Projeto Backend

## iniciando o projeto

https://start.spring.io/

Opções >
Project Maven 
Language Java
Spring Boot 2.7.11

Group
com.microprojeto

Artifact
microprojetobackend

Name
microprojetobackend

Description
Demo project for Spring Boot

Package name
com.microprojeto.microprojetobackend

Packaging
Jar

Java 
8

Dependencias

Spring Data Jpa
Spring Web
MySql Driver
Spring REST Docs

generate

Após a geração 

Adicionar duas bibliotecas no pom

```
		<!-- https://mvnrepository.com/artifact/junit/junit -->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.13.2</version>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>test</scope>
		</dependency>
```

mvn spring-boot:run


application.properties

```
spring.datasource.url=jdbc:mysql://172.17.0.3:3306/aula-micro
spring.datasource.username=dbusermicro
spring.datasource.password=aulamicro2023
```

test.properties

```
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
```

mvn clean package

mvn -Dmaven.test.skip clean  package

docker build -t micro-projeto-backend .

docker run -d -p 8080:8080 micro-projeto-backend .

export MYUSER=dbusermicro
export MYPASSWORD=aulamicro2023
export HOSTNAME=172.17.0.3


##  Camada Controller

```
import br.com.microprojeto.microprojetobackend.entidades.Estado;
import br.com.microprojeto.microprojetobackend.servicos.EstadoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/estados")
public class EstadoControler {

    @Autowired
    EstadoServico estadoServico;

    @GetMapping("")
    public List<Estado> listarEstados(){
        return estadoServico.listarTodosEstados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> buscar(@PathVariable Integer id) {
        try{
            Estado estado = estadoServico.buscarEstado(id);
            return new ResponseEntity<Estado>(estado, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Estado>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public void adicionar(@RequestBody Estado estado){
        estadoServico.salvarEstado(estado);
    }

    @DeleteMapping("/{id}")
    public void deletarEstado(@PathVariable Integer id) {
        estadoServico.excluirEstado(id);
    }
}
```

## Camada de Servico

```
import br.com.microprojeto.microprojetobackend.entidades.Estado;
import br.com.microprojeto.microprojetobackend.repositorio.RepositorioEstados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EstadoServico {

    @Autowired
    private RepositorioEstados repositorioEstados;

    public List<Estado> listarTodosEstados() {
        return repositorioEstados.findAll();
    }

    public void salvarEstado(Estado estado) {
        repositorioEstados.save(estado);
    }

    public Estado buscarEstado(Integer id) {
        return repositorioEstados.findById(id).get();
    }

    public void excluirEstado(Integer id) {
        repositorioEstados.deleteById(id);
    }
}

```

## Entidade Estado


```
import javax.persistence.*;

@Entity
@Table(name = "estados")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDESTADO")
    private int id;

    @Column(name = "SIGLAUF")
    private String siglaUf;

    public Estado() {
    }

    public Estado(int id, String siglaUf) {
        this.id = id;
        this.siglaUf = siglaUf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSiglaUf() {
        return siglaUf;
    }

    public void setSiglaUf(String siglaUf) {
        this.siglaUf = siglaUf;
    }
}

```

## Respository

```
import br.com.microprojeto.microprojetobackend.entidades.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioEstados extends JpaRepository<Estado, Integer> {
}
```

## Arquivo de teste com db memory mock

```
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
```