package oni.onijpa.controller;

import oni.onijpa.domain.Member;
import oni.onijpa.dto.member.MemberForm;
import oni.onijpa.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String signUpForm() {
        return "/members/signUpForm";
    }

    @PostMapping("/members/new")
    public String signUp(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        member.setEmail(form.getEmail());
        memberService.join(member);

        return "redirect:/members";
    }

    @GetMapping("/members")
    public String members(Model model) {
        model.addAttribute("members", memberService.findMembers());
        return "/members/memberList";
    }
}
