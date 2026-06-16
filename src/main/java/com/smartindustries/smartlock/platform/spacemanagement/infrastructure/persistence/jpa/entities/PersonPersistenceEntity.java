package com.smartindustries.smartlock.platform.spacemanagement.infrastructure.persistence.jpa.entities;

import com.smartindustries.smartlock.platform.access.infrastructure.persistence.jpa.entities.AccessGroupPersistenceEntity;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.FullName;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.IdentityDocument;
import com.smartindustries.smartlock.platform.shared.infrastructure.persistence.jpa.converters.IdentityDocumentPersistenceConverter;
import com.smartindustries.smartlock.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "people")
@Getter
@Setter
@NoArgsConstructor
public class PersonPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private OrganizationPersistenceEntity organization;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "firstName", column = @Column(name = "first_name", nullable = false, length = 100)),
        @AttributeOverride(name = "lastName", column = @Column(name = "last_name", nullable = false, length = 100))
    })
    private FullName name;

    @Convert(converter = IdentityDocumentPersistenceConverter.class)
    @Column(name = "identity_document", nullable = false, length = 50)
    private IdentityDocument identityDocument;

}
