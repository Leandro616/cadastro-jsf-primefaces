package io.github.leandro616.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;

/**
 * Conversor JSF para LocalDate
 * 
*/
public class LocalDateConverter extends DateTimeConverter {

    private static final String PATTERN = "dd/MM/yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (Objects.isNull(value) || value.trim().isEmpty())
            return null;

        return LocalDate.parse(value, FORMATTER);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (Objects.isNull(value))
            return "";

        LocalDate date = (LocalDate) value;
        return date.format(FORMATTER);
    }

}
