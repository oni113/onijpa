package oni.onijpa;

import oni.onijpa.repository.MemberRepository;
import oni.onijpa.repository.MemoryMemberRepository;
import oni.onijpa.service.MemberService;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
