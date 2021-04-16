package com.yuiti.thiagoyuiti022010513sc2.dominio;

import javax.validation.constraints.Positive;

public class Golpe {
    @Positive
    private Integer idLutadorBate;
    @Positive
    private Integer idLutadorApanha;

    public Golpe(Integer idLutadorBate, Integer idLutadorApanha) {
        this.idLutadorBate = idLutadorBate;
        this.idLutadorApanha = idLutadorApanha;
    }

    public Integer getIdLutadorBate() {
        return idLutadorBate;
    }

    public void setIdLutadorBate(Integer idLutadorBate) {
        this.idLutadorBate = idLutadorBate;
    }

    public Integer getIdLutadorApanha() {
        return idLutadorApanha;
    }

    public void setIdLutadorApanha(Integer idLutadorApanha) {
        this.idLutadorApanha = idLutadorApanha;
    }
}
