package br.com.microprojeto.microprojetobackend.servicos;

import br.com.microprojeto.microprojetobackend.entidades.Estado;
import br.com.microprojeto.microprojetobackend.repositorio.RepositorioEstados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @Service define a classe de serviço no qual colocamos as ações, neste caso vemos um
 * crud abaixo, porém, pode ser qualquer funcionalizade ou grupo de funções que alterem dados
 * no banco ou em apis externas
 */
@Service
/**
 * Configura o comportamento transacional de um método.
 * Garante que seja 8 ou 80. ou a transação ocorre por completo ou falha em algum ponto de erro
 */
@Transactional
public class EstadoServico {


    /**
     * Esta anotação define um ponto de injeção dentro de uma classe. 
     * A mais utilizada é o autowired (Field Injection) Injeção de dependência por campo
     * Pode ser feito pelo construtor (Constructor Injection)
     * Pode ser feito utilizando um método setter (Setter Injection)
     */
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
