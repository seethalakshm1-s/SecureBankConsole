public class JsonTest {

    public static void main(String[] args) throws Exception {

        JsonService jsonService = new JsonService();

        String json = """
                {
                  "transactionId" : 25,
                  "type" : "DEPOSIT",
                  "amount" : 5000.0,
                  "fromAccountId" : 1001,
                  "toAccountId" : 1001,
                  "timestamp" : "2026-08-30T19:00:00"
                }
                """;

        Transaction transaction =
                jsonService.jsonToTransaction(json);

        System.out.println("===== JSON to Transaction =====");
        System.out.println("Transaction ID : " + transaction.getTransactionId());
        System.out.println("Type : " + transaction.getType());
        System.out.println("Amount : ₹" + transaction.getAmount());
        System.out.println("From Account : " + transaction.getFromAccountId());
        System.out.println("To Account : " + transaction.getToAccountId());
        System.out.println("Date & Time : " + transaction.getTimestamp());
    }
}