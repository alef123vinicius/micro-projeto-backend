package br.com.microprojeto.microprojetobackend.entidades;

import javax.persistence.*;

@Entity
@Table(name = "estados")
public class Estado {

    /**
     * @Id define o id da classe
     * @GeneratedValue define que o Jpa será responsável por gerenciar a geração do id da classe.
     * @Column define o nome da coluna e algumas propriedades como length, por exemplo.
     */
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
