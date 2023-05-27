package io.github.leandro616.persistence;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import io.github.leandro616.model.Empresa;

import jakarta.persistence.EntityManager;

@RequestScoped
public class EmpresaDAO extends SuperDAO<Empresa, Integer> {

    @Inject
    private EntityManager em;

    public EmpresaDAO() {
        super(Empresa.class);
    }

    public EmpresaDAO(EntityManager em) {
        super(Empresa.class, em);
        this.em = em;
    }

    public List<Empresa> buscarPorNome(String nome) {
        String hql = " FROM Empresa e WHERE e.nomeFantasia like :nome";

        return em.createQuery(hql, Empresa.class)
                .setParameter("nome", nome + "%")
                .getResultList();
    }
}
