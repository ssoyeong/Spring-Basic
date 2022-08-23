package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // 이름 기반 검색은 공통이 아니므로 따로 작성
    // select m from Member where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
