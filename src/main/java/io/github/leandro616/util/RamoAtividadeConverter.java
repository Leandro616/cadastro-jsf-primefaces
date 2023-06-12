package io.github.leandro616.util;

import java.util.List;
import java.util.Objects;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import io.github.leandro616.model.RamoAtividade;

public class RamoAtividadeConverter implements Converter {

    private List<RamoAtividade> ramos;

    public RamoAtividadeConverter(List<RamoAtividade> ramos) {
        this.ramos = ramos;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (Objects.isNull(value))
            return null;

        Integer id = Integer.valueOf(value);

        return ramos.stream()
                .filter(r -> Objects.equals(r.getId(), id))
                .findFirst()
                .orElse(null);

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (Objects.isNull(value))
            return null;

        RamoAtividade ramoAtividade = (RamoAtividade) value;

        return ramoAtividade.getId().toString();
    }

}
