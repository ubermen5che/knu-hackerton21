package KM.KNUguard.repository;

import KM.KNUguard.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    //JPA는 EntityManager을 이용하여 모든것을 처리한다.
    //Spring에 JPA 라이브러리를 추가해주면 자동으로 db와 연결되어 entitymanager만들어줌
    //우리는 그냥 아래와 같이 DI 받으면 됨.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    //내부적으로 insert문 돌아감.
    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    //마찬가지로 JPA가 자동으로 select문 돌려서 찾은 뒤 Member객체 반환.
    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }


    //PK이외에 컬럼들에 대해서는 jdql을 만들어 줘야함.
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}