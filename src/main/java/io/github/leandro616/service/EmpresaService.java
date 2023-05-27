package io.github.leandro616.service;

import javax.inject.Inject;

import io.github.leandro616.annotations.Transacional;
import io.github.leandro616.model.Empresa;
import io.github.leandro616.persistence.EmpresaDAO;

public class EmpresaService {

    @Inject
    EmpresaDAO dao;

    @Transacional
    public void salvar(Empresa empresa) {
        dao.salvar(empresa);
    }

    @Transacional
    public void excluir(Integer id) {
        dao.deletar(id);
    }
}
