package com.smartindustries.smartlock.platform.report.application.internal.eventhandlers;

import com.smartindustries.smartlock.platform.report.application.commandservices.ScheduleDayCommandService;
import com.smartindustries.smartlock.platform.report.domain.model.aggregates.ScheduleDay;
import com.smartindustries.smartlock.platform.report.domain.model.commands.CreateScheduleDayCommand;
import com.smartindustries.smartlock.platform.shared.application.result.ApplicationError;
import com.smartindustries.smartlock.platform.shared.application.result.Result;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.Day;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.TimeBlock;
import com.smartindustries.smartlock.platform.spacemanagement.interfaces.events.PersonAddedToOrganizationIntegrationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonAddedToOrganizationEventHandler {

    private final ScheduleDayCommandService scheduleDayCommandService;

    public PersonAddedToOrganizationEventHandler(ScheduleDayCommandService scheduleDayCommandService) {
        this.scheduleDayCommandService = scheduleDayCommandService;
    }

    @EventListener
    public void on(PersonAddedToOrganizationIntegrationEvent event) {
        for (Day day : Day.values()) {
            var command = new CreateScheduleDayCommand(event.personId(), day, new TimeBlock(null, null));
            var result = scheduleDayCommandService.handle(command);

            if (result instanceof Result.Failure<ScheduleDay, ApplicationError> f) {
                log.warn("Failed to create schedule day {} for person {}: {}",
                        day, event.personId(), f.error().message());
            }
        }
    }
}
