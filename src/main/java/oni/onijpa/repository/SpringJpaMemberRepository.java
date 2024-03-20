package oni.onijpa.repository;

import oni.onijpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByEmail(String email);

    @Override
    Optional<Member> findByName(String name);

    @Override
    void deleteById(Long id);
}
