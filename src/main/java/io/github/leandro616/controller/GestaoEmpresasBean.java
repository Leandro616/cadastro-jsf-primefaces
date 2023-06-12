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
import io.github.leandro616.util.FacesMessages;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EmpresaService service;

    @Inject
    private FacesMessages messages;

    private Empresa empresa = new Empresa();
    private List<Empresa> empresas = new ArrayList<>();
    private String termoPesquisa;

    public void todasEmpresas() {
        empresas = service.buscarPorNome(null);
    }

    public void salvar() {
        System.out.println(empresa);
    }

    public void pesquisar() {
        empresas = service.buscarPorNome(termoPesquisa);

        if (empresas.isEmpty()) {
            messages.info("Sua consulta não retornou registros");
        }
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public TipoEmpresa[] getTiposEmpresa() {
        return TipoEmpresa.values();
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

}
