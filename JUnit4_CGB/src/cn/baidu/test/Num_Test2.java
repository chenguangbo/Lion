package cn.baidu.test;

import static org.junit.Assert.*; //静态引入    可以直接使用类里面的方法
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;

import cn.baidu.Num;
import org.junit.Test;

public class Num_Test2 {

	@Test
	public void testTotal() {
		int total = new Num().total(10, 20);
		// 参数1.出错提示信息
		// 参数2.真实的方法返回值
		// 参数3.匹配规则
		// assertThat("返回信息", total, Matchers.is(20));
		assertThat(total + "", Matchers.startsWith("3"));

	}

}
