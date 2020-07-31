package ar.com.ada.api.nasa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.nasa.entities.Pais;
import ar.com.ada.api.nasa.repositories.PaisRepository;

@Service
public class PaisService {

    @Autowired
    PaisRepository paisRepository;

    public void crearPais(int codigoPais, String nombre) {

        Pais pais = new Pais();

        pais.setCodigoPais(codigoPais);
        pais.setNombre(nombre);

        this.grabar(pais);

    }

    public void grabar(Pais pais) {

        paisRepository.save(pais);
    }

    public List<Pais> listarPaises() {

        return paisRepository.findAll();
    }

    public Pais buscarPorCodigoPais(int codigoPais) {

        return paisRepository.findByCodigoPais(codigoPais);
    }

    public void actualizarNombrePais(Pais paisOriginal, String nombreNuevo) {

        paisOriginal.setNombre(nombreNuevo);

        this.grabar(paisOriginal);

    }
}