package com.smartindustries.smartlock.platform.report.domain.model.aggregates;

import com.smartindustries.smartlock.platform.report.domain.model.commands.CreateScheduleDayCommand;
import com.smartindustries.smartlock.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.Day;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.TimeBlock;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ScheduleDay extends AbstractDomainAggregateRoot<ScheduleDay> {

    @Setter
    private Long id;

    @Setter
    private Long personId;

    @Setter
    private Day day;

    @Setter
    private TimeBlock timeBlock;

    public ScheduleDay() {
    }

    public ScheduleDay(CreateScheduleDayCommand command) {
        this.personId = command.personId();
        this.day = command.day();
        this.timeBlock = command.timeBlock();
    }
}
