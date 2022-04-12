package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
         ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자가 10000원 주문
        statefulService1.order("userA", 10000);
        // ThreadB : B사용자가 20000원 주문
        statefulService2.order("userB", 20000);

        // A 사용자의 주문 금액을 조회
        int price = statefulService1.getPrice();
        System.out.println("userA price = " + price);

        // 원하는 값을 10000원이지만 20000원이 나온다.
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    @Configuration
    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}