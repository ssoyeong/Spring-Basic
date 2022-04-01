package hello.core.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    MemberServiceImpl은 MemberRepository, MemoryMemberRepository (추상화, 구체화) 모두 의존하고 있음
//    DIP 위반

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
