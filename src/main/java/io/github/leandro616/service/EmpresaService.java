package io.github.leandro616.service;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.github.leandro616.annotations.Transacional;
import io.github.leandro616.model.Empresa;
import io.github.leandro616.persistence.EmpresaDAO;

@ApplicationScoped
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

    public List<Empresa> buscarPorNome(String nome) {
        if (Objects.isNull(nome))
            nome = "";
        return dao.buscarPorNome(nome);
    }
}
