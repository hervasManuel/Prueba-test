package builders.io.bank.transactions.infrastructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/transactions")
public interface TransactionPostEndpoint {
    @PostMapping
    @Operation(summary = "Send a transaction", description = "A new transaction is sent")
    @ApiResponse(responseCode = "200", description = "A new transactions successfully sent")
    ResponseEntity<String> sendTransaction(@Parameter(description = "Transactions information")
                                           @RequestBody TransactionRequest requestTransaction);
}
