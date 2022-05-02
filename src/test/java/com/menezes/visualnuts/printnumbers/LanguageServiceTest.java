package com.menezes.visualnuts.printnumbers;


import com.menezes.visualnuts.languages.dto.Country;
import com.menezes.visualnuts.languages.service.LanguagesService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LanguageServiceTest {

    public static final String PATH_FILE = "data/languages_countries.json";

    private LanguagesService languagesService;

    @BeforeAll
    public void init() {
        languagesService = new LanguagesService();
    }

    private List<Country> givenCountriesList() {
        List<Country> countries = new ArrayList<>();
        countries.add(Country.builder().country("US").languages(Collections.singletonList("en")).build());
        countries.add(Country.builder().country("BE").languages(Arrays.asList("nl","fr","de")).build());
        countries.add(Country.builder().country("NL").languages(Arrays.asList("nl","fy")).build());
        countries.add(Country.builder().country("DE").languages(Collections.singletonList("de")).build());
        countries.add(Country.builder().country("ES").languages(Collections.singletonList("es")).build());
        countries.add(Country.builder().country("CM").languages(Arrays.asList("en","fr")).build());
        countries.add(Country.builder().country("CD").languages(Arrays.asList("en","fr")).build());

        return countries;
    }

    @Test
    void getCountriesFromFile() throws Exception {
        assertTrue(!languagesService.getCountries().isEmpty());
    }

    @Test
    void printNumberOfCountries() throws Exception {
        assertEquals(7, languagesService.printNumberOfCountries());
    }

    @Test
    void printCountryWithTheMostOfficialLanguages() throws Exception {
        assertEquals("BE", languagesService.printCountryWithTheMostOfficialLanguages("de").toString());
    }

    @Test
    void printHowManyOfficialLanguages() throws Exception {
        assertEquals(6, languagesService.howManyOfficialLanguages());
    }

    @Test
    void printCoutryWithTheHighestNumberOfOfficialLanguages() throws Exception {
        assertEquals("BE", languagesService.coutryWithTheHighestNumberOfOfficialLanguages());
    }

    @Test
    void printMostCommonOfficialLanguages() throws Exception {
        assertEquals("en", languagesService.mostCommonOfficialLanguages().findFirst().get().getKey());
    }
}