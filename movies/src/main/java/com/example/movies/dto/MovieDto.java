package com.example.movies.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class MovieDto {
    Long id;
    String title;
    String genre;
    int year;
    double rating;
    int duration;

    public MovieDto(String title, String genre, int year, double rating, int duration) {
    }
}
