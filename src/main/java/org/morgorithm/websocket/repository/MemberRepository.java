package org.morgorithm.websocket.repository;


import org.morgorithm.websocket.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

@SuppressWarnings("unchecked")
public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {
    @Query("select m,s, s.facility from Member m Left join Status s on s.member=m where m.mno=:mno")
    List<Object[]> getMemberWithStatus(@Param("mno") Long mno);

    @Query("select m, mi, count(mi) from Member m left outer join MemberImage mi on mi.member=m where m.mno=:mno")
    List<Object[]> getMemberWithAll(Long mno);

    @Query("select m, mi, count(mi) from Member m" + " left outer join MemberImage mi on mi.member=m group by m")
    Page<Object[]> getListPage(Pageable pageable);

    @Query("select m from Member m")
    Page<Object> getMemberList(Pageable pageable);

    @Query("select m.name, m.mno from Member m where m.phone=:p")
    List<Object[]> getNameByPhone(String p);

    @Query("select count(m) from Member m")
    int getMemberNum();

}
