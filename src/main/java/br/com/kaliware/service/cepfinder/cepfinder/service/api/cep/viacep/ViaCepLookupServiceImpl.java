package br.com.kaliware.service.cepfinder.cepfinder.service.api.cep.viacep;

import br.com.kaliware.service.cepfinder.cepfinder.record.address.viacep.AddressViaCepRecord;
import br.com.kaliware.service.cepfinder.cepfinder.service.exception.api.ApiSocketException;
import br.com.kaliware.service.cepfinder.cepfinder.service.exception.api.ApiTimeoutException;
import br.com.kaliware.service.cepfinder.cepfinder.service.exception.resource.ResourceNotFoundException;
import br.com.kaliware.service.cepfinder.cepfinder.service.request.RequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.SocketException;
import java.time.Duration;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

@Service
public class ViaCepLookupServiceImpl implements ViaCepLookupService, RequestService<String, AddressViaCepRecord> {

  @Value("${API.VIA.CEP.URL:https://viacep.com.br}")
  private String url;

  @Value("${API.CONSULTA.TIMEOUT:10000}")
  private Long timeout;

  @Override
  public AddressViaCepRecord lookupCep(String cep) {
    String path = String.format("/ws/%s/json/", cep);
    if (sendRequest(path).isEmpty())
      throw new ResourceNotFoundException("Nenhum endereço encontrado para o CEP fornecido: ");
    return sendRequest(path).stream().toList().get(0);
  }

  @Override
  public Collection<AddressViaCepRecord> lookupCep(String uf, String city, String street) {
    String path = String.format("/ws/%s/%s/%s/json/", uf, city, street);
    return sendRequest(path);
  }

  @Override
  public Collection<AddressViaCepRecord> sendRequest(String path){
    WebClient webClient = buildBaseWebClient();

    WebClient.RequestBodySpec requestBodySpec = webClient
       .method(HttpMethod.GET)
       .uri(url + path);

    Flux<AddressViaCepRecord> flux = requestBodySpec
       .retrieve()
       .onStatus(HttpStatusCode::is4xxClientError, response ->
          Mono.error(new ResourceNotFoundException("Nenhum endereço encontrado para o CEP fornecido"))
       )
       .bodyToFlux(AddressViaCepRecord.class)
       .timeout(Duration.ofMillis(timeout))
       .onErrorResume(this::handleError);

    return flux.collectList().block();
  }

}
