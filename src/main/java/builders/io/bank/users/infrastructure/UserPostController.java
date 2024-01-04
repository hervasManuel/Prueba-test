package builders.io.bank.users.infrastructure;

import builders.io.bank.users.application.UserExistException;
import builders.io.bank.users.application.UserRegister;
import builders.io.bank.users.domain.AuthUsername;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPostController implements UserPostEndpoint{
    private static final Logger logger = LoggerFactory.getLogger(UserPostController.class);
    private final UserRegister userRegister;
    public UserPostController(UserRegister userRegister){
        this.userRegister = userRegister;
    }
   @Override
    public ResponseEntity newUser(@RequestBody RequestNewUser requestNewUser) {
       try {
            AuthUsername username = AuthUsername.create(requestNewUser.getUsername());
            logger.info("New User request: {}", requestNewUser.getUsername());
            userRegister.register(username);
            logger.info("User created correctly: {}",requestNewUser.getUsername());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (UserExistException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
        }

    }
}
