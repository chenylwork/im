package com.yanshang.car.im;

import com.yanshang.car.im.bean.User;
import com.yanshang.car.im.services.UserServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImApplicationTests {
	@Autowired
	private UserServices userServices;

	@Test
	public void contextLoads() {
		userServices.checkUserRegister("zhangsan");
	}
	@Test
	public void register() {
		User user = new User("zhangsan","123456","张三");
		userServices.register(user);
	}

}

