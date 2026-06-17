package com.smartindustries.smartlock.platform.shared.domain.model.valueobjects;

import java.time.LocalTime;

public record TimeBlock(LocalTime start, LocalTime end) {

    public TimeBlock {
        if ((start == null) != (end == null))
            throw new IllegalArgumentException("Both start and end must be null, or both must be non-null");
    }
}
