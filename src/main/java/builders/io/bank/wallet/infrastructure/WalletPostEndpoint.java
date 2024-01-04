package builders.io.bank.wallet.infrastructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/wallets")
public interface WalletPostEndpoint {
    @PostMapping
    @Operation(summary = "Create a new wallet", description = "A new wallet is created")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A new wallet successfully created"),
            @ApiResponse(responseCode = "409", description = "Wallet already exists"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters")
    })
    ResponseEntity<String> createWallet(@Parameter(description = "Wallet information")
                                        @RequestBody RequestNewWallet requestUserId);
}
