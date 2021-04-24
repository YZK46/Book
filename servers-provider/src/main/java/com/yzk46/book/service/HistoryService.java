package com.yzk46.book.service;

import com.yzk46.book.entities.History;

import java.util.List;

public interface HistoryService {

    void addHis(History history);

    List<History> getHis(Integer uId);
}
