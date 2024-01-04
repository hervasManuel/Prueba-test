package builders.io.bank.users.infrastructure.persistence;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -7641097164174744444L;
    @Id
    private String userId;
    @Column(nullable = false)
    private String username;

    public UserEntity (String userId, String username){
        this.userId = userId;
        this.username = username;
    }
    public UserEntity(){}
    public String userId() {
        return userId;
    }
    public String username() {
        return username;
    }
}


