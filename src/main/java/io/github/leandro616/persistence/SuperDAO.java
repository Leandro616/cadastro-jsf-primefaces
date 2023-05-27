package io.github.leandro616.persistence;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import jakarta.persistence.EntityManager;

public class SuperDAO<T, I extends Serializable> {

    @Inject
    private EntityManager em;
    private Class<T> clazz;

    public SuperDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    public SuperDAO(Class<T> clazz, EntityManager em) {
        this.clazz = clazz;
        this.em = em;
    }

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
