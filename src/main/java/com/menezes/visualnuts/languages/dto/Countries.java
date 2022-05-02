package com.menezes.visualnuts.languages.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Countries {

    private List<Country> countries;
}
