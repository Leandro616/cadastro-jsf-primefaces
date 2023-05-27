package io.github.leandro616.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe responsavel por produzir EntityManager para o CDI fazer a injeção
 *  
*/
@ApplicationScoped
public class EntityManagerProducer {
    private EntityManagerFactory factory;

    public EntityManagerProducer() {
        this.factory = Persistence.createEntityManagerFactory("cursoJSF");
    }

    /**
     * metodo responsavel por criar entityManagers, será criada uma nova 
     * instancia a cada requisição
     * 
     * @return EntityManager
     */
    @Produces
    @RequestScoped
    public EntityManager createEntityManager() {
        return factory.createEntityManager();
    }

    /**
     * metodo responsavel por executar o metodo close() toda
     * vez que uma instancia de EntityManager terminar de ser 
     * usada
     * 
     * @param em
     */
    public void closeEntityManager(@Disposes EntityManager em) {
        em.close();
    }
}
