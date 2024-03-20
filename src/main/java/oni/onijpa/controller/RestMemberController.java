package oni.onijpa.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import oni.onijpa.domain.Member;
import oni.onijpa.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "회원 API", description = "회원 기능 API")
@RestController
@RequestMapping("/api")
public class RestMemberController {
    private final MemberService memberService;

    @Autowired
    public RestMemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Operation(summary = "회원 목록 조회", description = "회원 목록 전체 조회")
    @GetMapping("/members")
    public List<Member> members() {
        return memberService.findMembers();
    }

    @Operation(summary = "회원 등록", description = "신규 회원 정보 등록")
    @Parameter(name = "name", description = "이름")
    @Parameter(name = "email", description = "이메일")
    @PostMapping("/members/new")
    public Optional<Member> signUp(@RequestParam(name = "name", required = true) String name, @RequestParam(name = "email", required = true) String email) {
        Member member = new Member();
        member.setName(name);
        member.setEmail(email);
        memberService.join(member);

        return memberService.findMember(member.getId());
    }

    @Operation(summary = "회원 수정", description = "회원 정보 수정 : 이름")
    @Parameter(name = "email", description = "이메일")
    @Parameter(name = "name", description = "이름")
    @PutMapping("/member")
    public Optional<Member> editMember(@RequestParam(name = "name", required = true) String name, @RequestParam(name = "email", required = true) String email) {
        Member member = new Member();
        member.setName(name);
        member.setEmail(email);
        memberService.updateMemberName(member);

        return memberService.findMember(member.getId());
    }

    @Operation(summary = "회원 삭제", description = "회원 정보 삭제")
    @Parameter(name = "email", description = "이메일")
    @DeleteMapping("/member")
    public void deleteMember(@RequestParam(name = "email", required = true) String email) {
        memberService.deleteMember(email);
    }



    @Operation(summary = "회원 조회", description = "회원 단건 정보 조회")
    @Parameter(name = "email", description = "이메일")
    @GetMapping("/member")
    public Optional<Member> member(@RequestParam(name = "email", required = true) String email) {
        return memberService.findMemberByEmail(email);
    }
}
