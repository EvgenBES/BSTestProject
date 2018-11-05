package com.test.domain.executors;

import io.reactivex.Scheduler;

public interface PostExecutionThread  {
    Scheduler getScheduler();
}
