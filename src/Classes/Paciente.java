/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity(name = "Pacientes")
/**
 * Classe POJO que representa os dados do paciente
 */
public class Paciente extends Pessoa{
    @Lob
    private String comportamentosPrejudiciais;
    @Lob
    private String comorbidades;
    @Lob
    private String cirurgias;
    @Lob
    private String alergias;
    private String convenio;
    
    public Paciente() {};

    public String getComportamentosPrejudiciais() {
        return comportamentosPrejudiciais;
    }

    public void setComportamentosPrejudiciais(String comportamentosPrejudiciais) {
        this.comportamentosPrejudiciais = comportamentosPrejudiciais;
    }

    public String getComorbidades() {
        return comorbidades;
    }

    public void setComorbidades(String comorbidades) {
        this.comorbidades = comorbidades;
    }

    public String getCirurgias() {
        return cirurgias;
    }

    public void setCirurgias(String cirurgias) {
        this.cirurgias = cirurgias;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }
    
    
}
