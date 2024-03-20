package oni.onijpa.service;

import oni.onijpa.domain.Member;
import oni.onijpa.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
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

    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findByEmail(email).stream().findAny();
    }

    public Optional<Member> updateMemberName(Member member) {
        memberRepository.save(member);

        return Optional.ofNullable(member);
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
