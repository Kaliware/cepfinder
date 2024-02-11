package br.com.kaliware.service.cepfinder.cepfinder.controller.cep;

import br.com.kaliware.service.cepfinder.cepfinder.controller.cep.exception.StandardError;
import br.com.kaliware.service.cepfinder.cepfinder.record.address.viacep.AddressViaCepRecord;
import br.com.kaliware.service.cepfinder.cepfinder.service.api.cep.viacep.ViaCepLookupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/viacep")
@Tag(name = "API ViaCep", description = "Operações relacionadas à consulta de endereço na API ViaCep")
public class ViaCepController {

  private final ViaCepLookupService viaCepLookupService;

  @Autowired
  public ViaCepController(ViaCepLookupService viaCepLookupService) {
    this.viaCepLookupService = viaCepLookupService;
  }

  @Operation(
     summary = "Consultar endereço por CEP",
     description = "Consulta o endereço correspondente ao CEP especificado.",
     responses = {
        @ApiResponse(
           description = "OK",
           responseCode = "200",
           content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = AddressViaCepRecord.class)))
     }
  )
  @GetMapping("/{cep}")
  public ResponseEntity<AddressViaCepRecord> viaCepLookupCep(
     @Parameter(description = "CEP a ser consultado", required = true) @PathVariable String cep) {
    return ResponseEntity.ok(viaCepLookupService.lookupCep(cep));
  }

  @Operation(
     summary = "Consultar endereço por UF, cidade e rua",
     description = "Consulta o endereço correspondente ao UF, cidade e rua especificados.",
     responses = {
        @ApiResponse(
           description = "OK",
           responseCode = "200",
           content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = AddressViaCepRecord.class))
        )
     }
  )
  @GetMapping("/{uf}/{city}/{street}")
  public ResponseEntity<Collection<AddressViaCepRecord>> viaCepLookupAddress(
     @Parameter(description = "UF do endereço", required = true) @PathVariable String uf,
     @Parameter(description = "Cidade do endereço", required = true) @PathVariable String city,
     @Parameter(description = "Rua do endereço", required = true) @PathVariable String street) {
    return ResponseEntity.ok(viaCepLookupService.lookupCep(uf, city, street));
  }
}
