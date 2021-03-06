package pl.training.bank.account;

import lombok.Setter;
import pl.training.bank.common.ResultPage;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class JpaAccountRepository implements AccountRepository {

    private static final String SELECT_ACCOUNTS = "select a from Account a";
    private static final String SELECT_ACCOUNT_BY_NUMBER = "select a from Account a where a.number = :number";

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Account save(Account account) {
        entityManager.persist(account);
        entityManager.flush();
        entityManager.refresh(account);
        return account;
    }

    @Override
    public ResultPage<Account> get(int pageNumber, int pageSize) {
        List<Account> accounts = entityManager
                .createQuery(SELECT_ACCOUNTS, Account.class)
                .setFirstResult(pageNumber * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        return new ResultPage<>(accounts, pageNumber, -1);
    }

    @Override
    public Optional<Account> getByNumber(String accountNumber) {
        try {
            return Optional.of(entityManager
                    .createQuery(SELECT_ACCOUNT_BY_NUMBER, Account.class)
                    .setParameter("number", accountNumber)
                    .getSingleResult());
        } catch (NoResultException ex) {
            throw new AccountNotFoundException();
        }
    }

    @Override
    public void update(Account account) {
        if (entityManager.find(Account.class, account.getId()) == null) {
            throw new AccountNotFoundException();
        }
        entityManager.merge(account);
    }

}
