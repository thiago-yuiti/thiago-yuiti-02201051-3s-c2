package com.yuiti.thiagoyuiti022010513sc2.repositorio;

import com.yuiti.thiagoyuiti022010513sc2.dominio.Lutador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LutadorRepository extends JpaRepository<Lutador,Integer> {

    List<Lutador> findAllByOrderByForcaGolpe();
    Long countAllByVidaGreaterThan(Double vida);
    List<Lutador> findAllByVidaEquals(Double vida);

}
