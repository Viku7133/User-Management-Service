package com.example.userService.external.adapter;

import com.example.userService.constants.ErrorMessage;
import com.example.userService.exception.CustomException;
import java.net.URI;
import java.util.Collections;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public abstract class AbstractAdapter {

  protected HttpHeaders getBaseHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    headers.setContentType(MediaType.APPLICATION_JSON);
    return headers;
  }

  protected <T> T getExternalApiResponse(URI url, HttpHeaders headers, Class<T> responseClass)
      throws CustomException {
    RestTemplate restTemplate = new RestTemplate();
    try {
      ResponseEntity<T> responseEntity =
          restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<T>(headers), responseClass);
      return responseEntity.getBody();
    } catch (Exception e) {
      throw new CustomException(ErrorMessage.EXTERNAL_SERVICE_EXCEPTION,
          HttpStatus.FAILED_DEPENDENCY.value());
    }
  }

}