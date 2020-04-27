package J1.Model.Mapper;

import J1.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    User findUserById(@Param("id") Integer id);

    @Select("select * from user where username = #{username}")
    User findUserByUsername(@Param("username") String username);

    @Insert("insert into user(username,encrypted_password,created_at,updated_at) values (#{username},#{encrypted_password},  DATETIME('now'),  DATETIME('now'))")
    void saveUserByUsername(@Param("username") String username, @Param("encrypted_password") String encrypted_password);
}
