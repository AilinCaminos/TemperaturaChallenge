package ar.com.ada.api.nasa.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemperaturaController {

/**
 * POST /temperaturas : que registre una temperatura de un país en un año específico
RequestBody {
“codigoPais”: 32,
“anio”: 2010,
“grados”: 38.6
}
Respuesta Esperada(JSON):
{
“id”: 25 //O cualquier número de temperatura que devuelva.
}

 */

 /**
  * GET /temperaturas/paises/{codigoPais} : que devuelva la lista de temperaturas con sus años de un
    país especifico, indicado por “codigoPais”.
    
    DELETE /temperaturas/{id}: no se borrará la temperatura id, deberá cambiar el año a 0. 
  */

}