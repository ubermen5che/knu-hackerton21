package KM.KNUguard.service;

import KM.KNUguard.domain.Login;
import KM.KNUguard.repository.LoginMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class LoginService {

    @Autowired
    LoginMemberRepository loginMemberRepository;

    public Optional<Login> login(String id, String pw){
        return loginMemberRepository.findByIdAndPw(id, pw);
    }
}
