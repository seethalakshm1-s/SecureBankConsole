import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonService {

    private final ObjectMapper objectMapper;

    public JsonService() {
        objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public String transactionToJson(Transaction transaction) throws Exception {
        return objectMapper.writeValueAsString(transaction);
    }

    public Transaction jsonToTransaction(String json) throws Exception {
        return objectMapper.readValue(json, Transaction.class);
    }
}