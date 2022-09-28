package org.example;

import org.example.client.AuthorClient;
import org.example.entity.Author;
import org.example.entity.Book;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SteamDemo {

    public static void main(String[] args) {
//        streamArr();
//        streamMap2();
//        streamDistinct();
//        streamSorted();
//        streamLimit();
//        streamSkip();
//        streamFlatMap1();
//        streamFlatMap2();
//        streamCount();
//        streamMaxAndMin();
//        streamCollectToList();
//        streamCollectToSet();
//        streamCollectToMap();
//        streamAnyMach();
//        streamAllMach();
//        streamNoneMach();
//        streamFindAny();
//        streamFindFirst();
//        streamReduce01();
        streamReduce02();
    }
    //stream流过滤器
    private  static void streamArr(){
        int[] arr={1,1,2,3,4,4,5,6};
        Stream stream;
//        Arrays.stream(arr).distinct().forEach(System.out::println);
          IntStream.of(arr).distinct().filter(i->i>2).forEach(System.out::println);
    }

    //map处理流
    private  static void  streamMap(){
        Map<String,Integer> map=new HashMap<>();
        map.put("蜡笔小新",19);
        map.put("小黑子",17);
        map.put("竹内结子",16);

        Set<Map.Entry<String,Integer>> entries=map.entrySet();
        entries.stream().filter( entries1->entries1.getValue()>16).forEach(System.out::println);

    }

    //stream流map对象转换
    private  static void  streamMap2(){
        List<Author> authors=getAuthorArr();
        authors.stream().map(author -> AuthorClient.builder().name(author.getName()).build()).forEach(System.out::println);
    }
    //stream流去重复
    private  static void  steamDistinct(){
        List<Author> authors=getAuthorArr();
        authors.stream().distinct().forEach(System.out::println);
    }

    /**
     *  stream排序分两种
     *   <p>1.sorted()内写入方法会有两个对象a1-a2为升序 a2-a1为降序></p>
     *   <p>实例: sorted((a1,a2)->a1.getAage()-a2.getAge())</p>
     *   <p>2.在对象类里引入 Comparable 实现 compareTo方法 将当前的类的要排序的字段减去传入类的字段</p>
     *   <p>实例: （注：此处stream流在写入sorted无需在写入方法）</p>
     *   <p>this.age-o.age 升序</p>
     *   <p>o.age-this.age 降序</p>
     */
    //stream流排序
    private  static void  streamSorted(){
        List<Author> authors=getAuthorArr();
        authors.stream().distinct().sorted((a1,a2)->a2.getAge()-a1.getAge()).forEach(System.out::println);
    }

    //stream流最大长度
    private  static void  streamLimit(){
        List<Author> authors=getAuthorArr();
        authors.stream().distinct().sorted().limit(2).forEach(System.out::println);
    }

    //stream流跳过第n元素执行剩下的元素
    private  static void  streamSkip(){
        List<Author> authors=getAuthorArr();
        authors.stream().distinct().sorted().skip(1).forEach(System.out::println);
    }

    //stream流flatmap 将一个对象转换多个对象流
    //1
    private  static void  streamFlatMap1(){
        List<Author> authors=getAuthorArr();
        authors.stream()
                .distinct()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .forEach(book -> System.out.println(book.getName()));
    }
    //2
    private  static void  streamFlatMap2(){
        List<Author> authors=getAuthorArr();
        authors.stream()
                .distinct()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .flatMap(book->Arrays.stream(book.getCategory().split(",")))
                .distinct()
                .forEach(System.out::println);
    }

    //stream记录当前流中处理后的数据
    private  static void  streamCount(){
        List<Author> authors=getAuthorArr();
        Long  count= authors.stream()
                .distinct()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .count();
        System.out.println("当前流中每个作家一共有"+count+"个数据");
    }

    //stream流max和min
    private static  void streamMaxAndMin(){
        List<Author> authors=getAuthorArr();
        //取每个作家的书籍评分最大值
        Optional<Integer> max=authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book->book.getScore())
                .max((score1,score2)->score1-score2);

        System.out.println(max.get());

        //取每个作家的书籍评分最小值
        Optional<Integer> min=authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book->book.getScore())
                .min((score1,score2)->score1-score2);

        System.out.println(min.get());
    }

    /**
     * stream流获取流中的list set 和map 集合
     */
    //1.steam流list转换
    private  static void  streamCollectToList(){
        List<Author> authors=getAuthorArr();
        List<String> nameList=authors.stream().map(author -> author.getName()).collect(Collectors.toList());
        nameList.forEach(System.out::println);
    }

    //2.steam流Set转换
    private  static void  streamCollectToSet(){
        List<Author> authors=getAuthorArr();
        Set<Book> books=authors.stream().flatMap(author -> author.getBooks().stream()).collect(Collectors.toSet());
        books.forEach(System.out::println);
    }

    //3.steam流map转换
    private  static void  streamCollectToMap(){
        List<Author> authors=getAuthorArr();
        Map<String,List<Book>> map=authors.stream().distinct()
                .collect(Collectors.toMap(author -> author.getName(),author -> author.getBooks()));
        System.out.println(map);
    }

    //stream流判断是否有任意符合的条件
    private static void  streamAnyMach(){
        List<Author> authors=getAuthorArr();
        boolean flag= authors.stream().anyMatch(author -> author.getAge()>18);
        System.out.println("authors 是否有大于18岁的 flag="+flag);
    }

    //stream流判断是否所有的条件都符合
    private static void  streamAllMach(){
        List<Author> authors=getAuthorArr();
        boolean flag= authors.stream().allMatch(author -> author.getAge()>=18);
        System.out.println("authors 是否所有有大于15岁的 flag="+flag);
    }

    //stream流判断是否所有的条件都不符合
    private static void  streamNoneMach(){
        List<Author> authors=getAuthorArr();
        boolean flag= authors.stream().noneMatch(author -> author.getAge()>25);
        System.out.println("authors 是否没有都25岁的 flag="+flag);
    }

    //stream流获取流中任意一个元素切随机
    private static void  streamFindAny(){
        List<Author> authors=getAuthorArr();
        authors.stream().filter(author -> author.getAge()<17).findAny().ifPresent(author->{
            System.out.println(author.getName());
        });
    }

    //stream流获取中的第一个元素
    private static void  streamFindFirst(){
        List<Author> authors=getAuthorArr();
        authors.stream().sorted((a1,a2)->a1.getAge()-a2.getAge()).findFirst().ifPresent(author -> {System.out.println(author.getName());});
    }

    /**
     * <p>stream流reduce归并可以在流中制订计算方式</p>
     * 实例:1
     */
    private static  void  streamReduce01(){
        List<Author> authors=getAuthorArr();
        Integer integer=authors.stream().map(author -> author.getAge()).reduce(0,(result,element)->result+element);
        System.out.println("authors 中所有作家的年龄总和 ageCount="+integer);
        Integer max=authors.stream().map(author -> author.getAge()).reduce(Integer.MIN_VALUE,(i1,i2)->i1<i2?i2:i1);
        System.out.println("authors 中所有年龄最大值 max="+max);
        Integer min=authors.stream().map(author -> author.getAge()).reduce(Integer.MAX_VALUE,(i1,i2)->i1>i2?i2:i1);
        System.out.println("authors 中所有年龄最小值 min="+min);
    }

    /**
     * 实例：2
     */
    private static void streamReduce02(){
        List<Author> authors=getAuthorArr();
        Integer min=authors.stream().map(author -> author.getAge()).reduce((i,j)->i>j?j:i).get();
        System.out.println("authors 中所有年龄最小值 min="+min);
    }

    /**
     * 实例：3
     */
    private static void streamReduce03(){

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
                Book.builder().id(3l).name("阿Q正传").category("小说").score(97).intro("记述旧社会生活在底层的阿Q悲惨一生。最后不明不白的死去。").build(),
                Book.builder().id(4l).name("狂人日记").category("小说").score(67).intro("评判旧社会以人血馒头对人们思想上的封建迷信且荒谬。").build()
        ));
        List<Book> book3=new ArrayList<Book>(Arrays.asList(
                Book.builder().id(5l).name("钢铁是怎么炼成的").category("小说").score(97).intro("保尔柯察金的艰难一生。").build(),
                Book.builder().id(6l).name("战争与和平").category("小说").score(67).intro("主要叙述沙俄时期，贵族与拿破仑战争。").build(),
                Book.builder().id(6l).name("战争与和平").category("小说").score(67).intro("主要叙述沙俄时期，贵族与拿破仑战争。").build()
        ));

        author.setBooks(book);
        author1.setBooks(book2);
        author2.setBooks(book3);
        author3.setBooks(book3);
        return Arrays.asList(author,author1,author2,author3);
    }
}
