package org.morgorithm.websocket.repository;

import org.morgorithm.websocket.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
    @Query("select count(f) from Facility f")
    int getFacilityNum();

    @Query("select f.building from Facility f")
    List<Object[]> getFacilityNames();

    @Query("select count(f) from Facility f")
    List<Object[]> getFacilityBno();

    @Query("select distinct f.bno from Facility f")
    List<Long> getAllBnos();
}
