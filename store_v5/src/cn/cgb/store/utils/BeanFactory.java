package cn.cgb.store.utils;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.cgb.store.dao.CategoryDao;
import cn.cgb.store.domain.Category;

public class BeanFactory {

	public static Object createBean(String beanName){
		
		try {
			//利用DOM4J读取application.xml文件. 通过beanName寻找对应实现字符串
			SAXReader read=new SAXReader();
			//1_获取document节点
			
			//Document document = read.read(new File("src/application.xml"));
			//通过应用类加载器获取src下指定名称文件的输入流
			Document document = read.read(BeanFactory.class.getClassLoader().getResourceAsStream("application.xml"));
			//2_通过document节点获取到根节点
			Element root=document.getRootElement();
			//3_遍历根节点下的子节点
			List<Element> list = root.elements();
			for(Element element:list){
				//4_获取子节点的各个属性对应的值
				String id=element.attributeValue("id");
				String str=element.attributeValue("class");
				//System.out.println(id+"<===>"+str);
				if(id.equals(beanName)){
					//str就是当前我们要找的**Dao对应的字符串
					Class clazz=Class.forName(str);
					//通过反射创建对象
					return clazz.newInstance();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//通过反射创建对象返回
		return null;
	}
	
	
	public static void main(String[] args) {
		try {
			CategoryDao cd = (CategoryDao) createBean("CategoryDao");
			List<Category> allCats = cd.findAllCats();
			for(Category c:allCats){
				System.out.println(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
