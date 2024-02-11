package br.com.kaliware.service.cepfinder.cepfinder.service.api.cep;

public interface CepLookupService<T> {
    T lookupCep(String cep);
}