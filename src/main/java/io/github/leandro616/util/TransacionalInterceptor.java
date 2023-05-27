package io.github.leandro616.util;

import io.github.leandro616.annotations.Transacional;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

/** 
 *  Classe responsavel por controlar as transações. Todas as 
 *  classes ou métodos que tiverem @Transacional passaram por esse 
 *  intercptador antes.
 * 
 *  nessa classe o @Transacional serve para avisar o CDI quais as classes ou 
 *  metodos que vão ser interceptadas, nesse caso quem tiver com @Transacional
 *  
 */
@Interceptor
@Transacional
@Priority(Interceptor.Priority.APPLICATION)
public class TransacionalInterceptor {

    @Inject
    private EntityManager em;

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        EntityTransaction transaction = em.getTransaction();

        // controle para saber se foi o interceptor que criou a transação
        boolean criador = false;

        try {
            if (!transaction.isActive()) {

                // evita que se faça commit em operações sem transação
                transaction.begin();
                transaction.rollback();

                // inicia a transação
                transaction.begin();

                criador = true;
            }

            // contunua a execução do metodo que está sendo interceptado
            return context.proceed();
        } catch (Exception e) {
            if (criador)
                transaction.rollback();
            
            throw e;
        } finally {
            if (transaction.isActive() && criador)
                transaction.commit();
        }
    }
}
