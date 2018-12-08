package com.yuntian.androidnewarchitecture.repository;

import com.yuntian.androidnewarchitecture.net.service.GoodService;
import com.yuntian.androidnewarchitecture.contract.GoodContract;
import com.yuntian.baselibs.lifecycle.BaseRepository;

/**
 * @author   chulingyan
 * @time     2018/12/07 20:38
 * @describe 接口约束
 */
public class GoodRepository extends BaseRepository<GoodService> implements GoodContract {



    public GoodRepository(GoodService service) {
        super(service);
    }









}


