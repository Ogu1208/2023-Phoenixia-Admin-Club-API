package com.example.club.Repository;

import com.example.club.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    List<Club> findByName(String name);
    List<Club> findByNameContaining(String name);
    List<Club> findByDescriptionContaining(String description);

}
