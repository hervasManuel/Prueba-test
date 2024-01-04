package builders.io.bank.wallet.infrastructure.hibernate;

import builders.io.bank.shared.domain.UserId;
import builders.io.bank.wallet.domain.Wallet;
import builders.io.bank.wallet.domain.WalletRepository;
import builders.io.bank.wallet.infrastructure.persistence.WalletEntity;
import builders.io.bank.wallet.infrastructure.persistence.WalletEntityMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public final class WalletJPARepository implements WalletRepository {
    private final SpringDataWalletRepository repository;
    private final WalletEntityMapper walletEntityMapper;
    public WalletJPARepository(SpringDataWalletRepository repository, WalletEntityMapper walletEntityMapper){
        this.repository = repository;
        this.walletEntityMapper = walletEntityMapper;
    }
    @Override
    public List<Wallet> findAll(){
        List<Wallet> walletList = new ArrayList<>();
        repository.findAll().forEach(walletEntity -> walletList.add(walletEntityMapper.walletEntityToWallet(walletEntity)));
        return walletList;
    }
    @Override
    public Wallet save(Wallet wallet){
        WalletEntity entityWallet = walletEntityMapper.walletToWalletEntity(wallet);
        return walletEntityMapper.walletEntityToWallet(repository.save(entityWallet));
    }
    @Override
    public Optional<Wallet> searchByAddress(String address){
        return repository.findByAddress(address)
                .map(walletEntityMapper::walletEntityToWallet);
    }
    @Override
    public Optional<Wallet> searchByUserId(UserId id){
        return repository.findByUserId(id.value())
                .map(walletEntityMapper::walletEntityToWallet);

    }
}
