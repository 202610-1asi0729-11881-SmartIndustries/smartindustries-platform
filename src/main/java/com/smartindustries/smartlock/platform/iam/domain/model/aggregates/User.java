package com.smartindustries.smartlock.platform.iam.domain.aggregates;

import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.Email;
import com.smartindustries.smartlock.platform.shared.domain.model.valueobjects.FullName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

@Getter
public class User extends AbstractAggregateRoot<User> {

    @Setter
    private Long id;

    @Setter
    private FullName name;

    @Setter
    private String password;

    @Setter
    private Email email;

    public User(String firstName, String lastName, String password, String email){
        this.name = new FullName(firstName, lastName);
        this.password = password;
        this.email = new Email(email);
    }

    public User(){}
}
