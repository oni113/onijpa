package oni.onijpa;

import oni.onijpa.repository.MemberRepository;
import oni.onijpa.repository.NewMemberRepository;
import oni.onijpa.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    private final NewMemberRepository newMemberRepository;

    public SpringConfig(MemberRepository memberRepository, NewMemberRepository newMemberRepository) {
        this.memberRepository = memberRepository;
        this.newMemberRepository = newMemberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository, newMemberRepository);
    }

    /*
    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
    */
}
