package br.com.kaliware.service.cepfinder.cepfinder.service.exception.api;

public class ApiException extends RuntimeException {

  private String apiResponse;

  public ApiException(String message) {
    super(message);
  }

  public ApiException(String message, String apiResponse) {
    super(message);
    this.apiResponse = apiResponse;
  }

  public ApiException(String message, Throwable cause) {
    super(message, cause);
  }

  public ApiException(String message, Throwable cause, String apiResponse) {
    super(message, cause);
    this.apiResponse = apiResponse;
  }

  public ApiException(Throwable cause) {
    super(cause);
  }

  public ApiException(Throwable cause, String apiResponse) {
    super(cause);
    this.apiResponse = apiResponse;
  }


  public String getApiResponse() {
    return apiResponse;
  }
}
