package com.knu.demo.exception.handler;

import com.knu.demo.entity.ErrorInfo;
import com.knu.demo.exception.BookingNotFoundException;
import com.knu.demo.exception.CarNotFoundException;
import com.knu.demo.exception.NullPasswordException;
import com.knu.demo.exception.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
//404
@ControllerAdvice
public class RestExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            BookingNotFoundException.class,
            CarNotFoundException.class,
            UserNotFound.class
    })
    @ResponseBody
    public ErrorInfo notFoundExceptionHandler(Exception e) {
        return new ErrorInfo().setTimestamp(System.currentTimeMillis())
                .setMessage(e.getMessage())
                .setDeveloperMessage(e.toString());
    }
//400
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            NullPasswordException.class
    })
    //500
    @ResponseBody
    public ErrorInfo badRequestExceptionHandler(Exception e) {
        return new ErrorInfo().setTimestamp(System.currentTimeMillis())
                .setMessage(e.getMessage())
                .setDeveloperMessage(e.toString());
    }
}
