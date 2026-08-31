import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JsonFileAccountRepository implements AccountRepository {

    private final JsonService jsonService = new JsonService();

    private final String fileName = "accounts.json";

    @Override
    public void save(Account account) throws Exception {

        List<Account> accounts = findAll();

        Optional<Account> oldAccount = findById(account.getId());

        if (oldAccount.isPresent()) {
            accounts.remove(oldAccount.get());
        }

        accounts.add(account);

        String json = jsonService.accountsToJson(accounts);

        java.nio.file.Files.writeString(
            java.nio.file.Path.of(fileName),
            json
        );
    }

    @Override
    public Optional<Account> findById(int id) throws Exception {

        List<Account> accounts = findAll();

        for (Account account : accounts) {

            if (account.getId() == id) {
                return Optional.of(account);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Account> findAll() throws Exception {

        File file = new File(fileName);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        String json = java.nio.file.Files.readString(
            file.toPath()
        );

        if (json.isEmpty()) {
            return new ArrayList<>();
        }

        return jsonService.jsonToAccounts(json);
    }

    @Override
    public void deleteById(int id) throws Exception {

        List<Account> accounts = findAll();

        accounts.removeIf(
            account -> account.getId() == id
        );

        String json = jsonService.accountsToJson(accounts);

        java.nio.file.Files.writeString(
            java.nio.file.Path.of(fileName),
            json
        );
    }
}