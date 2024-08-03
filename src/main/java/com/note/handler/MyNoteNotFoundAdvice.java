package com.note.handler;

import com.note.exception.MyNoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MyNoteNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(MyNoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String emplyeeNotFoundhandler(MyNoteNotFoundException e) {
        return e.getMessage();
    }
}
