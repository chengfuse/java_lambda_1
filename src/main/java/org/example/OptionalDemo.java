package org.example;

import org.example.entity.Author;
import org.example.entity.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class OptionalDemo {

    private static List<Book> book=new ArrayList<Book>(Arrays.asList(
            Book.builder().id(1l).name("鸡你太美").category("乐曲").score(97).intro("啊！鸡你太美了，鸡你实在是太美了！").build(),
            Book.builder().id(2l).name("你干嘛").category("乐曲").score(67).intro("你干嘛！我画个心圈恶心你。哼！").build()
    ));

    private static  Author author= Author.builder().id(1l).name("蔡徐坤").age(23).intro("全民制作人到家好。我是练习时长两年半的偶像练习生").books(book).build();

    public static void main(String[] args) {
//        getAuthorOptional();
//        getOptionalIsEmpty();
//        getOptionalOrElseGet();
//        getOptionalOrElseThrow();
//        getOptionalFilter();
//        getOptionalIsPresent();
        getOptionalMap();
    }

    //optional类基本用法 of表示获取对象 ofNullable 获取对象并判断是否为空
    private  static void getAuthorOptional(){
        Optional<Author> authorOptional= Optional.ofNullable(author);
        authorOptional.ifPresent(System.out::println);
    }

    //optional调用empty为空方法
    private static void getOptionalIsEmpty(){
        Optional<Author> authorOptional= author==null?Optional.empty():Optional.of(author);
        authorOptional.ifPresent(System.out::println);
    }

    //optional orElseGet返回对象如果为空返回默认值
    private static  void getOptionalOrElseGet(){
        Optional<Author> authorOptional=Optional.ofNullable(author);
        Author author1=authorOptional.orElseGet(()->new Author());
        System.out.println(author1);
    }

    //optional orElseThrow返回对象如果为空根据你传入的参数来创建异常
    private static  void getOptionalOrElseThrow(){
        Optional<Author> authorOptional=Optional.ofNullable(null);
        try {
            Author author1=authorOptional.orElseThrow(()->new RuntimeException("author 为空"));
            System.out.println(author1);
        }catch (Exception e){
            System.out.println("author 为空");
        }
    }

    //optional filter过滤条件
    private static void getOptionalFilter(){
        Optional<Author> authorOptional=Optional.ofNullable(author);
        authorOptional.filter(author -> author.getAge()>30).ifPresent(System.out::println);
    }

    //optional isPresent判断是否存在数据
    private static  void  getOptionalIsPresent(){
        Optional<Author> authorOptional=Optional.ofNullable(author);
        if (authorOptional.isPresent()) {
            System.out.println("已存在作家 author="+authorOptional.get().getName());
        }
    }

    //optional map数据转换
    private static  void  getOptionalMap(){
        Function<String,Object> function;
        Optional<Author> authorOptional=Optional.ofNullable(author);
        authorOptional.map(author -> author.getBooks()).ifPresent(System.out::println);
    }
}
