package com.example.userService.external.adapter;

import com.example.userService.exception.CustomException;
import com.example.userService.external.response.PinCodeResponseEntity;
import java.util.List;

public interface PostalPinCodeAdapter {

  List<PinCodeResponseEntity> getPostalPinCodeResponse(String pinCode) throws CustomException;

}

