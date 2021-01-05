package com.lx.service.impl;

import com.lx.dao.MailDao;
import com.lx.model.MailModel;
import com.lx.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    MailDao mailDao;

    @Override
    public List<MailModel> getMailTasks() {

        List<MailModel> tasks = mailDao.selectMails();

        if(tasks.size() > 0) {
            for (MailModel task : tasks) {
                mailDao.setFlgOne(task.getTid());
            }
        }

        return tasks;
    }
}
