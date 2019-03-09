package br.edu.iftm.sistemaClientes.model.dao;

import br.edu.iftm.sistemaClientes.model.domain.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ClienteDAO {
    
    public void salvarAtualizar(Cliente cliente) {
        EntityManager entityManager = Conexao.getEntityManager();
        entityManager.getTransaction().begin();
        if(cliente.getId() != null) {
            cliente = entityManager.merge(cliente);
        }
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    public void excluir(Cliente cliente) {
        EntityManager entityManager = Conexao.getEntityManager();
        entityManager.getTransaction().begin();
        cliente = entityManager.merge(cliente);
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    public List<Cliente> pesquisar(Cliente cliente) {
        EntityManager entityManager = Conexao.getEntityManager();
        
        StringBuffer sql = new StringBuffer("FROM Cliente c WHERE 1 = 1");
        
        if(cliente.getId() != null) {
            sql.append(" AND c.id = :id");
        }
        
        if(cliente.getNome() != null && !cliente.getNome().equals("")) {
            sql.append(" AND c.nome LIKE :nome");
        }
        
        Query query =entityManager.createQuery(sql.toString());
        
        if(cliente.getId() != null) {
            query.setParameter("id", cliente.getId());
        }
        
        if(cliente.getNome() != null && !cliente.getNome().equals("")) {
            query.setParameter("nome", "%" + cliente.getNome() + "%");
        }
        
        return query.getResultList();
    }
    
}
