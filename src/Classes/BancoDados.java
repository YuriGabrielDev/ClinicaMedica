/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;
import java.util.ArrayList;

/**
 * Classe POJO que representa o banco de dados da clínica médica.
 * @author YuriGabriel 
 */
public class BancoDados {
    private ArrayList<Paciente> pacientes;
    private ArrayList<Consulta> consultas;
    private ArrayList<Prontuario> prontuarios;
    
    public BancoDados() {
        this.pacientes = new ArrayList();
        this.consultas = new ArrayList();
        this.prontuarios = new ArrayList();
    }

    protected ArrayList<Prontuario> getProntuarios() {
        return prontuarios;
    }

    protected void setProntuarios(ArrayList<Prontuario> prontuarios) {
        this.prontuarios = prontuarios;
    }
    
    protected ArrayList<Paciente> getDadosPacientes() {
        return pacientes;
    }

    protected void setDadosPacientes(ArrayList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    protected ArrayList<Consulta> getConsultas() {
        return consultas;
    }

    protected void setConsultas(ArrayList<Consulta> consultas) {
        this.consultas = consultas;
    }
    
}
