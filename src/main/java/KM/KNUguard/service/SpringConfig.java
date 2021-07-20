package KM.KNUguard.service;

import KM.KNUguard.repository.CrawlDataRepository;
import KM.KNUguard.repository.CrawlDataRepositoryImpl;
import KM.KNUguard.repository.JpaMemberRepository;
import KM.KNUguard.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    private final CrawlDataRepository crawlDataRepository;
    private final EntityManager em;

    @Autowired
    public SpringConfig(MemberRepository memberRepository, EntityManager em, CrawlDataRepository
                        crawlDataRepository){
        this.em = em;
        this.crawlDataRepository = crawlDataRepository;
        this.memberRepository = memberRepository;
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

    @Bean
    public CheckingService checkingService() {
        return new CheckingService(crawlDataRepository);
    }

    @Bean
    public CrawlDataRepository crawlDataRepository(){
        return new CrawlDataRepositoryImpl(em);
    }

}
