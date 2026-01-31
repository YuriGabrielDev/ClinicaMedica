/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.TemporalType;

@MappedSuperclass
/**
 * Classe POJO que representa os dados de uma pessoa
 */
public class Pessoa {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    protected String nome;
    @Column(unique = true, nullable = false)
    protected String cpf;
    protected String telefone;
    protected String email;
    @Temporal(TemporalType.DATE)
    protected Date dataNascimento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    public void setDataNascimento(int ano, int mes, int dia) {
        
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
