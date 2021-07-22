package KM.KNUguard.service;

import KM.KNUguard.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    private final CrawlDataRepository crawlDataRepository;
    private final EntityManager em;
    private final UnivDataRepository univDataRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository, EntityManager em, CrawlDataRepository
                        crawlDataRepository, UnivDataRepository univDataRepository){
        this.em = em;
        this.crawlDataRepository = crawlDataRepository;
        this.memberRepository = memberRepository;
        this.univDataRepository = univDataRepository;
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
    public LoginService loginService(){
        return new LoginService();
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }

    @Bean
    public CheckingService checkingService() {
        return new CheckingService(crawlDataRepository);
    }

    @Bean
    public CrawlDataRepository crawlDataRepository(){
        return new CrawlDataRepositoryImpl(em);
    }

    @Bean
    public GeocodingService geocodingService(){
        return new GeocodingService(crawlDataRepository);
    }
    @Bean
    public UnivDataRepository univDataRepository(){
        return new UnivDataRepositoryImpl(em);
    }
}
