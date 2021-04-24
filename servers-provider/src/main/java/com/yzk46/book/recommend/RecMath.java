package com.yzk46.book.recommend;

import com.google.common.collect.Lists;
import com.yzk46.book.entities.Relate;
import com.yzk46.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @program: book
 * @description: 算法计算类
 * @author: yzk46
 * @create: 2021-04-10 22:59
 **/
public class RecMath {

    private List<Integer> tagList = new ArrayList<>();

    @Autowired
    BookService bookService;

    public List<Integer> recommend(Integer uid, List<Relate> data){
        //找到最近邻用户id
        Map<Double, Integer> distances = computeNearestNeighbor(uid, data);
        Integer nearest = distances.values().iterator().next();
        Map<Integer, List<Relate>>  userMap=data.stream().collect(Collectors.groupingBy(Relate::getUserId));
        //最近邻用户看过书籍列表
        List<Integer>  neighborItemList = userMap.get(nearest).stream().map(e->e.getBookId()).collect(Collectors.toList());
        //指定用户看过书籍列表
        List<Integer>  userItemList  = userMap.get(uid).stream().map(e->e.getBookId()).collect(Collectors.toList());
        //找到最近邻看过，但是该用户没看过的书籍，计算推荐，放入推荐列表
        List<Integer> recommendList = new ArrayList<>();
        for (Integer item : neighborItemList) {
            if (!userItemList.contains(item)) {
                recommendList.add(item);
            }
        }
        Collections.sort(recommendList);
        return recommendList;
    }

    private Map<Double, Integer> computeNearestNeighbor(Integer userId, List<Relate> list) {
        //根据用户id分组
        //Relate包含用户id，书籍id，和用户对该书籍的喜爱程度
        Map<Integer, List<Relate>>  userMap=list.stream().collect(Collectors.groupingBy(Relate::getUserId));
        //距离，用户id
        Map<Double, Integer> distances = new TreeMap<>();
        //k为用户id，v为关联列表
        userMap.forEach((k,v)->{
            if(k!=userId){
                double distance = dis(v,userMap.get(userId));
                distances.put(distance, k);
            }
        });
        return distances;
    }

    private double dis(List<Relate> xList, List<Relate> yList) {
        List<Integer> xs= Lists.newArrayList();
        List<Integer> ys= Lists.newArrayList();
        xList.forEach(x->{
            yList.forEach(y->{
                //找到与目标用户有相同爱好书籍，存入矩阵
                if(x.getBookId().equals(y.getBookId())){
                    xs.add(x.getRelating());
                    ys.add(y.getRelating());
                }
            });
        });
        return getRelate(xs,ys);
    }

    public static Double getRelate(List<Integer> xs, List<Integer>  ys){
        int n=xs.size();
        double Ex= xs.stream().mapToDouble(x->x).sum();
        double Ey=ys.stream().mapToDouble(y->y).sum();
        double Ex2=xs.stream().mapToDouble(x->Math.pow(x,2)).sum();
        double Ey2=ys.stream().mapToDouble(y->Math.pow(y,2)).sum();
        double Exy= IntStream.range(0,n).mapToDouble(i->xs.get(i)*ys.get(i)).sum();
        double numerator=Exy-Ex*Ey/n;
        double denominator=Math.sqrt((Ex2-Math.pow(Ex,2)/n)*(Ey2-Math.pow(Ey,2)/n));
        if (denominator==0) return 0.0;
        return numerator/denominator;
    }
}
