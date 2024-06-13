package com.example.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.models.Museum;

public interface MuseumRepository extends JpaRepository<Museum, Long> {

}
