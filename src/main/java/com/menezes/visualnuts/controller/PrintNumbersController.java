package com.menezes.visualnuts.controller;

import com.menezes.visualnuts.exception.BusinessException;
import com.menezes.visualnuts.printnumbers.PrintNumbersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/printNumebers")
public class PrintNumbersController {

    private final PrintNumbersService service;


    public PrintNumbersController(PrintNumbersService service) {
        this.service = service;
    }

    @GetMapping("{maxNumber}")
    public ResponseEntity<List<String>> printNumbers(@PathVariable String maxNumber) throws NumberFormatException {
        return ResponseEntity.ok(service.verifyNumberAndPrint(Integer.parseInt(maxNumber)));
    }
}
