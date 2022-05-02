package com.menezes.visualnuts.controller;

import com.menezes.visualnuts.languages.constants.Constants;
import com.menezes.visualnuts.languages.service.LanguagesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

@RestController
@RequestMapping("/languages")
public class LanguageController {

    private final LanguagesService service;

    public LanguageController(LanguagesService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) throws Exception {
        service.uploadFile(file);
        return ResponseEntity.ok(Constants.UPLOAD_SUCCESS);
    }

    @GetMapping("/count-countries")
    public ResponseEntity<Integer> countCountries() throws Exception {
        return ResponseEntity.ok(service.printNumberOfCountries());
    }

    @GetMapping("/country-with-the-most-official-languages")
    public ResponseEntity<AtomicReference<String>> countryWithTheMostOfficialLanguages(@RequestParam(required = false, value = "country") String country) throws Exception {
        return ResponseEntity.ok(service.printCountryWithTheMostOfficialLanguages(country));
    }

    @GetMapping("/how-many-official-languages")
    public ResponseEntity<Integer> HowManyOfficialLanguages() throws Exception {
        return ResponseEntity.ok(service.howManyOfficialLanguages());
    }

    @GetMapping("/country-with-the-highest-number-of-official-languages")
    public ResponseEntity<String> coutryWithTheHighestNumberOfOfficialLanguages() throws Exception {
        return ResponseEntity.ok(service.coutryWithTheHighestNumberOfOfficialLanguages());
    }

    @GetMapping("/most-common-official-languages")
    public ResponseEntity<Stream<Map.Entry<String, Long>>> mostCommonOfficialLanguages() throws Exception {
        return ResponseEntity.ok(service.mostCommonOfficialLanguages());
    }
}
