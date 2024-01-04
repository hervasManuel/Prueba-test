package builders.io.bank.shared.infrastructure.bus.event.virtualmachine;

import builders.io.bank.shared.domain.bus.event.DomainEvent;
import builders.io.bank.transactions.domain.TxnMethod;
import builders.io.bank.virtualmachine.application.*;
import builders.io.bank.virtualmachine.application.TransferTrx;
import builders.io.bank.wallet.application.WalletUpdateBalance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
@Service
public class VirtualMachineActionsServiceImpl implements VirtualMachineActionsService {
    private final Logger logger = LoggerFactory.getLogger(VirtualMachineActionsServiceImpl.class);
    @Autowired
    private WalletUpdateBalance walletUpdateBalance;
    @Override
    public void executeTransactionOnTransactionSent(DomainEvent domainEvent) {
        try {
            logger.info("Bank Virtual Machine: Transaction event received");
            var eventPrimitives = domainEvent.toPrimitives();
            TxnMethod method = TxnMethod.valueOf(eventPrimitives.get("method").toString());
            String from = eventPrimitives.get("from").toString();
            String to = eventPrimitives.get("to").toString();
            BigDecimal value = (BigDecimal) eventPrimitives.get("value");
            TrxOperationExecutor trxOperationExecutor = new TrxOperationExecutor();

            switch (method) {
                case ADD_BALANCE -> trxOperationExecutor.executeOperation(new AddBalanceTrx(to,value, walletUpdateBalance));
                case TRANSFER -> trxOperationExecutor.executeOperation(new TransferTrx(from,to,value, walletUpdateBalance));
                default -> throw new IllegalArgumentException();
            }
        }
        catch (IllegalArgumentException e){
            logger.warn("\"Method invaid!: {}", e.getMessage());
        }
    }
}