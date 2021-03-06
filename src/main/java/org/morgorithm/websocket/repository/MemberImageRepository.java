package org.morgorithm.websocket.repository;

import org.morgorithm.websocket.entity.MemberImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberImageRepository extends JpaRepository<MemberImage, Long> {
    List<MemberImage> findByMemberMno(Long mno);
}
