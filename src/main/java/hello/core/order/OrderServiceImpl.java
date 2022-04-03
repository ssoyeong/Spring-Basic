package hello.core.order;

import hello.core.discount.DiscountPolicy;
//import hello.core.discount.FixDiscountPolicy;
//import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {


//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    /*
    * 위와 같이 변경하는 것은, client인 OverServiceImpl를 고쳐야 한다.
    * 인터페이스 DiscountPolicy뿐만 아니라 구체적인 클래스도 의존하고 있음
    * DIP 위반. OCP 위반.
     */

    private final MemberRepository memberRepository ;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /* 인터페이스에만 의존하도록 생성자를 통해 구체화한다. */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        /*
         * 할인에 관한 것은 createOrder가 관여하지 않고
         * discountPolicy에게 무조건 넘겨버림
         * SRP를 잘 지킴
         */

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
