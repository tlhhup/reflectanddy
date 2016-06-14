package com.reflectanddy.dy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.reflectanddy.service.UserService;
import com.reflectanddy.service.UserServiceImpl;

public class ProxyTest {

	public static void main(String[] args) {
		UserServiceImpl target=new UserServiceImpl();
		UserService userService = (UserService) getProxy(target);
		userService.update();
	}

	public static Object getProxy(final Object target){
		Object proxy=Proxy.newProxyInstance(target.getClass().getClassLoader(), 
										    target.getClass().getInterfaces(),//指定代码生成的代理实例与目标对象实现同一接口
										    new InvocationHandler(){
			public Object invoke(Object proxy,
					Method method, Object[] args)
					throws Throwable {
				System.out.println("开启事务");
				Object relValue=method.invoke(target, args);
				System.out.println("关闭事务");
				return relValue;
			}
		});
		return proxy;
	}
}
