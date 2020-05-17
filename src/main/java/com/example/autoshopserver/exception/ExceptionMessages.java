package com.example.autoshopserver.exception;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Data
public class ExceptionMessages {

    @Value("${exception.CarNotFound.message}")
    String carNotFoundMessage;

    @Value("${exception.BrandNotFound.message}")
    String brandNotFoundMessage;

    @Value("${exception.UserNotFound.message}")
    String userNotFoundMessage;



    @Value("${exception.ParseException.message}")
    String parseExceptionMessage;

    @Value("${exception.NullPointerException.message}")
    String NullPointerExceptionMessage;

    @Value("${exception.IOException.message}")
    String IOExceptionMessage;

    @Value("${exception.NotAuthorizedException.message}")
    String NotAuthorizedExceptionMessage;

    @Value("${exception.CreateJsonException.message}")
    String CreateJsonExceptionMessage;

    @Value("${exception.AccessDeniedException.message}")
    String AccessDeniedExceptionMessage;

}
