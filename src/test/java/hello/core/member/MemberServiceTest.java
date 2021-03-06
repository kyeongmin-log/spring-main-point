package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    // jnuit 을 이용한 테스트 코드
    // 테스트 성공을 한눈에 볼 수 있고 테스트 실패 시 콘솔 창에 왜 실패했는지 자세하게 알려준다.
    MemberService memberService;

    // 테스트가 실행되기 전에 실행되게 하는 annotation
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join(){
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
