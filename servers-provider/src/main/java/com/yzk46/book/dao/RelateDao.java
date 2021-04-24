package com.yzk46.book.dao;

import com.yzk46.book.entities.Relate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RelateDao {
    void updateRelate(Relate relate);
    int addRelate(Relate relate);
    Relate getRelate(Relate relate);
    List<Relate> getRelateByBook(Relate relate);
    List<Relate> getAllRelate();
}
