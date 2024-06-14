package com.example.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.backend.models.Museum;

import java.util.Optional;

@Repository
public interface MuseumRepository extends JpaRepository<Museum, Long>
{
    Optional<Museum> findByName(String name);
}