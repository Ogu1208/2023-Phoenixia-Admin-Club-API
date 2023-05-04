package com.example.club.controller;

import com.example.club.Repository.ClubRepository;
import com.example.club.domain.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api")
public class ClubController {
    @Autowired
    private ClubRepository clubRepository;


    @GetMapping("/clubs")
    List<Club> all(@RequestParam(required = false, defaultValue = "") String name) {
        if (!StringUtils.hasText(name)) {
            return clubRepository.findAll();
        } else {
            return clubRepository.findByNameContaining(name);
        }

    }
    // end::get-aggregate-root[]

    @PostMapping("/clubs")
    Club newClub(@RequestBody Club newClub) {
        return clubRepository.save(newClub);
    }

    // Single item

    @GetMapping("/clubs/{id}")
    Club one(@PathVariable Long id) {

        return clubRepository.findById(id)
                .orElse(null);
    }

    @PutMapping("/clubs/{id}")
    Club replaceClub(@RequestBody Club newClub, @PathVariable Long id) {

        return clubRepository.findById(id)
                .map(club -> {
                    club.setName(newClub.getName());
                    club.setDescription(newClub.getDescription());
                    return clubRepository.save(club);
                })
                .orElseGet(() -> {
                    newClub.setId(id);
                    return clubRepository.save(newClub);
                });
    }

    @DeleteMapping("/clubs/{id}")
    void deleteClub(@PathVariable Long id) {
        clubRepository.deleteById(id);
    }
}
