package br.com.kaliware.service.cepfinder.cepfinder.service.api.cep.viacep;

import br.com.kaliware.service.cepfinder.cepfinder.record.address.viacep.AddressViaCepRecord;
import br.com.kaliware.service.cepfinder.cepfinder.service.api.cep.CepLookupService;

import java.util.Collection;

public interface ViaCepLookupService extends CepLookupService<AddressViaCepRecord> {

  Collection<AddressViaCepRecord> lookupCep(String uf, String city, String street);

}
