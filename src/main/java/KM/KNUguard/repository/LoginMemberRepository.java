package KM.KNUguard.repository;

import KM.KNUguard.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginMemberRepository extends JpaRepository<Login, String> {
    Optional<Login> findByIdAndPw(String id, String pw);
}
