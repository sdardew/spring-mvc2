package hello.login.domain.login;

import hello.login.web.member.Member;
import hello.login.web.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * @return null 로그인 실패
     */
    public Member login(String loginId, String password) {
//        Optional<Member> findMemberOptionnal = memberRepository.findByLoginId(loginId);
//        Member member = findMemberOptionnal.get();
//
//        if(member.getPassword().equals(password)) {
//            return member;
//        } else {
//            return null;
//        }
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
