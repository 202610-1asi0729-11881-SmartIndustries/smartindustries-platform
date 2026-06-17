package com.smartindustries.smartlock.platform.report.domain.repositories;

import com.smartindustries.smartlock.platform.report.domain.model.aggregates.ScheduleDay;

import java.util.Optional;

public interface ScheduleDayRepository {
    Optional<ScheduleDay> findById(Long id);
    ScheduleDay save(ScheduleDay scheduleDay);
}
