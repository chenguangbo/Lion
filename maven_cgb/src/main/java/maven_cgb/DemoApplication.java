package maven_cgb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		//String string = list.get(0);
		int size = list.size();
		boolean add = list.add("123");
		boolean contains = list.contains(123);
		System.out.println(contains);
		String remove = list.remove(0);
		boolean equals = list.equals(list);
		list.clear();
		boolean empty = list.isEmpty();
		System.out.println("是否为空： "+empty);
		
		int lastIndexOf = list.lastIndexOf(null);
		System.out.println(lastIndexOf);
		
		String[] str = {"123","899","123","我打阿达","321","456"};
		int binarySearch = Arrays.binarySearch(str, "456");
		System.out.println("是否含有456：  "+binarySearch);
		Arrays.sort(str);
		
		System.out.println("数组："+Arrays.toString(str));
		
		
		System.out.println("查询位置"+ binarySearch);
		List<String> list2 = Arrays.asList(str);
		//添加前
//		list2.add("能添加");
//		System.out.println("添加后");
		list.addAll(list2);
		
//		Iterator<String> iterator = list.iterator();
//		while (iterator.hasNext()) {
//			String next = iterator.next();
//			System.out.println(next);
//		}
		
		
		
		
		
		
		
		
		//SpringApplication.run(DemoApplication.class, args);
		
	}

}
