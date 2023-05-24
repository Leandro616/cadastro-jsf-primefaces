package io.github.leandro616.persistence;

import java.util.List;

import io.github.leandro616.model.Empresa;
import jakarta.persistence.EntityManager;

public class EmpresaDAO extends SuperDAO<Empresa, Integer> {

    private EntityManager em;

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
