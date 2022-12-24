package br.com.banco.utils;

import br.com.banco.exceptions.IdNotFoundException;
import br.com.banco.exceptions.NullFieldsException;
import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class ExceptionThrowller {

    public void IfNullThrow(Object dto) throws NullFieldsException {
        try {
            for (Field f : dto.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(dto) == null && !f.getName().equals("id") && !f.getName().equals("nomeOperadorTransacao")) {
                    System.out.println(dto);
                    throw new NullFieldsException();
                }
            }
        }catch (IllegalAccessException ex){

        }
    }

    public Object FindOrThrow(Long id, JpaRepository repository) throws IdNotFoundException {
        try {
            return repository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new IdNotFoundException();
        }
    }
}