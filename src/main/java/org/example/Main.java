package org.example;

import org.example.entity.Author;
import org.example.entity.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Author> authors=getAuthorArr();
        authors.stream().distinct()
                .filter(author->author.getAge()<18)
                .forEach(data-> System.out.println(data.getName()));
    }


    private  static List<Author> getAuthorArr(){
        //作家添加
        Author author= Author.builder().id(1l).name("蔡徐坤").age(23).intro("全民制作人到家好。我是练习时长两年半的偶像练习生").build();
        Author author1= Author.builder().id(2l).name("鲁迅").age(17).intro("我说过很多话！还有不少重复的。").build();
        Author author2= Author.builder().id(3l).name("列夫托尔斯泰").age(15).intro("俄国著名作家。").build();
        Author author3= Author.builder().id(3l).name("列夫托尔斯泰").age(15).intro("俄国著名作家。").build();

        List<Book> book=new ArrayList<Book>(Arrays.asList(
                Book.builder().id(1l).name("鸡你太美").category("乐曲").score(97).intro("啊！鸡你太美了，鸡你实在是太美了！").build(),
                Book.builder().id(2l).name("你干嘛").category("乐曲").score(67).intro("你干嘛！我画个心圈恶心你。哼！").build()
        ));
        List<Book> book2=new ArrayList<Book>(Arrays.asList(
                Book.builder().id(3l).name("阿Q正传").category("小说").score(97).intro("记述旧社会生活在底层的阿Q悲惨一生。最后不明不白的死去。").build(),
                Book.builder().id(4l).name("阿Q正传").category("小说").score(97).intro("记述旧社会生活在底层的阿Q悲惨一生。最后不明不白的死去。").build(),
                Book.builder().id(5l).name("狂人日记").category("小说").score(67).intro("评判旧社会以人血馒头对人们思想上的封建迷信且荒谬。").build()
        ));
        List<Book> book3=new ArrayList<Book>(Arrays.asList(
                Book.builder().id(6l).name("钢铁是怎么炼成的").category("小说").score(97).intro("保尔柯察金的艰难一生。").build(),
                Book.builder().id(7l).name("战争与和平").category("小说").score(67).intro("主要叙述沙俄时期，贵族与拿破仑战争。").build(),
                Book.builder().id(8l).name("战争与和平").category("小说").score(67).intro("主要叙述沙俄时期，贵族与拿破仑战争。").build()
        ));

        author.setBooks(book);
        author1.setBooks(book2);
        author2.setBooks(book3);
        author3.setBooks(book3);
        return Arrays.asList(author,author1,author2,author3);
    }
}
