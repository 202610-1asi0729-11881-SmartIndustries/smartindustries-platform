package com.smartindustries.smartlock.platform.iam.infrastructure.persistence.jpa.entities;

import com.smartindustries.smartlock.platform.administration.infrastructure.persistence.jpa.entities.MembershipPersistenceEntity;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.Email;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.FullName;
import com.smartindustries.smartlock.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import com.smartindustries.smartlock.platform.shared.interfaces.rest.converters.EmailPersistenceConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "firstName", column = @Column(name = "first_name", nullable = false)),
        @AttributeOverride(name = "lastName", column = @Column(name = "last_name", nullable = false)),
    })
    private FullName name;

    @Column(name = "password", nullable = false)
    private String password;

    @Convert(converter = EmailPersistenceConverter.class)
    @Column(name = "email", nullable = false)
    private Email email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MembershipPersistenceEntity> memberships = new ArrayList<>();
}
