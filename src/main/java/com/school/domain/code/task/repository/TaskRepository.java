package com.school.domain.code.task.repository;

import com.school.domain.code.task.Task;
import com.school.domain.utils.HibernateUtils;
import org.hibernate.Session;

public class TaskRepository {

    public Task get(String id) {
        try(Session session = HibernateUtils.openSession()) {
            return session.load(Task.class, id);
        }
    }
}
