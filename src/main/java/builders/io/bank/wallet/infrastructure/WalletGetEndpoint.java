package builders.io.bank.wallet.infrastructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/wallets")
@RestController
public interface WalletGetEndpoint {
    @GetMapping(value = "/{address}")
    @Operation(summary = "Get balance from address wallet", description = "Balance is return")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Balance is obtained successfully"),
            @ApiResponse(responseCode = "404", description = "Balance not found"),
    })
    ResponseEntity<String> getBalance(@Parameter(description = "Address of a wallet") @PathVariable String address);
}
