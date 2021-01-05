package com.lx.service;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IndexService {
    /*
    查询访客量
     */
    public int selectWatchedCount();

    /*
    增加访客量
     */
    public void addWatchedCount();
}
