package io.github.leandro616.persistence;

import java.util.List;

import io.github.leandro616.model.RamoAtividade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class RamoAtividadeDAO extends SuperDAO<RamoAtividade, Integer> {

    private EntityManager em;

    public RamoAtividadeDAO(EntityManager em) {
        super(RamoAtividade.class, em);
        this.em = em;
    }

    /**
     * exemplo utilizando Criteria
     * 
     * @param nome
     * @return 
     */
    public List<RamoAtividade> buscarPorNome(String nome) {
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<RamoAtividade> criteria = builder.createQuery(RamoAtividade.class);
        Root<RamoAtividade> root = criteria.from(RamoAtividade.class);
        criteria.select(root);
        criteria.where(builder.like(root.get("descricao"), nome + "%"));

        return em.createQuery(criteria).getResultList();

    }

}