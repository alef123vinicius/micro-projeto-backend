package br.com.microprojeto.microprojetobackend.repositorio;

import br.com.microprojeto.microprojetobackend.entidades.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioEstados extends JpaRepository<Estado, Integer> {
}
