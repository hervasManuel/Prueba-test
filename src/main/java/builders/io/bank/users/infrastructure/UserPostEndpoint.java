package builders.io.bank.users.infrastructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/users")
public interface UserPostEndpoint {
    @PostMapping
    @Operation(summary = "Create a new user", description = "A new user is created")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A new user successfully created"),
            @ApiResponse(responseCode = "409", description = "User already exists"),
    })
    ResponseEntity<String> newUser(@Parameter(description = "User information") @RequestBody RequestNewUser userDTO);
}
