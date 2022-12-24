package br.com.banco.controllers;


import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/transferencias/{id}")
public class TransferenciaController {

    @Autowired
    private TransferenciaService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransferenciaDTO>> get(
            @PathVariable Long id,
            @RequestParam(defaultValue = "") String operador,
            @RequestParam(defaultValue = "0000-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicio,
            @RequestParam(defaultValue = "9999-12-31") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFim){
        return new ResponseEntity<>(
                (operador.equals("") && dataInicio.getYear()==0 && dataFim.getYear()==0) ?  service.read(id) :
                        (operador.equals("")) ? service.read(id,dataInicio,dataFim) :
                                service.read(id,operador,dataInicio,dataFim)
                ,HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransferenciaDTO> post(@RequestBody TransferenciaDTO dto){
        return new ResponseEntity<>(service.create(dto),HttpStatus.CREATED);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransferenciaDTO> put(@PathVariable Long id,@RequestBody TransferenciaDTO dto){
        return new ResponseEntity<>(service.update(id,dto),HttpStatus.ACCEPTED);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransferenciaDTO> delete(@PathVariable Long id){
        return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
    }
}
