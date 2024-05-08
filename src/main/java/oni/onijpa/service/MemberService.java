package oni.onijpa.service;

import oni.onijpa.domain.Member;
import oni.onijpa.repository.MemberRepository;
import oni.onijpa.repository.NewMemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final NewMemberRepository newMemberRepository;

    public MemberService(MemberRepository memberRepository, NewMemberRepository newMemberRepository) {
        this.memberRepository = memberRepository;
        this.newMemberRepository = newMemberRepository;
    }

    public Long join(Member member) {
        validateDuplicateEmail(member);
        //memberRepository.save(member);
        newMemberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers() {
        return newMemberRepository.findAll();
    }

    public Optional<Member> findMember(Long memberId) {
        //return memberRepository.findById(memberId);
        return Optional.ofNullable(newMemberRepository.find(memberId));
    }

    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findByEmail(email).stream().findAny();
    }

    public void deleteMember(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        memberRepository.deleteById(member.orElseThrow().getId());
    }

    private void validateDuplicateEmail(Member member) {
        memberRepository.findByEmail(member.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("중복 이메일!!");
                });
    }
}
