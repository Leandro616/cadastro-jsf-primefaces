package io.github.leandro616.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import io.github.leandro616.enums.TipoEmpresa;
import io.github.leandro616.model.Empresa;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Empresa empresa = new Empresa();

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
