package br.com.kaliware.service.cepfinder.cepfinder.service.exception.api;

public class ApiTimeoutException extends ApiException {

  public ApiTimeoutException(String message) {
    super(message);
  }

  public ApiTimeoutException(String message, String apiResponse) {
    super(message, apiResponse);
  }

  public ApiTimeoutException(String message, Throwable cause) {
    super(message, cause);
  }

  public ApiTimeoutException(String message, Throwable cause, String apiResponse) {
    super(message, cause, apiResponse);
  }

  public ApiTimeoutException(Throwable cause) {
    super(cause);
  }

  public ApiTimeoutException(Throwable cause, String apiResponse) {
    super(cause, apiResponse);
  }
}
