package com.example.todoapi.repository.task;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.*;

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


    @Update("UPDATE tasks SET title = #{title} WHERE id = #{id}" )
    void update(TaskRecord taskRecord);

    @Delete("DELETE FROM tasks WHERE id = ${taskId}")
    void delete(Long taskId);
}
