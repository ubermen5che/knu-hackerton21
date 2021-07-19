package KM.KNUguard.service;

import KM.KNUguard.repository.JpaMemberRepository;
import KM.KNUguard.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    private EntityManager em;

    @Autowired
    public SpringConfig(MemberRepository memberRepository, EntityManager em){
        this.memberRepository = memberRepository;
        this.em = em;
    }

    /**
     @Bean
     public MemberService memberService() {
     return new MemberService(memberRepository);
     }
     **/


    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }

}
