package ar.com.ada.api.nasa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.nasa.entities.Pais;
import ar.com.ada.api.nasa.repositories.PaisRepository;

@Service
public class PaisService {
    
    @Autowired
    PaisRepository paisRepository;
    
    public Pais crearPais(Integer codigoPais, String nombre){

        Pais pais = new Pais();

        pais.setCodigoPais(codigoPais);
        pais.setNombre(nombre);

        paisRepository.save(pais);

        return pais;

    }
}