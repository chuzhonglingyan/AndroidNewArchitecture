package com.yuntian.androidnewarchitecture.db.dao;

import java.util.List;

import io.reactivex.Flowable;

public interface IBaseDao<T> {

    long insert(T t);

    List<Long> inserts(List<T> list);

    void delete(int id);

    void delete(T t);

    void delete(List<Integer> userIds);


    void update(T t);

    void update(List<T> list);

    Flowable<List<T>> getByIds(List<Integer> userIds);

    Flowable<T> getById(int id);

    Flowable<List<T>> getAll();

}

