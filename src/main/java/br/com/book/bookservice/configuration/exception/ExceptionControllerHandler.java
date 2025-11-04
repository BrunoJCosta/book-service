package br.com.book.bookservice.configuration.exception;

import br.com.book.bookservice.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionControllerHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public Response badRequest(NotFoundException exception) {
        return Response.badRequest(exception.getMessage());
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    @ExceptionHandler(NotFoundException.class)
//    public Response badRequest(NotFoundException exception) {
//        return Response.badRequest(exception.getMessage());
//    }


}
