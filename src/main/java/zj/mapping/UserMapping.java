package zj.mapping;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import zj.entity.user;

import java.util.ArrayList;

/**
 * Created by zj on 2017-2-23.
 */
@Mapper
public interface UserMapping {

	@Select("select * from person where id = #{id}")
	user findById(@Param("id") int id);

	@Insert("insert into person (name , password) values (#{name} , #{password})")
	void insert(@Param("name") String name, @Param("password") String password);

	@Select({
			"select count(*) ",
			"from person ",
			"where name = #{name} "
	})
	int findbyUser(@Param("name") String username, @Param("password") String password);

	@Select("select * from person")
    ArrayList<user> findAll();
}
