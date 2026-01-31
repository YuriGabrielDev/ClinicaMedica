/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Representa o sistema da clinica médica
 */
public class SistemaClinicaMedica {
    private final EntityManagerFactory entityManagerFactory;
    
    public SistemaClinicaMedica() {
        entityManagerFactory = Persistence.createEntityManagerFactory("ClinicaMedicaPU");
    }
    
    /**
     * Encerra o sistema
     */
    public void encerrar(){
        entityManagerFactory.close();
    }
    
    /**
    * Cadastra os dados de um médico no sistema
    * @param dadosMedico dados a serem cadastrados
    * @return Em caso de erro, retorna uma string informando
    */
    public String registrarMedico(Medico dadosMedico) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String erro = null;
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(dadosMedico);
            entityManager.getTransaction().commit();
        } catch (javax.persistence.PersistenceException e) {
            erro = "CRM ou CPF já estão cadastrados";
        }
        entityManager.close();
        return erro;
    }
    
    /**
    * Cadastra os dados de uma secretária no sistema
    * @param dadosSecretaria dados a serem cadastrados
    * @return Em caso de erro, retorna uma string informando
    */
    public String registrarSecretaria(Secretaria dadosSecretaria) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String erro = null;
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(dadosSecretaria);
            entityManager.getTransaction().commit();
        } catch (javax.persistence.PersistenceException e) {
            erro = "CPF já está cadastrado";
        }
        entityManager.close();
        return erro;
    }
    
    /**
     * Obtem os dados do médico por meio do cpf
     * @param cpf valor unico
     * @return dados do médico. Se não houver, retorna null
     */
    public Medico obterMedico(String cpf) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Medico dadosMedico;
        try {
            Query query = entityManager.createQuery("SELECT m FROM Medicos m WHERE m.cpf = :cpf");
            query.setParameter("cpf", cpf);
            dadosMedico = (Medico) query.getSingleResult();
            
        } catch (Exception e) {
            dadosMedico = null;
        }
        entityManager.close();
        return dadosMedico;
    }
    
    /**
     * Obtem os dados do médico por meio do id
     * @param id Identificador do registro
     * @return dados do médico. Se não houver, retorna null
     */
    public Medico obterMedico(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Medico dadosMedico;
        try {
            Query query = entityManager.createQuery("SELECT m FROM Medicos m WHERE m.id = :id");
            query.setParameter("id", id);
            dadosMedico = (Medico) query.getSingleResult();
            
        } catch (Exception e) {
            dadosMedico = null;
        }
        entityManager.close();
        return dadosMedico;
    }
    
    /**
     * Obtem os dados da secretaria por meio do cpf
     * @param cpf valor unico
     * @return dados do médico. Se não houver, retorna null
     */
    public Secretaria obterSecretaria(String cpf) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Secretaria dadosSecretaria = null;
        try {
            
            Query query = entityManager.createQuery("SELECT m FROM Secretarias m WHERE m.cpf = :cpf");
            query.setParameter("cpf", cpf);
            dadosSecretaria = (Secretaria) query.getSingleResult();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        entityManager.close();
        return dadosSecretaria;
    }
    
    /**
     * Pesquisa uma lista de pacientes no banco da dados
     * @param cpf Cpf do paciente
     * @param nome Nome do paciente
     * @return Lista de pacientes com informação filtrada
     */
    public List<Paciente> pesquisarPacientes(String nome, String cpf) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery(
            "select p from Pacientes p where p.nome like :nome and p.cpf like :cpf"
        );
        query.setParameter("cpf", "%" + cpf + "%");
        query.setParameter("nome", "%" + nome + "%" );
        List<Paciente> pacientes = query.getResultList();
        return pacientes;
    }
    
    /**
     * Obtem os dados do paciente por meio do id
     * @param id Identificador do registro
     * @return dados do médico. Se não houver, retorna null
     */
    public Paciente obterPaciente(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Paciente paciente;
        try {
            Query query = entityManager.createQuery("SELECT m FROM Pacientes m WHERE m.id = :id");
            query.setParameter("id", id);
            paciente = (Paciente) query.getSingleResult();
            
        } catch (Exception e) {
            paciente = null;
        }
        entityManager.close();
        return paciente;
    }
    
    /**
     * Atualiza os dados adicionais do paciente no banco
     * @param id Id de registro do paciente
     * @param comportamentosPrejudiciais Comportamentos prejudiciais
     * @param comorbidades Comorbidades
     * @param cirurgias Cirurgias
     * @param alergias Alergias
     */
    public void atualizarDadosAdicionaisPaciente(
        Integer id,
        String comportamentosPrejudiciais,
        String comorbidades,
        String cirurgias,
        String alergias
    ) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Paciente paciente = em.find(Paciente.class, id);

        paciente.setComportamentosPrejudiciais(comportamentosPrejudiciais);
        paciente.setComorbidades(comorbidades);
        paciente.setCirurgias(cirurgias);
        paciente.setAlergias(alergias);

        em.getTransaction().commit();
        em.close();
    }

    /**
     * Atualiza os dados de um paciente no banco
     * @param id Id de registro do paciente
     * @param nome Nome
     * @param cpf Cpf
     * @param telefone Telefone
     * @param email Email
     * @param dataNascimento Data de nascimento 
     * @param convenio Convenio
     * @return Erro, se houver
     */
    public String atualizarDadosPaciente(
        Integer id,
        String nome,
        String cpf,
        String telefone,
        String email,
        Date dataNascimento,
        String convenio
    ) {
        EntityManager em = entityManagerFactory.createEntityManager();
        String erro = null;

        try {
            em.getTransaction().begin();

            Paciente paciente = em.find(Paciente.class, id);

            paciente.setNome(nome);
            paciente.setCpf(cpf);
            paciente.setTelefone(telefone);
            paciente.setEmail(email);
            paciente.setDataNascimento(dataNascimento);
            paciente.setConvenio(convenio);

            em.getTransaction().commit();
        } catch (javax.persistence.PersistenceException e) {
            erro = "CRM ou CPF já estão cadastrados";
        }
        em.close();
        return erro;
    }

    /**
     * Registra dados do paciente no banco de dados
     * @param paciente Dados do paciente
     * @return Erro, se houver
     */
    public String registrarPaciente(Paciente paciente) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String erro = null;
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(paciente);
            entityManager.getTransaction().commit();
        } catch (javax.persistence.PersistenceException e) {
            erro = "CPF já estão cadastrados";
        }
        entityManager.close();
        return erro;
    }
    
    /**
     * Remove os dados do paciente do banco de dados
     * @param idPaciente 
     */
    public void removerPaciente(Integer idPaciente) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        Paciente paciente = entityManager.find(Paciente.class, idPaciente);
        if (paciente != null) {
            entityManager.remove(paciente);
        }
        
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    /**
     * Pesquisa uma lista de consulta no banco de dados
     * @return Lista de consultas com informação filtrada
     * @param paciente Nome do paciente que irá a consulta
     * @param medico Nome do medico que fará a consulta
     * @param tipo Tipo da consulta
     */
    public List<Consulta> pesquisarConsultas(
        String paciente, 
        String medico, 
        String tipo 
    ) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery(
            "select c from Consultas c join c.paciente p join c.medico m " +
            "where p.nome like :paciente and m.nome like :medico and c.tipo like :tipo"
        );
        query.setParameter("paciente", "%" + paciente + "%");
        query.setParameter("medico", "%" + medico + "%" );
        query.setParameter("tipo", "%" + tipo + "%" );
        List<Consulta> consultas = query.getResultList();
        return consultas;
    }
    
        /**
     * Pesquisa uma lista de medicos no banco da dados
     * @param cpf Cpf do medico
     * @param nome Nome do medico
     * @return Lista de pacientes com informação filtrada
     */
    public List<Medico> pesquisarMedicos(String nome, String cpf) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery(
            "select m from Medicos m where m.nome like :nome and m.cpf like :cpf"
        );
        query.setParameter("cpf", "%" + cpf + "%");
        query.setParameter("nome", "%" + nome + "%" );
        List<Medico> medicos = query.getResultList();
        return medicos;
    }
    
    /**
     * Registra dados do paciente no banco de dados
     * @param consulta Consulta a ser cadastrada
     */
    public void registrarConsulta(Consulta consulta) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        entityManager.persist(consulta);
        entityManager.getTransaction().commit();
            
        entityManager.close();
    }
    
    /**
     * Atualiza os dados de uma consulta no banco
     * @param id Id de registro do paciente
     * @param dataMarcada Nova data
     * @param tipo Tipo da consulta
     */
    public void atualizarConsulta(
        Integer id,
        Date dataMarcada,
        String tipo
    ) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Consulta consulta = em.find(Consulta.class, id);

        consulta.setTipo(tipo);
        consulta.setDataMarcada(dataMarcada);

        em.getTransaction().commit();
        em.close();
    }
    
    /**
     * Obtem os dados da consulta por meio do id
     * @param id Identificador do registro da consulta
     * @return Consulta. Se não houver, retorna null
     */
    public Consulta obterConsulta(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Consulta consulta;
        try {
            Query query = entityManager.createQuery("SELECT s FROM Consultas s WHERE s.id = :id");
            query.setParameter("id", id);
            consulta = (Consulta) query.getSingleResult();
            
        } catch (Exception e) {
            consulta = null;
        }
        entityManager.close();
        return consulta;
    }
    
    /**
     * Remove a consulta do banco de dados
     * @param idConsulta Id de registro da consulta
     */
    public void removerConsulta(Integer idConsulta) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        Consulta consulta = entityManager.find(Consulta.class, idConsulta);
        if (consulta != null) {
            entityManager.remove(consulta);
        }
        
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    /**
     * Obtem uma lista de consultas que coincidem com o dia
     * @param ano Ano da consulta
     * @param mes Mes da consulta
     * @param dia Dia da consulta
     * @param filtrarContato Ativa filtragem por contato
     * @return 
     */
    public List<Consulta> pesquisarConsultas(
        int ano,
        int mes,
        int dia,
        boolean filtrarContato
    ) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, ano);
        calendar1.set(Calendar.MONTH, mes);
        calendar1.set(Calendar.DAY_OF_MONTH, dia);
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        
        Calendar calendar2 = (Calendar) calendar1.clone();
        calendar2.add(Calendar.DAY_OF_YEAR, 1);
        
        
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query;
        if (filtrarContato) {
            query = entityManager.createQuery(
                "select c from Consultas c join c.paciente p " +
                "where c.dataMarcada >= :inicio and c.dataMarcada < :final " +
                "and ((p.telefone is not null and p.telefone <> '') " +
                "or (p.email is not null and p.email <> '')) "
            );
        } else {
            query = entityManager.createQuery(
                "select c from Consultas c " +
                "where c.dataMarcada >= :inicio and c.dataMarcada < :final " +
                ""
            );
        }
        query.setParameter("inicio", calendar1.getTime());
        query.setParameter("final", calendar2.getTime());
        List<Consulta> consultas = query.getResultList();
        
        return consultas;
    }

    public List<Prontuario> pesquisarProntuario(
        String paciente, 
        String medico
    ) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery(
            "select pr from Prontuarios pr join pr.paciente p join pr.medico m " +
            "where p.nome like :paciente and m.nome like :medico"
        );
        query.setParameter("paciente", "%" + paciente + "%");
        query.setParameter("medico", "%" + medico + "%" );
        List<Prontuario> prontuarios = query.getResultList();
        return prontuarios;
    }
    
    /**
     * Obtem o prontuário cadastrado no banco de dados
     * @param id Id de registro do prontuário
     * @return 
     */
    public Prontuario obterProntuario(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Prontuario prontuario;
        try {
            Query query = entityManager.createQuery("SELECT p FROM Prontuarios p WHERE p.id = :id");
            query.setParameter("id", id);
            prontuario = (Prontuario) query.getSingleResult();
            
        } catch (Exception e) {
            prontuario = null;
        }
        entityManager.close();
        return prontuario;
    }
    
    public void atualizarProntuario(
        Integer id,
        String sintomas,
        String diagnostiscos,
        String prescricao
    ) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Prontuario prontuario = em.find(Prontuario.class, id);

        prontuario.setSintomas(sintomas);
        prontuario.setDiagnosticos(diagnostiscos);
        prontuario.setPrescricao(prescricao);

        em.getTransaction().commit();
        em.close();
    }

    public void removerProntuario(Integer idProntuario) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        Prontuario prontuario = entityManager.find(Prontuario.class, idProntuario);
        if (prontuario != null) {
            entityManager.remove(prontuario);
        }
        
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    /**
     * Pesquisa uma lista de prontuários de um médico em um mês
     * @param medico Médico que cadastrou o prontuário
     * @param ano Ano
     * @param mes Mês
     * @return Lista condizente
     */
    public List<Prontuario> pesquisarProntuarios(
        Medico medico,
        int ano,
        int mes
    ) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, ano);
        calendar1.set(Calendar.MONTH, mes);
        calendar1.set(Calendar.DAY_OF_MONTH, 1);
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);

        Calendar calendar2 = (Calendar) calendar1.clone();
        calendar2.add(Calendar.MONTH, 1);
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query;
        query = entityManager.createQuery(
            "select p from Prontuarios p join p.medico m " +
            "where p.dataCriacao >= :inicio and p.dataCriacao < :final " + 
            "and m.id = :id"
        );
        query.setParameter("inicio", calendar1.getTime());
        query.setParameter("final", calendar2.getTime());
        query.setParameter("id", medico.getId());
        List<Prontuario> prontuarios = query.getResultList();
        
        return prontuarios;
    }
    
    public void registrarProntuario(Prontuario prontuario) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        entityManager.persist(prontuario);
        entityManager.getTransaction().commit();
            
        entityManager.close();
    }
}


