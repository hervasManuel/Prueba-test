package builders.io.bank;

import builders.io.bank.shared.infrastructure.bus.event.info.InfoConsumer;
import builders.io.bank.shared.infrastructure.bus.event.virtualmachine.VirtualMachineConsumer;
import builders.io.bank.shared.infrastructure.bus.event.wallet.WalletConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import reactor.bus.EventBus;

import static reactor.bus.selector.Selectors.$;

@SpringBootApplication
@EnableJpaRepositories
public class BankApplication implements CommandLineRunner {
	@Autowired
	private EventBus eventBus;
	@Autowired
	private WalletConsumer walletConsumer;
	@Autowired
	private InfoConsumer infoConsumer;
	@Autowired
	private VirtualMachineConsumer virtualMachineConsumer;
	@Override
	public void run(String... args) throws Exception {
		eventBus.on($("users_topic"), walletConsumer);
		eventBus.on($("wallets_topic"), infoConsumer);
		eventBus.on($("transactions_topic"), virtualMachineConsumer);
	}

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

}
