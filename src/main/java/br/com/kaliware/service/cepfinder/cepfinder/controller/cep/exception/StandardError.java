package br.com.kaliware.service.cepfinder.cepfinder.controller.cep.exception;

import java.time.Instant;
import java.util.Collection;

public record StandardError(
   Instant timestamp,
   Integer status,
   Collection<String> errors,
   String path

) {

}