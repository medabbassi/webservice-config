package com.example.movies.controller;

import com.example.movies.dto.MovieDto;
import com.example.movies.entity.Movies;
import com.example.movies.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/movies")
@CrossOrigin(origins = "*", allowedHeaders = "*") // Allow all origins and headers
public class MoviesController {
    private  final MovieService movieService;

    @GetMapping
    public List<Movies> getAllMovies(){
        return movieService.getAllMovies();
    }
    @GetMapping("/get-movie-by-movie-title/{movieTitle}")
    public ResponseEntity<List<MovieDto>> getMovieByTitle(@PathVariable String movieTitle) {
        List<Movies> movies = movieService.findByTitle(movieTitle);
        if (movies == null || movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<MovieDto> movieDtos = movies.stream()
                .map(movie -> new MovieDto(movie.getTitle(), movie.getGenre(), movie.getYear(), movie.getRating(), movie.getDuration()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(movieDtos, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMovie(@RequestBody Movies movie){
        movieService.saveMovie(movie);
    }


}
