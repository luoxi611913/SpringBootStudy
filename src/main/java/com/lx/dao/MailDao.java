package com.lx.dao;

import com.lx.model.MailModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MailDao {

    /*
    查询所有没被加入任务队列的任务
     */
    @Select("SELECT * FROM mailtasks WHERE flg = 0;")
    public List<MailModel> selectMails();

    /*
    把加入队列的任务设为1
     */
    @Update("UPDATE mailtasks SET flg = 1 WHERE tid = #{tid}")
    public void setFlgOne(Integer tid);

    /*
    新加一条计划
     */
    @Insert("INSERT INTO mailtasks (tchron,toUser,fromUser,mailTitle,mailContents,doDate,flg) " +
            "values (#{tchron},#{toUser},#{fromUser},#{mailTitle},#{mailContents},#{doDate},0)")
    public void insertNewSchedu(MailModel mailModel);
}
