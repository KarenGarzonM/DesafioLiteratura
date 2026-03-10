package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosLibro {

    private String title;

    private List<String> languages;

    @JsonAlias("download_count")
    private Integer numeroDescargas;

    private List<DatosAutor> authors;

    public String getTitle() {
        return title;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public List<DatosAutor> getAuthors() {
        return authors;
    }
}
