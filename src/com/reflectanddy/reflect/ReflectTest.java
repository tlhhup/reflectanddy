package com.reflectanddy.reflect;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import com.reflectanddy.entity.User;
import com.reflectanddy.service.UserServiceImpl;

public class ReflectTest {

	public static void main(String[] args) {
		try {
			Class<?> clazz = Class.forName("com.reflectanddy.service.UserServiceImpl");
			UserServiceImpl userService = (UserServiceImpl) clazz.newInstance();
			//��ȡ�����ķ���
			Method[] methods = clazz.getDeclaredMethods();
			for(int i=0;i<methods.length;i++){
				Method method=methods[i];
				Resource resource = method.getAnnotation(Resource.class);
				if(resource!=null){//��ȡ����resource��ע��ķ���
					Class<?>[] parameterTypes = method.getParameterTypes();
					//���õĲ�������
					Object[] params=new Object[parameterTypes.length];
					for(int j=0;j<parameterTypes.length;j++){
						Class<?> parameter=parameterTypes[j];
						params[j]=parameter.newInstance();
					}
					method.invoke(userService, params);
				}
			}
			User user=new User();
			user.setName("����");
			userService.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
