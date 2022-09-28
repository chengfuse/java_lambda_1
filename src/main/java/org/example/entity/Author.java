package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Tolerate;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class Author implements Comparable<Author>{
    //编号
    private Long id;
    //姓名
    private  String name;
    //年龄
    private  Integer age;
    //简介
    private String  intro;
    //作品
    private List<Book> books;


    @Tolerate
    public Author(){}

    @Override
    public int compareTo(Author o) {
        return this.age-o.age;
    }
}
