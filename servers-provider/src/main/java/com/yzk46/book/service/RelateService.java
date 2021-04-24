package com.yzk46.book.service;

import com.yzk46.book.entities.Relate;

import java.util.List;

public interface RelateService {
    void updateRelate(Relate relate);
    int addRelate(Relate relate);
    Relate getRelate(Relate relate);
    List<Relate> getRelateByBook(Relate relate);
    List<Relate> getAllRelate();
}
