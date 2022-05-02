package com.menezes.visualnuts.languages.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    private String country;
    private List<String> languages;

    public int getLanguagesListSize() {
        return languages.size();
    }

}
