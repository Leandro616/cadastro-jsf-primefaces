package io.github.leandro616.persistence;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

public class SuperDAO<T, I extends Serializable> {

    private Class<T> clazz;
    private EntityManager em;

    public SuperDAO(Class<T> clazz, EntityManager em) {
        this.clazz = clazz;
        this.em = em;
    }

    @Transactional
    public T salvar(T entity) {
        em.merge(entity);
        return entity;
    }

    public T buscarPorId(I id) {
        return em.find(clazz, id);
    }

    public List<T> listar() {
        return em.createQuery("FROM " + clazz.getName(), clazz)
                .getResultList();
    }

    public void deletar(I id) {
        T entity = buscarPorId(id);
        em.remove(entity);
    }

}
