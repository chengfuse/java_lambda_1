package org.example.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Tolerate;
@Builder
@Data
@EqualsAndHashCode
public class Book {
    //编号
    private  Long  id;
    //书名
    private  String name;
    //分类
    private  String  category;
    //评分
    private  Integer score;
    //简介
    private String intro;

    @Tolerate
    public Book(){}
}
