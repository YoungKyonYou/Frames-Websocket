package org.morgorithm.websocket.repository;




import org.morgorithm.websocket.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Long>, QuerydslPredicateExecutor<Status> {
    @Query("select s.facility, count(s.state), s.facility.bno, s.state, s.regDate from Status s WHERE (s.regDate >= :timeFrom AND s.regDate < :timeTo) group by s.facility, s.state order by s.facility.bno")
    List<Object[]> getFacilityInInfoOneDay(@Param("timeFrom") LocalDateTime fromTime, @Param("timeTo") LocalDateTime timeTo);



    @Query(value = "select s from Status s where s.statusnum > :lastStatusNum order by s.statusnum desc")
    List<Status> findRecentStatusList(Long lastStatusNum);


}
