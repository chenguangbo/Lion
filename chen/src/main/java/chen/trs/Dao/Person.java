package chen.trs.Dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


//                                        泛型<pojo实体bean，主键ID的类型>
public interface Person extends JpaRepository<Person, Serializable> {
	//根据人的名字查找
	public List<Person> findByName(String name);
	
	//根据爱好查找
	public List<Person> findByHobby(String hobby);
	
	//
	
	
}
