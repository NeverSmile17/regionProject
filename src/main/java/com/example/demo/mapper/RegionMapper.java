package com.example.demo.mapper;

import com.example.demo.model.Region;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RegionMapper {

    @Results({
            @Result(property = "shortName", column = "short_name")
    })
    @Cacheable("REGION")
    @Select("SELECT * FROM REGION")
    List<Region> findAll();

    @Results({
            @Result(property = "shortName", column = "short_name")
    })
    @Cacheable("REGION")
    @Select("SELECT * FROM REGION WHERE id = #{id}")
    Region findById(Integer id);

    @CacheEvict(cacheNames = "REGION", allEntries = true)
    @Insert("INSERT INTO REGION(id, name, short_name) VALUES (#{id}, #{name}, #{shortName})")
    void insert(Region region);

    @CacheEvict(cacheNames = "REGION", allEntries = true)
    @Update("UPDATE REGION SET name = #{name}, short_name = #{shortName} WHERE id = #{id}")
    void update(Region region);

    @CacheEvict(cacheNames = "REGION", allEntries = true)
    @Delete("DELETE FROM REGION WHERE id = #{id}")
    void deleteById(Integer id);
}