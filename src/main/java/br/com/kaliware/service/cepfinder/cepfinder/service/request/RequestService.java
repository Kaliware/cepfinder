package br.com.kaliware.service.cepfinder.cepfinder.service.request;

import br.com.kaliware.service.cepfinder.cepfinder.service.exception.api.ApiSocketException;
import br.com.kaliware.service.cepfinder.cepfinder.service.exception.api.ApiTimeoutException;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.SocketException;
import java.util.Collection;
import java.util.concurrent.TimeoutException;


public interface RequestService<Y, Z> {

  Collection<Z> sendRequest(Y dto);

  default WebClient buildBaseWebClient() {
    return WebClient
       .builder()
       .filter(captureInfoRequest())
       .build();
  }

  default ExchangeFilterFunction captureInfoRequest() {
    return (clientRequest, next) -> {
      // Captura e imprime informações da requisição
      System.out.println("[REQUISICAO] - HTTP method: " + clientRequest.method());
      System.out.println("[REQUISICAO] - URI: " + clientRequest.url());
      System.out.println("[REQUISICAO] - Headers: " + clientRequest.headers());
      System.out.println("[REQUISICAO] - attributes: " + clientRequest.attributes());
      System.out.println("[REQUISICAO] - cookies: " + clientRequest.cookies());
      return next.exchange(clientRequest);
    };
  }

  default <T> Mono<T> handleError(Throwable error) {
    if (error instanceof TimeoutException) {
      return Mono.error(new ApiTimeoutException(
         "A requisição excedeu o limite de tempo. Verifique a conexão de rede ou tente novamente mais tarde.",
         error
      ));
    } else if (error instanceof SocketException) {
      return Mono.error(new ApiSocketException("Falha na comunicação com a API.", error));
    } else {
      return Mono.error(error);
    }
  }

}
