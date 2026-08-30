import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JsonFileAccountRepository implements AccountRepository {

    private final File accountsFile;
    private final File transactionsFile;
    private final ObjectMapper objectMapper;

    public JsonFileAccountRepository() {
        this("accounts.json", "transactions.json");
    }

    public JsonFileAccountRepository(
            String accountsFileName,
            String transactionsFileName) {

        this.accountsFile = new File(accountsFileName);
        this.transactionsFile = new File(transactionsFileName);

        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void save(Account account) {

        List<AccountDTO> accountDTOs = readAccountDTOs();

        AccountDTO dto = new AccountDTO(
                account.getId(),
                account.getCustomerName(),
                account.getBalance()
        );

        boolean updated = false;

        for (int i = 0; i < accountDTOs.size(); i++) {

            if (accountDTOs.get(i).getId() == account.getId()) {
                accountDTOs.set(i, dto);
                updated = true;
                break;
            }
        }

        if (!updated) {
            accountDTOs.add(dto);
        }

        writeAccountDTOs(accountDTOs);

        saveTransactionsForAccount(account);
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

        List<AccountDTO> accountDTOs = readAccountDTOs();

        List<Account> accounts = new ArrayList<>();

        for (AccountDTO dto : accountDTOs) {

            Account account = new Account(
                    dto.getId(),
                    dto.getCustomerName(),
                    dto.getBalance()
            );

            loadTransactionsForAccount(account);

            accounts.add(account);
        }

        return accounts;
    }

    @Override
    public void deleteById(int id) {

        List<AccountDTO> accountDTOs = readAccountDTOs();

        boolean removed = accountDTOs.removeIf(
                dto -> dto.getId() == id
        );

        if (removed) {
            writeAccountDTOs(accountDTOs);
            deleteTransactionsForAccount(id);
        }
    }

    private List<AccountDTO> readAccountDTOs() {

        if (!accountsFile.exists()) {
            return new ArrayList<>();
        }

        try {

            return objectMapper.readValue(
                    accountsFile,
                    new TypeReference<List<AccountDTO>>() {}
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to read accounts.json",
                    e
            );
        }
    }

    private void writeAccountDTOs(List<AccountDTO> accountDTOs) {

        try {

            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(accountsFile, accountDTOs);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to write accounts.json",
                    e
            );
        }
    }

    private List<TransactionRecordDTO> readTransactionRecords() {

        if (!transactionsFile.exists()) {
            return new ArrayList<>();
        }

        try {

            return objectMapper.readValue(
                    transactionsFile,
                    new TypeReference<List<TransactionRecordDTO>>() {}
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to read transactions.json",
                    e
            );
        }
    }

    private void writeTransactionRecords(
            List<TransactionRecordDTO> records) {

        try {

            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(transactionsFile, records);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to write transactions.json",
                    e
            );
        }
    }

    private void saveTransactionsForAccount(Account account) {

        List<TransactionRecordDTO> records =
                readTransactionRecords();

        records.removeIf(
                record -> record.getAccountId() == account.getId()
        );

        for (Transaction transaction :
                account.getTransactions().values()) {

            records.add(
                    new TransactionRecordDTO(
                            account.getId(),
                            transaction
                    )
            );
        }

        writeTransactionRecords(records);
    }

    private void loadTransactionsForAccount(Account account) {

        List<TransactionRecordDTO> records =
                readTransactionRecords();

        for (TransactionRecordDTO record : records) {

            if (record.getAccountId() == account.getId()) {

                account.addTransaction(
                        record.getTransaction()
                );
            }
        }
    }

    private void deleteTransactionsForAccount(int accountId) {

        List<TransactionRecordDTO> records =
                readTransactionRecords();

        records.removeIf(
                record -> record.getAccountId() == accountId
        );

        writeTransactionRecords(records);
    }
}