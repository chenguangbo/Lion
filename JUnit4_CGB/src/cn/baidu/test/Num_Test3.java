package cn.baidu.test;

import static org.hamcrest.Matchers.*;
//静态引入    可以直接使用类里面的方法
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import cn.baidu.Num;

public class Num_Test3 {
	
	@BeforeClass
	public static void beforeClass(){
		System.out.println("BeforeClass");
	}
	
	@AfterClass
	public static void afterClass(){
		System.out.println("AfterClass");
	}
	
	
	@Before
	public void before(){
		System.out.println("before");
	}
	
	@After
	public void after(){
		System.out.println("after");
	}
	
	@Ignore
	@Test
	public void testDivide(){
		int divide = new Num().divide(10, 5);
		assertThat(divide, is(2));
	}
	//expected   排除这个异常
	@Test(expected=java.lang.ArithmeticException.class)
	public void testDivide2(){
		int divide = new Num().divide(10, 0);
		assertThat(divide, is(2));
	}
	//timeout 设置方法执行时间     100毫秒      超过后方法失败
	@Test(expected=java.lang.ArithmeticException.class,timeout=100)
	public void testDivide3(){
		int divide = new Num().divide(10, 0);
		assertThat(divide, is(2));
	}
	
	
}
