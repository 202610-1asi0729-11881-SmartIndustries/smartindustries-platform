package com.smartindustries.smartlock.platform.report.application.internal.commandservices;

import com.smartindustries.smartlock.platform.report.application.commandservices.ScheduleDayCommandService;
import com.smartindustries.smartlock.platform.report.domain.model.aggregates.ScheduleDay;
import com.smartindustries.smartlock.platform.report.domain.model.commands.CreateScheduleDayCommand;
import com.smartindustries.smartlock.platform.report.domain.repositories.ScheduleDayRepository;
import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class ScheduleDayCommandServiceImpl implements ScheduleDayCommandService {

    private final ScheduleDayRepository scheduleDayRepository;

    public ScheduleDayCommandServiceImpl(ScheduleDayRepository scheduleDayRepository) {
        this.scheduleDayRepository = scheduleDayRepository;
    }

    @Override
    public Result<ScheduleDay, ApplicationError> handle(CreateScheduleDayCommand command) {
        try {
            var scheduleDay = new ScheduleDay(command);
            var saved = scheduleDayRepository.save(scheduleDay);
            return Result.success(saved);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("ScheduleDay", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("schedule-day-creation", e.getMessage()));
        }
    }
}
