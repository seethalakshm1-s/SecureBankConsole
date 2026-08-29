import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    void save(Account account);

    Optional<Account> findById(int id);

    List<Account> findAll();

    void deleteById(int id);
}