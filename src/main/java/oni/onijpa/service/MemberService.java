package oni.onijpa.service;

import oni.onijpa.domain.Member;
import oni.onijpa.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        validateDuplicateEmail(member);
        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private void validateDuplicateEmail(Member member) {
        memberRepository.findByEmail(member.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("중복 이메일!!");
                });
    }
}
