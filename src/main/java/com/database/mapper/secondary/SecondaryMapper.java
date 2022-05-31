package com.database.mapper.secondary;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author sunlongfei
 */
@Mapper
public interface SecondaryMapper {

    @Select("SELECT str FROM tab WHERE id = #{id}")
    String query(Integer id);

    @Insert("INSERT INTO tab (str) VALUES (#{str})")
    void insertItem(String str);
}
