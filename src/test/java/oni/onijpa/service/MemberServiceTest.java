package oni.onijpa.service;

import oni.onijpa.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService service;

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setEmail("aaa@ccc.aaa");
        member.setName("회원1");

        // when
        Long savedMemberId = service.join(member);

        // then
        Member savedMember = service.findMember(savedMemberId).get();
        assertThat(savedMember.getEmail()).isEqualTo(member.getEmail());
    }

    @Test
    void joinException() {
        // given
        Member member1 = new Member();
        member1.setEmail("aaa@aaa.com");

        Member member2 = new Member();
        member2.setEmail("aaa@aaa.com");

        // when
        service.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> service.join(member2));

        // then
        assertThat(e.getMessage()).isEqualTo("중복 이메일!!");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findMember() {
    }
}