package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        // 콘솔 창에 뜨는 메세지를 통해 확인하는 테스트 코드는 하나하나 확인해야하기에 좋은 테스트 방법이 아니다.
        /** 기존 방식
         * AppConfig appConfig = new AppConfig();
         * MemberService memberService = appConfig.memberService();
         * */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMemver = " + findMember.getName());
    }
}
