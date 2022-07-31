package com.example.userService.external.adapter;


import com.example.userService.exception.CustomException;
import com.example.userService.external.response.PinCodeResponseEntity;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PostalPinCodeAdapterImpl extends AbstractAdapter implements PostalPinCodeAdapter {

  @Value("${external.api.post-office.url}/pincode/")
  private String getPostOfficeUrl;

  /**
   * @param pinCode
   * @return
   * @throws CustomException
   */
  public List<PinCodeResponseEntity> getPostalPinCodeResponse(String pinCode)
      throws CustomException {
    URI url = URI.create(getPostOfficeUrl + pinCode);
    return getExternalApiResponse(url, getBaseHeaders(), List.class);
  }

}
