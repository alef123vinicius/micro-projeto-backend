package br.com.microprojeto.microprojetobackend.controlador;

import br.com.microprojeto.microprojetobackend.entidades.Estado;
import br.com.microprojeto.microprojetobackend.servicos.EstadoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(maxAge = 3600)
/**
 * @RestController inclui as anotações @Controller e @Responsebody, ou seja, simplifica a implementação do controlador
 * Cada método então, será serializado com retorno HttpResponse
 */
@RestController
//@CrossOrigin(origins = {"http://frontend-service:80", "http://172.18.0.2:32585/"}, allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")

/**
 * Define uma rota no qual indicará o nome do endpoint que podemos acessar
 * Pode ser definido dentro da classe os sub métodos GET, POST, PUT, DELETE com
 * adições na rota .. ex: (/estados/listar ou /estados/alterar)
 */
@RequestMapping("/estados")
public class EstadoControler {

    /**
     * Esta anotação define um ponto de injeção dentro de uma classe. 
     * A mais utilizada é o autowired (Field Injection) Injeção de dependência por campo
     * Pode ser feito pelo construtor (Constructor Injection)
     * Pode ser feito utilizando um método setter (Setter Injection)
     */
    @Autowired
    EstadoServico estadoServico;

    /** 
     * Definindo um método GET sem adição de rota
     * apenas GET /estados 
    */

    @GetMapping("")
    public List<Estado> listarEstados(){
        return estadoServico.listarTodosEstados();
    }

    /** 
     * Definindo um método GET com passagem de parâmetro no path,
     * apenas GET /estados/1
    */
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

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEstado(@RequestBody Estado estado, Integer id){
        try {
            Estado estadoAtual = estadoServico.buscarEstado(id);
            estado.setId(id);
            estadoServico.salvarEstado(estado);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
