package builders.io.bank.virtualmachine.application;

import java.util.ArrayList;
import java.util.List;

public class TrxOperationExecutor {
    private final List<TrxOperation> trxOperations = new ArrayList<>();
    public void executeOperation(TrxOperation trxOperation) {
        trxOperations.add(trxOperation);
        trxOperation.execute();
    }
}
