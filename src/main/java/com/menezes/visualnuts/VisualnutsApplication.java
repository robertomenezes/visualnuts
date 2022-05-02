package com.menezes.visualnuts;

import com.menezes.visualnuts.languages.service.LanguagesService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VisualnutsApplication {

    public static final String CONTRY_WITH_THE_MOST_OFFICIAL_LANGUAGE_WHERE_THEY_SPEAK = "de";
    public static final String PATH_FILE = "data/languages_countries.json";

    public static void main(String[] args) {
        SpringApplication.run(VisualnutsApplication.class, args);

//        LanguagesService languagesService = new LanguagesService();
//        try {
//            languagesService.languagesStatics(PATH_FILE, CONTRY_WITH_THE_MOST_OFFICIAL_LANGUAGE_WHERE_THEY_SPEAK);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
