import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JsonFileAccountRepository implements AccountRepository {

    private final File file;
    private final ObjectMapper objectMapper;

    public JsonFileAccountRepository(String fileName) {
        this.file = new File(fileName);

        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void save(Account account) {
        List<Account> accounts = findAll();

        boolean updated = false;

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getId() == account.getId()) {
                accounts.set(i, account);
                updated = true;
                break;
            }
        }

        if (!updated) {
            accounts.add(account);
        }

        writeAccounts(accounts);
    }

    @Override
    public Optional<Account> findById(int id) {
        return findAll()
                .stream()
                .filter(account -> account.getId() == id)
                .findFirst();
    }

    @Override
    public List<Account> findAll() {
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try {
            return objectMapper.readValue(
                    file,
                    new TypeReference<List<Account>>() {}
            );
        } catch (IOException e) {
            throw new RuntimeException("Unable to read accounts.json", e);
        }
    }

    @Override
    public void deleteById(int id) {
        List<Account> accounts = findAll();

        boolean removed = accounts.removeIf(
                account -> account.getId() == id
        );

        if (removed) {
            writeAccounts(accounts);
        }
    }

    private void writeAccounts(List<Account> accounts) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, accounts);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write accounts.json", e);
        }
    }
}