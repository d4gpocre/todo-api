package com.example.todoapi.repository.task;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TaskRepository {

    @Select("select id, title From tasks WHERE id = #{taskId}" )
    Optional<TaskRecord> select(long taskId);

    @Select("SELECT id, title FROM tasks LIMIT #{limit} OFFSET #{offset}" )
    List<TaskRecord> selectList(int limit , long offset);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO tasks (title) VALUES (#{title})" )
    // myBatisではinsertのアノテーションがついたやつはvoidでないと定義できないらしい
    void insert(TaskRecord taskId);



}
