package br.com.microprojeto.microprojetobackend.servicos;

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
