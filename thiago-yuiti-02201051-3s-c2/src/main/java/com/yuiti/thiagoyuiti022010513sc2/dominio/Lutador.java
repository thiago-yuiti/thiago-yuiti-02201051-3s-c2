package com.yuiti.thiagoyuiti022010513sc2.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;


@Entity
public class Lutador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(min = 3, max = 12)
    @NotBlank
    private String nome;
    @PositiveOrZero
    private Double forcaGolpe;
    private Double vida = 100.0;
    private Integer concentracoesRealizadas = 0;
    private boolean vivo = isVivo();


    public boolean isVivo(){
        if(vida>0){
            return true;
        }else {
            return false;
        }
        }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getForcaGolpe() {
        return forcaGolpe;
    }

    public void setForcaGolpe(Double forcaGolpe) {
        this.forcaGolpe = forcaGolpe;
    }

    public Double getVida() {
        return vida;
    }

    public void setVida(Double vida) {
        this.vida = vida;
    }

    public Integer getConcentracoesRealizadas() {
        return concentracoesRealizadas;
    }

    public void setConcentracoesRealizadas(Integer concentracoesRealizadas) {
        this.concentracoesRealizadas = concentracoesRealizadas;
    }


}
