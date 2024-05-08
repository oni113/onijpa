package oni.onijpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import oni.onijpa.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewMemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
