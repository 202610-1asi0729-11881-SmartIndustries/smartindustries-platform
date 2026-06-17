package com.smartindustries.smartlock.platform.report.domain.model.commands;

import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.Day;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.TimeBlock;

public record CreateScheduleDayCommand(Long personId, Day day, TimeBlock timeBlock) {
    public CreateScheduleDayCommand {
        if (personId == null || personId < 1)
            throw new IllegalArgumentException("personId cannot be null or less than 1");
        if (day == null)
            throw new IllegalArgumentException("day cannot be null");
        if (timeBlock == null)
            throw new IllegalArgumentException("timeBlock cannot be null");
    }
}
