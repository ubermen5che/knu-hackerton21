package KM.KNUguard.controller;

import KM.KNUguard.domain.Login;
import KM.KNUguard.domain.Member;
import KM.KNUguard.service.LoginService;
import KM.KNUguard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;
    private final LoginService loginService;

    @Autowired
    public MemberController(MemberService memberService, LoginService loginService){
        this.memberService = memberService;
        this.loginService = loginService;
    }

    @GetMapping("/members")
    public List<Member> members(){
        Member member = new Member();
        return memberService.findMembers();
    }

    @PostMapping("/login")
    public HashMap<String, String> login(@RequestBody HashMap<String, String> loginJson){
        Optional<Login> result = loginService.login((loginJson.get("id")), loginJson.get("pw"));

        System.out.println("loginJson.get(\"id\") = " + loginJson.get("id"));
        System.out.println("loginJson.get(\"pw\") = " + loginJson.get("pw"));

        /**
        Member member = new Member();
        member.setId(loginJson.get("id"));
        member.setPw(loginJson.get("pw"));
        memberService.join(member);
        **/

        HashMap<String, String> body = new HashMap<>();

        if(result.isPresent()){
            System.out.println("result.get() = " + result.get().getId());
            body.put("result", "success");
        }else{
            body.put("result", "fail");
        }

        return body;

    }
}
