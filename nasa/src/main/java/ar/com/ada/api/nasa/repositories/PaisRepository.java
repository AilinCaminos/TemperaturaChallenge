package ar.com.ada.api.nasa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.nasa.entities.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer> {

    public Pais findByCodigoPais(int codigoPais);

}