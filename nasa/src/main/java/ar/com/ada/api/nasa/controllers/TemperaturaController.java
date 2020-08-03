package ar.com.ada.api.nasa.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.nasa.entities.Temperatura;
import ar.com.ada.api.nasa.models.request.TemperaturaRequest;
import ar.com.ada.api.nasa.models.response.GenericResponse;
import ar.com.ada.api.nasa.models.response.*;
import ar.com.ada.api.nasa.services.TemperaturaService;

@RestController
public class TemperaturaController {

  @Autowired
  TemperaturaService temperaturaService;

  /*
   * POST /temperaturas : que registre una temperatura de un país en un año
   * específico RequestBody { “codigoPais”: 32, “anio”: 2010, “grados”: 38.6 }
   * 
   * Respuesta Esperada(JSON):
   * 
   * { “id”: 25 //O cualquier número de temperatura que devuelva. }
   */

  @PostMapping("/temperaturas")
  public ResponseEntity<GenericResponse> cargarTemperatura(@RequestBody TemperaturaRequest temp) {

    Temperatura temperatura = temperaturaService.cargarTemperatura(temp.codigoPais, temp.anio, temp.temperatura);

    GenericResponse resp = new GenericResponse();

    resp.isOk = true;
    resp.message = "Se cargo la temperatura con exito";
    resp.id = temperatura.getId();

    return ResponseEntity.ok(resp);

  }

  /*
   * GET /temperaturas/paises/{codigoPais} : que devuelva la lista de temperaturas
   * con sus años de un país especifico, indicado por “codigoPais”.
   */

  @GetMapping("temperaturas/paises/{codigoPais}")
  public ResponseEntity<List<Temperatura>> listarTemperaturas(@PathVariable int codigoPais) {

    return ResponseEntity.ok(temperaturaService.listarTemperatura());

  }

  /*
   * DELETE /temperaturas/{id}: no se borrará la temperatura id, deberá cambiar el
   * año a 0.
   */

  @DeleteMapping("/temperaturas/{id}")
  public ResponseEntity<?> borrarTemperatura(@PathVariable int id) {

    Temperatura temperatura = temperaturaService.buscarPorTemperaturaId(id);

    if (temperatura != null) {

      temperaturaService.borrarTemperatura(temperatura);

      GenericResponse resp = new GenericResponse();

      resp.isOk = true;
      resp.id = temperatura.getId();
      resp.message = "Se borro la temperatura con exito";

      return ResponseEntity.ok(resp);

    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

  }

  /*
   * GET /temperaturas/anios/{anio} : que devuelva la lista de temperaturas de un
   * año en particular en el siguiente formato JSON Array: [{ “nombrePais”:
   * “Argentina”, “grados”: 29 }, { “nombrePais”: “Venezuela”, “grados”: 45 }]
   */
  @GetMapping("/temperaturas/anios/{anio}")
  public ResponseEntity<List<TemperaturaAnioResponse>> listarTemperaturasPorAnio(@PathVariable Integer anio) {

    List<TemperaturaAnioResponse> resultado = new ArrayList<>();

    // Obtengo la lista de temperaturas anuales.
    List<Temperatura> tempsPorAnio = temperaturaService.buscarPorAnio(anio);

    // Recorres y agregas temperatura de un formato, a otro formato, o sea, la
    // "mapeas"
    for (Temperatura t : tempsPorAnio) {
      TemperaturaAnioResponse t2 = new TemperaturaAnioResponse();
      t2.nombrePais = t.getPais().getNombre();
      t2.grados = t.getTemperatura();
      resultado.add(t2);
    }

    return ResponseEntity.ok(resultado);

  }

  /*
   * GET /temperaturas/maximas/{codigoPais} : que devuelva la temperatura máxima
   * para un país en particular en este formato JSON(informar el año en que
   * ocurrió) { “nombrePais”: “Venezuela”, “temperaturaMaxima”: 45, “anio”: 2011 }
   */

  @GetMapping("/temperaturas/maximas/{codigoPais}")
  public ResponseEntity<TemperaturaMaxResponse> buscarTempMax(@PathVariable Integer codigoPais) {

    TemperaturaMaxResponse tempMaxResponse = new TemperaturaMaxResponse();

    Temperatura tempMax = temperaturaService.buscarTempMax(codigoPais);

    tempMaxResponse.nombrePais = tempMax.getPais().getNombre();
    tempMaxResponse.temperaturaMaxima = tempMax.getTemperatura();
    tempMaxResponse.anio = tempMax.getAnio();

    return ResponseEntity.ok(tempMaxResponse);

  }
  
}