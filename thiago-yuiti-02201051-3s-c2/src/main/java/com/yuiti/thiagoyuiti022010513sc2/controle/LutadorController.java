package com.yuiti.thiagoyuiti022010513sc2.controle;

import com.yuiti.thiagoyuiti022010513sc2.dominio.Golpe;
import com.yuiti.thiagoyuiti022010513sc2.dominio.Lutador;
import com.yuiti.thiagoyuiti022010513sc2.repositorio.LutadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lutadores")

public class LutadorController {

    @Autowired
    private LutadorRepository repository;

    @PostMapping
    public ResponseEntity postLutadores(@RequestBody @Valid Lutador lutador){

        repository.save(lutador);
        return ResponseEntity.status(201).build();
    }
    @GetMapping
   public ResponseEntity getLutadores(){
        List<Lutador> lutadores = repository.findAllByOrderByForcaGolpe();
        return ResponseEntity.status(200).body(lutadores);
    }
    @GetMapping("/contagens-vivos")
    public ResponseEntity getContagensVivos(){
        Long vivos = repository.countAllByVidaGreaterThan(0.0);
        return ResponseEntity.status(200).body("Números de vivos = " + vivos);
    }
    @PostMapping("/{id}/concentrar")
    public ResponseEntity postConcentrar(@PathVariable Integer id){


        if(!repository.existsById(id)){
            return ResponseEntity.status(204).build();
        }else {
            Lutador lutador = repository.findById(id).get();
            if(lutador.getConcentracoesRealizadas()<3) {
                lutador.setConcentracoesRealizadas(lutador.getConcentracoesRealizadas()+1);
                lutador.setVida(lutador.getVida()*1.15);
                repository.save(lutador);
                return ResponseEntity.status(200).build();
            }else{
                return ResponseEntity.status(400).body("Lutador já se concentrou 3 vezes!");
            }
        }
    }
    @PostMapping("/golpe")
    public ResponseEntity postGolpe(@RequestBody @Valid Golpe golpe){
        if(!repository.existsById(golpe.getIdLutadorApanha()) ||!repository.existsById(golpe.getIdLutadorBate())){
            return ResponseEntity.status(204).build();
        }

        Lutador lutadorBate = repository.findById(golpe.getIdLutadorBate()).get();
        Lutador lutadorApanha = repository.findById(golpe.getIdLutadorApanha()).get();
        if(!lutadorBate.isVivo() || !lutadorApanha.isVivo()){
            return ResponseEntity.status(400).body("Ambos os lutadores devem estar vivos!");
        }
        lutadorApanha.setVida(lutadorApanha.getVida()-lutadorBate.getForcaGolpe());
        repository.save(lutadorApanha);
        if (lutadorApanha.getVida()<=0){
            lutadorApanha.setVida(0.0);
        }
         List<Lutador> lutadores = new ArrayList<Lutador>();
            lutadores.add(lutadorBate);
            lutadores.add(lutadorApanha);
        return ResponseEntity.status(200).body(lutadores);
    }

    @GetMapping("/mortos")
    public ResponseEntity getMortos(){
        List<Lutador> lutadoresMortos = repository.findAllByVidaEquals(0.0);

        if(lutadoresMortos.isEmpty()){
            return ResponseEntity.status(204).build();
        }else {
            return ResponseEntity.status(200).body(lutadoresMortos);
        }

    }
}
