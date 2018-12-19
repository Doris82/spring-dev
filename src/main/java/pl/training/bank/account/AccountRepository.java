package pl.training.bank.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> getByNumber(String accountNumber);

    @Query("select a from Account a where a.id = :id")
    Account getById(@Param("id") Long id);

    @Query("select a from Account a where a.balance >= :balance")
    List<Account> getAccountsWithBalance(@Param("balance") Long balance);

}
