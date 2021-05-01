package org.itstack.interview.test;

import org.itstack.interview.Bean_A;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_alias() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring-config.xml");
        Bean_A bean_a = beanFactory.getBean("bean_a", Bean_A.class);
        logger.info("获取 Bean 通过别名：{}", bean_a.getBean_b());
    }

    @Test
    public void test_aop(){
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring-config-aop.xml");
        Bean_A bean_a = beanFactory.getBean("bean_a", Bean_A.class);
        logger.info("获取 Bean 通过别名：{}", bean_a.getBean_b());
    }

}
