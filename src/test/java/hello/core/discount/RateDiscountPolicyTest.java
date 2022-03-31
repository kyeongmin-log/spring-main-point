package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {
    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다")
    void vip_o(){
        // given
        Member memberVIP = new Member(1L, "memberVIP", Grade.VIP);
        // when
        int discountPrice = discountPolicy.discount(memberVIP, 20000);
        // then
        assertThat(discountPrice).isEqualTo(2000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되면 안된다")
    void vip_x(){
        // given
        Member memberBasic = new Member(2L, "memberBasic", Grade.BASIC);
        // when
        int discountPrice = discountPolicy.discount(memberBasic, 20000);
        // then
        assertThat(discountPrice).isEqualTo(0);
    }
}