package com.lx.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IndexDao {

    /*
    查询访客量
     */
    @Select("SELECT wcounts FROM watched;")
    public int selectWatchedCount();

    /*
    增加访客量
     */
    @Update("UPDATE watched SET wcounts = (wcounts + 1);")
    public void addWatchedCount();
}
