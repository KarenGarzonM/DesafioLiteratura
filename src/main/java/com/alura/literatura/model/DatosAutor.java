package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosAutor {

    private String name;

    @JsonAlias("birth_year")
    private Integer nacimiento;

    @JsonAlias("death_year")
    private Integer fallecimiento;

    public DatosAutor() {
    }

    public String getName() {
        return name;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    public Integer getFallecimiento() {
        return fallecimiento;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nombre='" + name + '\'' +
                ", nacimiento=" + nacimiento +
                ", fallecimiento=" + fallecimiento +
                '}';
    }
}
