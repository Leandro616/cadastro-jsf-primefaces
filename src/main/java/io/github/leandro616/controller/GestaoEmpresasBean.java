package io.github.leandro616.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import io.github.leandro616.enums.TipoEmpresa;
import io.github.leandro616.model.Empresa;
import io.github.leandro616.service.EmpresaService;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EmpresaService service;

    private Empresa empresa = new Empresa();
    private List<Empresa> empresas = new ArrayList<>();

    public List<Empresa> getEmpresas() {
        if (empresas.isEmpty())
            empresas = service.buscarPorNome(null);

        return empresas;
    }

    public void salvar() {
        System.out.println(empresa);
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public TipoEmpresa[] getTiposEmpresa() {
        return TipoEmpresa.values();
    }
}
