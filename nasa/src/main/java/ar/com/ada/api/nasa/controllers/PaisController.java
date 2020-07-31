package ar.com.ada.api.nasa.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import ar.com.ada.api.nasa.entities.Pais;
import ar.com.ada.api.nasa.models.request.CreacionPaisRequest;
import ar.com.ada.api.nasa.models.response.GenericResponse;
import ar.com.ada.api.nasa.services.PaisService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class PaisController {

   @Autowired
   PaisService paisService;

   /*
    * POST /paises : que permita la creación de un país RequestBody:
    * 
    * { “codigoPais”: 32, “nombre”: “Argentina” }
    */

   @PostMapping("/paises")
   public ResponseEntity<GenericResponse> crearPais(@RequestBody CreacionPaisRequest pais) {

      paisService.crearPais(pais.codigoPais, pais.nombre);

      GenericResponse resp = new GenericResponse();
      resp.isOk = true;
      resp.message = "Se agrego el pais " + pais.nombre + " con exito";

      return ResponseEntity.ok(resp);

   }

   /*
    * GET /paises : que devuelva la lista de países SIN las temperaturas.
    */

   @GetMapping("/paises")
   public ResponseEntity<List<Pais>> listarPaises() {

      return ResponseEntity.ok(paisService.listarPaises());

   }

   /*
    * GET /paises/{id} : que devuelva la info de un pais en particular(SIN las
    * temperaturas)
    */

   @GetMapping("/paises/{codigoPais}")
   public ResponseEntity<Pais> buscarPorCodigoPais(@PathVariable int codigoPais) {

      return ResponseEntity.ok(paisService.buscarPorCodigoPais(codigoPais));

   }

   /*
    * PUT /paises/{id} : que actualice solo el nombre del país. Usar un requestBody
    * que solo tenga el nombre del país.
    */

   @PutMapping("/paises/{codigoPais}")
   public ResponseEntity<GenericResponse> actualizarNombrePais(@PathVariable int codigoPais,@RequestBody String nombre) {

      Pais paisOriginal = paisService.buscarPorCodigoPais(codigoPais);

      if (paisOriginal != null) {

         paisService.actualizarNombrePais(paisOriginal, nombre);

         GenericResponse resp = new GenericResponse();

         resp.isOk = true;
         resp.message = "Se actualizo con exito el nombre";

         return ResponseEntity.ok(resp);

      } else {

         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

   }

}