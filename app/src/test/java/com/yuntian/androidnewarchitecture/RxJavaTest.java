package com.yuntian.androidnewarchitecture;

import com.yuntian.androidnewarchitecture.repository.DbUserRepository;

import org.junit.Test;

import io.reactivex.Flowable;

/**
 * @link {https://github.com/ReactiveX/RxJava}
 */
public class RxJavaTest {

    @Test
    public void  test1(){
        Flowable.just("Hello world").subscribe(System.out::println);
    }


}
