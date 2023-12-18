package oni.onijpa.service;

import oni.onijpa.domain.Member;
import oni.onijpa.repository.MemberRepository;
import oni.onijpa.repository.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
    MemberRepository memberRepository;
    MemberService service;

    @BeforeEach
    void clearMemoryRepository() {
        this.memberRepository = new MemoryMemberRepository();
        this.service = new MemberService(this.memberRepository);
    }

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setEmail("aaa@aaa.aaa");
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