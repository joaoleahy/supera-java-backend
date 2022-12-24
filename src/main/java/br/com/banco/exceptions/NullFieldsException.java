package br.com.banco.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Null fields aren't acceptable")
public class NullFieldsException extends RuntimeException{

    public static final long serialVersionUID = 1L;

    public NullFieldsException(String msg){
        super(msg);
    }

}
