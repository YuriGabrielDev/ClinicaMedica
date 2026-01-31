/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "Medicos")
/**
 * Classe POJO que representa os dados de um médico
 */
public class Medico extends Pessoa{
    private String areaMedica;
    @Column(unique = true, nullable = false)
    private String crm;
    private String senha;

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
    
    public String getAreaMedica() {
        return areaMedica;
    }

    public void setAreaMedica(String areaMedica) {
        this.areaMedica = areaMedica;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
