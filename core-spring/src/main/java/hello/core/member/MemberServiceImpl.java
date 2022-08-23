package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  // 자동 스프링 빈으로 등록
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired  // 자동 의존관계 주입. // ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // 생성자를 통해서 MemberRepository의 구현체를 설정한다. AppConfig에서 설정됨.

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 싱글톤인지 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
