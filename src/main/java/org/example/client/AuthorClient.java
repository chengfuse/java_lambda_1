package org.example.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Tolerate;
import org.example.entity.Book;

import java.util.List;

@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class AuthorClient {
    //编号
    private Long id;
    //姓名
    private  String name;
    //年龄
    private  Integer age;
    //简介
    private String  intro;


    @Tolerate
    public AuthorClient(){}
}
