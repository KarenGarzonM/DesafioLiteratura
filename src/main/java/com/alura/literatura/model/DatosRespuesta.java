package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosRespuesta {

    private List<DatosLibro> results;

    public List<DatosLibro> getResults() {
        return results;
    }

}
