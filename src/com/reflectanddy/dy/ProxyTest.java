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
										    target.getClass().getInterfaces(),//ָ���������ɵĴ���ʵ����Ŀ�����ʵ��ͬһ�ӿ�
										    new InvocationHandler(){
			public Object invoke(Object proxy,
					Method method, Object[] args)
					throws Throwable {
				System.out.println("��������");
				Object relValue=method.invoke(target, args);
				System.out.println("�ر�����");
				return relValue;
			}
		});
		return proxy;
	}
}
