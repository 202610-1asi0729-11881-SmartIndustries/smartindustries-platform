package com.smartindustries.smartlock.platform.report.infrastructure.persistence.jpa.adapters;

import com.smartindustries.smartlock.platform.report.domain.model.aggregates.ScheduleDay;
import com.smartindustries.smartlock.platform.report.domain.repositories.ScheduleDayRepository;
import com.smartindustries.smartlock.platform.report.infrastructure.persistence.jpa.assemblers.ScheduleDayPersistenceAssembler;
import com.smartindustries.smartlock.platform.report.infrastructure.persistence.jpa.repositories.ScheduleDayPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ScheduleDayRepositoryImpl implements ScheduleDayRepository {

    private final ScheduleDayPersistenceRepository jpaRepository;

    public ScheduleDayRepositoryImpl(ScheduleDayPersistenceRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<ScheduleDay> findById(Long id) {
        return jpaRepository.findById(id)
                .map(ScheduleDayPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public ScheduleDay save(ScheduleDay scheduleDay) {
        var entity = ScheduleDayPersistenceAssembler.toPersistenceFromDomain(scheduleDay);
        var saved = jpaRepository.save(entity);
        return ScheduleDayPersistenceAssembler.toDomainFromPersistence(saved);
    }
}
