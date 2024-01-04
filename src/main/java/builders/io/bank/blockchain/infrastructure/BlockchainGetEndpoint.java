package builders.io.bank.blockchain.infrastructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/blockchain/{from}/{to}")
@RestController
public interface BlockchainGetEndpoint {
    @GetMapping
    @Operation(summary = "Launch a Ethereum connection test", description = "Launch a Ethereum demo connection test")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Connection test successfully"),
            @ApiResponse(responseCode = "500", description = "Error in the connection")
    })
    ResponseEntity<String> blockchain(@Parameter(description = "Wallet address origin of transaction") @PathVariable String from,
                                      @Parameter(description = "Wallet address destination of transaction") @PathVariable String to);
}
