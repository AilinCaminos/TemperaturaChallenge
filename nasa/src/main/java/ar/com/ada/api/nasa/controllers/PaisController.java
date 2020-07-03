package ar.com.ada.api.nasa.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.nasa.models.response.CreacionPaisResponse;
import ar.com.ada.api.nasa.services.PaisService;

@RestController
public class PaisController {
        /**
     * POST /paises : que permita la creación de un país 
     * RequestBody:
     *  
     * {
     * “codigoPais”: 32, 
     * “nombre”: “Argentina” 
     * } 
     * GET /paises : que devuelva la lista
     * de países SIN las temperaturas. 
     * 
     * GET /paises/{id} : que devuelva la info de un
     * pais en particular(SIN las temperaturas) 
     * PUT /paises/{id} : que actualice
     * solo el nombre del país. 
     * Usar un requestBody que solo tenga el nombre del
     * país.
     */

     @Autowired
     PaisService paisService;

     @PostMapping("/paises")
     public ResponseEntity<CreacionPaisResponse> crearPais(@RequestBody CreacionPaisResponse pais, Integer codigoPais, String nombre){


        paisService.crearPais(codigoPais, nombre);

        CreacionPaisResponse paisCreado = new CreacionPaisResponse();

        pais.codigoPais = codigoPais;
        pais.nombre = nombre;

        return ResponseEntity.ok(pais);


     }
}