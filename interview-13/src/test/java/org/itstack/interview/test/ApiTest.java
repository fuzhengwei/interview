package org.itstack.interview.test;


import org.itstack.interview.IUserApi;
import org.itstack.interview.UserApi;
import org.itstack.interview.agent.asm.ASMProxy;
import org.itstack.interview.agent.bytebuddy.ByteBuddyProxy;
import org.itstack.interview.agent.cglib.CglibProxy;
import org.itstack.interview.agent.javasssist.JavassistProxy;
import org.itstack.interview.agent.jdk.JDKProxy;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_ASMProxy() throws Exception {
        IUserApi userApi = ASMProxy.getProxy(UserApi.class);
        String invoke = userApi.queryUserInfo();
        logger.info("测试结果：{}", invoke);
    }

    @Test
    public void test_ByteBuddyProxy() throws Exception {
        IUserApi userApi = ByteBuddyProxy.getProxy(UserApi.class);
        String invoke = userApi.queryUserInfo();
        logger.info("测试结果：{}", invoke);

    }

    @Test
    public void test_CglibProxy() throws Exception {
        CglibProxy cglibProxy = new CglibProxy();
        UserApi userApi = (UserApi) cglibProxy.newInstall(new UserApi());
        String invoke = userApi.queryUserInfo();
        logger.info("测试结果：{}", invoke);
    }

    @Test
    public void test_JavassistProxy() throws Exception {
        IUserApi userApi = JavassistProxy.getProxy(UserApi.class);
        String invoke = userApi.queryUserInfo();
        logger.info("测试结果：{}", invoke);
    }

    @Test
    public void test_JDKProxy() throws Exception {
        IUserApi userApi = JDKProxy.getProxy(IUserApi.class);
        String invoke = userApi.queryUserInfo();
        logger.info("测试结果：{}", invoke);
    }

    @Test
    public void test_reflect() throws Exception {
        Class<UserApi> clazz = UserApi.class;
        Method queryUserInfo = clazz.getMethod("queryUserInfo");
        Object invoke = queryUserInfo.invoke(clazz.newInstance());
        System.out.println(invoke);
    }

}
