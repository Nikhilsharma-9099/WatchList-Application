package com.example.nikhilsharma.watchlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nikhilsharma.watchlist.entity.Movie;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {

}
