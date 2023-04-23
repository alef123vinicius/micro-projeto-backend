package br.com.microprojeto.microprojetobackend.controlador;

import br.com.microprojeto.microprojetobackend.entidades.Estado;
import br.com.microprojeto.microprojetobackend.servicos.EstadoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
