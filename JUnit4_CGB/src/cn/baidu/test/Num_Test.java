package cn.baidu.test;

import static org.hamcrest.Matcher.*;

import static org.junit.Assert.*;
import cn.baidu.Num;
import org.junit.Test;
/**
 * 
 * @author CGB
 *
 */
public class Num_Test {

	@Test
	public void testTotal() {

		int total = new Num().total(1, 2);
		assertTrue("结果不正确", 3 == total);//assertTrue()  如果结果正确就是green 
		int total2 = new Num().total(1, 2);//多一个字符串参数    如果方法出错的提示信息
		assertTrue(total2 == 3);
	
	}

}
