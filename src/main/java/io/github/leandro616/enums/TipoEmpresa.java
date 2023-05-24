package io.github.leandro616.enums;

public enum TipoEmpresa {
    MEI("Microempreendedor individual"),
    EIRELI("Empresa individual de responsabilidade limitada"),
    LTDA("Sociedade limitada"),
    SA("Sociedade anonima");

    private String descricao;

    TipoEmpresa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }    
}
