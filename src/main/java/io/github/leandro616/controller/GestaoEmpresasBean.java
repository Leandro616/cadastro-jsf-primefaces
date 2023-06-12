package io.github.leandro616.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import io.github.leandro616.enums.TipoEmpresa;
import io.github.leandro616.model.Empresa;
import io.github.leandro616.model.RamoAtividade;
import io.github.leandro616.persistence.RamoAtividadeDAO;
import io.github.leandro616.service.EmpresaService;
import io.github.leandro616.util.FacesMessages;
import io.github.leandro616.util.RamoAtividadeConverter;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EmpresaService service;

    @Inject
    private FacesMessages messages;

    @Inject
    private RamoAtividadeDAO ramoAtividadeDAO;

    private Converter ramoAtividadeConverter;

    private Empresa empresa;
    private List<Empresa> empresas = new ArrayList<>();
    private String termoPesquisa;

    public void prepararNovaEmpresa() {
        System.out.println("preparando nova empresa");
        empresa = new Empresa();
    }

    public void todasEmpresas() {
        empresas = service.buscarPorNome(null);
    }

    public void salvar() {
        service.salvar(empresa);

        if (jaHouvePesquisa())
            pesquisar();
        else
            todasEmpresas();

        messages.info("Empresa cadastrada com sucesso!");
    }

    public void pesquisar() {
        empresas = service.buscarPorNome(termoPesquisa);

        if (empresas.isEmpty()) {
            messages.info("Sua consulta não retornou registros");
        }
    }

    /**
     * metodo que retorna a lista de ramos de acordo com o que o 
     * usuario digita no autocomplete
     * 
     * @param termo
     * @return lista de ramos de atividade
     */
    public List<RamoAtividade> completarRamoAtividade(String termo) {
        List<RamoAtividade> ramos = ramoAtividadeDAO.buscarPorNome(termo);

        ramoAtividadeConverter = new RamoAtividadeConverter(ramos);

        return ramos;
    }

    private boolean jaHouvePesquisa() {
        return termoPesquisa != null && !termoPesquisa.isBlank();
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

    public Converter getRamoAtividadeConverter() {
        return ramoAtividadeConverter;
    }

}
