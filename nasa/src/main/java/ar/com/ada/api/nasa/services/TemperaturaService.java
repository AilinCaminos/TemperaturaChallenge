package ar.com.ada.api.nasa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.nasa.entities.Pais;
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

    public Temperatura buscarPorTemperaturaId(int id) {

        return temperaturaRepository.findById(id);

    }

    public void actualizarEstadoTemperatura(Temperatura temperatura, int anio) {

        temperatura.setAnio(anio);

        temperaturaRepository.save(temperatura);

    }

    public void borrarTemperatura(Temperatura temperatura) {

        this.actualizarEstadoTemperatura(temperatura, 0);

    }

    public List<Temperatura> buscarPorAnio(Integer anio) {

        List<Temperatura> tempsAnio = new ArrayList<>();

        List<Pais> paises = paisService.listarPaises();

        for (Pais p : paises) {

            for (Temperatura t : p.getTemperaturas()) {

                if (t.getAnio().equals(anio)) {

                    tempsAnio.add(t);

                }
            }
        }

        return tempsAnio;

    }

    public Temperatura buscarTempMax(Integer codigoPais) {

        Temperatura tempMax = null;

        Pais pais = paisService.buscarPorCodigoPais(codigoPais);

        double tempMaximaNro = -1000;

        for (Temperatura t : pais.getTemperaturas()) {
            if (t.getTemperatura() > tempMaximaNro) {
                tempMaximaNro = t.getTemperatura();
                tempMax = t;
            }
        }

        return tempMax;
    }

}