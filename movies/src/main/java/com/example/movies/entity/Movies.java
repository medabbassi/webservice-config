package com.example.movies.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idMovie", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "rating", nullable = false)
    private double rating;

    @Column(name = "duration", nullable = false)
    private int duration;


}
