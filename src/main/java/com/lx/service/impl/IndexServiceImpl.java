package com.lx.service.impl;

import com.lx.dao.IndexDao;
import com.lx.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("indexService")
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexDao indexDao;

    @Override
    public int selectWatchedCount() {
        return indexDao.selectWatchedCount();
    }

    @Override
    public void addWatchedCount() {
        indexDao.addWatchedCount();
    }
}
