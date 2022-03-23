package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// @Service    // spring이 memberService를 container에 등록할 수 있도록 존재를 알려줌
@Transactional
public class MemberService {

    private MemberRepository memberRepository;

    // @Autowired
    public MemberService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }
    /*
    회원 가입
     */

    public Long join(Member member) {
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });

        /*
        Optional<Member> result = memberRepository.findByName(member.getName());    // null 가능성 있으면 Optional로 감싸서
        result.ifPresent(m -> { // result에 값이 있다면
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
         */
    }

    /*
    전체 회원 조회
     */

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);

    }
}
