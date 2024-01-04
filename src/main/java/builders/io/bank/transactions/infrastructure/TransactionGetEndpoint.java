package builders.io.bank.transactions.infrastructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/v1/transactions")
public interface TransactionGetEndpoint {
    @GetMapping(value = "/{address}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get transaction list of a userId", description = "A list of transactions receive")
    @ApiResponse(responseCode = "200", description = "List of transactions is obtained successfully")
    ResponseEntity<String> getTransactionsByAddress(@Parameter(description = "Address of a transaction")
                                                    @PathVariable String address);
}
