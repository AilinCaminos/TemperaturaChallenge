package ar.com.ada.api.nasa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.nasa.entities.Temperatura;
import ar.com.ada.api.nasa.repositories.TemperaturaRepository;

@Service
public class TemperaturaService {

    @Autowired
    PaisService paisService;
    @Autowired
    TemperaturaRepository temperaturaRepository;

    public Temperatura cargarTemperatura(Integer codigoPais, Integer anio, double temp) {

        Temperatura temperatura = new Temperatura();

        temperatura.setPais(paisService.buscarPorCodigoPais(codigoPais));
        temperatura.setAnio(anio);
        temperatura.setTemperatura(temp);

        temperaturaRepository.save(temperatura);

        return temperatura;

    }

    public List<Temperatura> listarTemperatura() {

        return temperaturaRepository.findAll();
    }

    public Temperatura buscarPorTemperaturaId(int id){

        return temperaturaRepository.findById(id);

    } 

    public void actualizarEstadoTemperatura(Temperatura temperatura, int anio){

        temperatura.setAnio(anio);

        temperaturaRepository.save(temperatura);

    }

    public void borrarTemperatura(Temperatura temperatura) {

        this.actualizarEstadoTemperatura(temperatura, 0);

    }

}