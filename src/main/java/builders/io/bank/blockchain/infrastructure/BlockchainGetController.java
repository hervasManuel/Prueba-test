package builders.io.bank.blockchain.infrastructure;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.abi.FunctionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.abi.datatypes.Function;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class BlockchainGetController implements BlockchainGetEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(BlockchainGetController.class);
    public static final String TRANSACTION_INVALID = "Transaction Invalid";
    private final Web3j web3j = Web3j.build(new HttpService("http://localhost:7545"));

    @Override
    public ResponseEntity blockchain(String from, String to) {
        logger.info("Successfully connected to Ganache");

        try {
            Web3ClientVersion clientVersion = web3j.web3ClientVersion().send();
            logger.info("Client version: {}", clientVersion.getWeb3ClientVersion());

            EthGasPrice gasPrice = web3j.ethGasPrice().send();
            logger.info("Default Gas Price: {}", gasPrice.getGasPrice());

            EthGetBalance balance = getEthBalance(from);
            logger.info("Balance of 'from' Wallet: {} Wei", balance.getBalance());

            logger.info("Balance in Ether format: {} ETH",
                    Convert.fromWei(web3j.ethGetBalance(from,
                                                        DefaultBlockParameterName.LATEST)
                                    .send().getBalance().toString(),
                                    Convert.Unit.ETHER));

            logger.info("Send Transaction: Transfer from: {}", from);
            logger.info("                             to: {}", to);
            logger.info("                          value: 50 ETH");
            String hash = sendTransaction(from,to);
            logger.info("Transaction executed. Hash: {}", hash);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException | ExecutionException | InterruptedException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Error sending json-rpc requests", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public EthGetBalance getEthBalance(String address) throws ExecutionException, InterruptedException {
        EthGetBalance result = new EthGetBalance();
        this.web3j.ethGetBalance(address,
                        DefaultBlockParameterName.LATEST)
                .sendAsync()
                .get();
        return result;
    }
    @Async
    public String sendTransaction(String from, String to) {
        String transactionHash;
        try {
            List inputParams = new ArrayList();
            List outputParams = new ArrayList();
            Function function = new Function("fuctionName", inputParams, outputParams);

            String encodedFunction = FunctionEncoder.encode(function);

            BigInteger nonce = BigInteger.valueOf(100);
            BigInteger gasPrice = BigInteger.valueOf(100);
            BigInteger gasLimit = BigInteger.valueOf(100);

            Transaction transaction = Transaction.createFunctionCallTransaction(from,
                                                                                nonce,
                                                                                gasPrice,
                                                                                gasLimit,
                                                                                to,
                                                                                encodedFunction);

            EthSendTransaction transactionResponse = web3j.ethSendTransaction(transaction).sendAsync().get();
            transactionHash = transactionResponse.getTransactionHash();

        } catch (Exception ex) {
            logger.error("Error sending transaction");
            return TRANSACTION_INVALID;
        }
        return transactionHash;
    }
}


