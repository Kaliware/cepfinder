package br.com.kaliware.service.cepfinder.cepfinder.service.exception.api;

public class ApiSocketException extends ApiException {

  public ApiSocketException(String message) {
    super(message);
  }

  public ApiSocketException(String message, String apiResponse) {
    super(message, apiResponse);
  }

  public ApiSocketException(String message, Throwable cause) {
    super(message, cause);
  }

  public ApiSocketException(String message, Throwable cause, String apiResponse) {
    super(message, cause, apiResponse);
  }

  public ApiSocketException(Throwable cause) {
    super(cause);
  }

  public ApiSocketException(Throwable cause, String apiResponse) {
    super(cause, apiResponse);
  }
}
