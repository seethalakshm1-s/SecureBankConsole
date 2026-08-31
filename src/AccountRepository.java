import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    void save(Account account)throws Exception;

    Optional<Account> findById(int id)throws Exception;

    List<Account> findAll()throws Exception;

    void deleteById(int id)throws Exception;
}