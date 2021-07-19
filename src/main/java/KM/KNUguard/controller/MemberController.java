package KM.KNUguard.controller;

import KM.KNUguard.domain.Member;
import KM.KNUguard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public List<Member> members(){
        Member member = new Member();
        return memberService.findMembers();
    }
}
