package com.cinema.domain.repositories;

import com.cinema.domain.entities.MovieSchedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.List;

public interface MovieScheduleRepository extends CrudRepository<MovieSchedule, Integer> {

    @Query(value = "SELECT * FROM movie_schedule WHERE movie_id = :movieId AND date_time >= :now ORDER BY date_time ASC", nativeQuery = true)
    List<MovieSchedule> findNewSchedulesForMovie(Integer movieId, Instant now);

    @Query("SELECT ms FROM MovieSchedule ms WHERE dateTime < ?1 ORDER BY dateTime DESC")
    List<MovieSchedule> findMoviesScheduledBefore(Instant dateTime);

    @Query("SELECT ms FROM MovieSchedule ms WHERE dateTime > ?1 ORDER BY dateTime ASC")
    List<MovieSchedule> findMoviesScheduledAfter(Instant dateTime);
}
