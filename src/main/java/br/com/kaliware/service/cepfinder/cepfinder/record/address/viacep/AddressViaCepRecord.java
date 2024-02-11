package br.com.kaliware.service.cepfinder.cepfinder.record.address.viacep;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressViaCepRecord(
   @JsonProperty("cep") String zipCode,
   @JsonProperty("logradouro") String street,
   @JsonProperty("complemento") String complement,
   @JsonProperty("bairro") String district,
   @JsonProperty("localidade") String city,
   @JsonProperty("uf") String state,
   @JsonProperty("ibge") String ibgeCode,
   @JsonProperty("gia") String giaCode,
   @JsonProperty("ddd") String areaCode,
   @JsonProperty("siafi") String siafiCode
) {
}
