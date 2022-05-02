package com.menezes.visualnuts.languages.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.menezes.visualnuts.exception.BusinessException;
import com.menezes.visualnuts.languages.dto.Countries;
import com.menezes.visualnuts.languages.dto.Country;
import com.menezes.visualnuts.util.Util;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.menezes.visualnuts.languages.constants.Constants.*;
import static com.menezes.visualnuts.util.Util.readFromInputStream;
import static com.menezes.visualnuts.util.Util.stringIsNullOrEmpty;

@Service
public class LanguagesService {

    public static final String PATH_FILE = "data/languages_countries.json";
    public static final String CONTRY_WITH_THE_MOST_OFFICIAL_LANGUAGE_WHERE_THEY_SPEAK = "de";
    public List<Country> countries;

    @CacheEvict(value = "countries")
    public void uploadFile(MultipartFile file) throws Exception {
        countries = getCountries(file);
    }

    @Cacheable(value = "countries")
    public List<Country> getCountries() throws Exception {

        if (countries != null) {
            return countries;
        }

        if (stringIsNullOrEmpty(PATH_FILE)) {
            throw new BusinessException(INVALID_PATH);
        }

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(PATH_FILE);
        if (inputStream == null) {
            throw new BusinessException("File not found");
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type listType = new TypeToken<ArrayList<Country>>(){}.getType();

        return gson.fromJson(readFromInputStream(inputStream), listType);
    }

    public List<Country> getCountries(MultipartFile pathFile) throws Exception {
        return new ObjectMapper().readValue(pathFile.getBytes(), Countries.class).getCountries();
    }

    public int printNumberOfCountries() throws Exception {
        List<Country> countries = getCountries();
        return countries.size();
    }

    public AtomicReference<String> printCountryWithTheMostOfficialLanguages(String countryWithTheMostOfficialLanguageWhereTheySpeak) throws Exception {
        if (Util.stringIsNullOrEmpty(countryWithTheMostOfficialLanguageWhereTheySpeak)) {
            countryWithTheMostOfficialLanguageWhereTheySpeak = CONTRY_WITH_THE_MOST_OFFICIAL_LANGUAGE_WHERE_THEY_SPEAK;
        }
        List<Country> countries = getCountries();
        AtomicReference<String> mostOfficialLanguage = new AtomicReference<>();

        String finalCountryWithTheMostOfficialLanguageWhereTheySpeak = countryWithTheMostOfficialLanguageWhereTheySpeak;
        countries.stream()
                .filter(c -> c.getLanguages().contains(finalCountryWithTheMostOfficialLanguageWhereTheySpeak.toLowerCase()))
                .max(Comparator.comparing(Country::getLanguagesListSize)).ifPresentOrElse(c -> mostOfficialLanguage.set(c.getCountry()), () -> mostOfficialLanguage.set(""));
        return mostOfficialLanguage;
    }

    public int howManyOfficialLanguages() throws Exception {
        List<Country> countries = getCountries();
        Set<String> languages = new HashSet<>();
        countries.forEach(c -> languages.addAll(c.getLanguages()));
        return languages.size();
    }

    public String coutryWithTheHighestNumberOfOfficialLanguages() throws Exception {
        List<Country> countries = getCountries();
        Country country = new Country();
        countries.stream().max(Comparator.comparing(Country::getLanguagesListSize)).ifPresent(c -> country.setCountry(c.getCountry()));
        return country.getCountry();
    }

    public Stream<Map.Entry<String, Long>> mostCommonOfficialLanguages() throws Exception {
        List<Country> countries = getCountries();
        List<String> commonsLanguages = new ArrayList<>();
        countries.forEach(c -> commonsLanguages.addAll(c.getLanguages()));
        Map<String, Long> collect = commonsLanguages.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return collect.entrySet()
        .stream()
        .filter(l -> l.getValue().equals((collect.entrySet().stream().max(Map.Entry.comparingByValue())).get().getValue()));
    }

}
