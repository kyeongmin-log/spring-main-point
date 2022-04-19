package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponetFilterAppConfigTest {
    /*
    * BeanB의 경우, 컴포넌트 스캔 시 excludeFilters 옵션으로 @MyExcludeComponet이 붙은
    * 클래스는 제외하였기 때문에 해당 클래스로 스프링 빈을 조회하면
    * 정의된 빈을 찾을 수 없다는 예외가 나오는 것을 확인할 수 있다.
    * */
    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponetFilterConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

        assertThrows(NoSuchBeanDefinitionException.class, ()->ac.getBean("beanB", BeanB.class));
    }

    /*
    * FilterType의 옵션(여러 개가 있지만 자주 쓰는 2가지를 작성)
    * 1. ANNOTATION : 애노테이션 이름으로 필터한다.(default)
    * 2. ASSIGNABLE_TYPE : 지정 타입으로 필터한다.(클래스명)
    * includeFilters은 거의 사용되지 않으며 excludeFilters는 간혹 사용된다.
    * */
    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponet.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponet.class)
    )
    static class ComponetFilterConfig{}
}
