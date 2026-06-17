package com.smartindustries.smartlock.platform.report.application.commandservices;

import com.smartindustries.smartlock.platform.report.domain.model.aggregates.ScheduleDay;
import com.smartindustries.smartlock.platform.report.domain.model.commands.CreateScheduleDayCommand;
import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;

public interface ScheduleDayCommandService {
    Result<ScheduleDay, ApplicationError> handle(CreateScheduleDayCommand command);
}
