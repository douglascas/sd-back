package com.car.rental.service.fipe;

import com.car.rental.dto.fipe.ManufacturerDTO;
import com.car.rental.dto.fipe.ModelDTO;
import com.car.rental.dto.ModelYearDTO;
import com.car.rental.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FipeService {

    @Autowired
    private RestService restService;

    @Transactional
    public ResponseEntity<List<ManufacturerDTO>> manufactures() {
        String uri = "http://fipeapi.appspot.com/api/1/carros/marcas.json";
        HttpEntity<?> body = new HttpEntity<>(new HttpHeaders());

        return this.restService.doRequest(uri, HttpMethod.GET, body, new ParameterizedTypeReference<List<ManufacturerDTO>>() {});
    }

    @Transactional
    public ResponseEntity<List<ModelDTO>> models(Integer manufactureId) {
        String uri = "http://fipeapi.appspot.com/api/1/carros/veiculos/"+ manufactureId +".json";
        HttpEntity<?> body = new HttpEntity<>(new HttpHeaders());

        return this.restService.doRequest(uri, HttpMethod.GET, body, new ParameterizedTypeReference<List<ModelDTO>>() {});
    }

    @Transactional
    public ResponseEntity<List<ModelYearDTO>> modelsYear(Integer manufactureId, Integer modelId) {
        String uri = "http://fipeapi.appspot.com/api/1/carros/veiculo/"+ manufactureId +"/"+ modelId +".json";
        HttpEntity<?> body = new HttpEntity<>(new HttpHeaders());

        return this.restService.doRequest(uri, HttpMethod.GET, body, new ParameterizedTypeReference<List<ModelYearDTO>>() {});
    }


}
