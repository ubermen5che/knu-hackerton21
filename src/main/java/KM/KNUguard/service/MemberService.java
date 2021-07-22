package KM.KNUguard.service;

import KM.KNUguard.domain.Login;
import KM.KNUguard.domain.Member;
import KM.KNUguard.repository.LoginMemberRepository;
import KM.KNUguard.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    //Dependency Ejection (외부에서 레포지토리 초기화가능하게함)
    //Test시에 사용되는 memberRepository와 MemberService에서의 memberRepository가 서로 다른 객체를 가지고 있었으나
    //Dependency Ejection 함으로써 동일한 MemberRepository를 갖게끔 수정함.

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     *
     * 회원가입
     */
    public String join(Member member){
        //같은 이름 중복 회원 x
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public void validateDuplicateMember(Member member) {
        memberRepository.findById(member.getId())   //Return optional
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(String memberId) {
        return memberRepository.findById(memberId);
    }
}
