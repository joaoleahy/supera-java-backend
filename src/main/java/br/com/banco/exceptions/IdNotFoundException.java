package br.com.banco.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Id not found")
public class IdNotFoundException extends RuntimeException{

    public static final long serialVersionUID = 1L;

    public IdNotFoundException(String msg){
        super(msg);
    }
}