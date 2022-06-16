package com.qa.lessons.common;

import com.qa.lessons.database.impl.DataBaseImpl;
import com.qa.lessons.enums.Executors;
import com.qa.lessons.managers.impl.GroupManagerImpl;
import com.qa.lessons.managers.impl.StudentManagerImpl;
import com.qa.lessons.services.impl.GroupServiceImpl;
import com.qa.lessons.services.impl.StudentServiceImpl;
import com.qa.lessons.services.impl.TaskServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static com.qa.lessons.enums.Executors.*;

public class Context {

    private static final Map<Executors, Executor> context = new HashMap<>();

    public static Executor getInstanceFromContext(Executors objectName) {
        return context.get(objectName);
    }

    public static void provideExecutors() {
        context.put(DATA_BASE, DataBaseImpl.getInstance());
        context.put(STUDENT_SERVICE, StudentServiceImpl.getInstance());
        context.put(GROUP_SERVICE, GroupServiceImpl.getInstance());
        context.put(TASK_SERVICE, TaskServiceImpl.getInstance());
        context.put(STUDENT_MANAGER, StudentManagerImpl.getInstance());
        context.put(GROUP_MANAGER, GroupManagerImpl.getInstance());
    }

}
