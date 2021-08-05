import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.LongAdder;

public class Shop implements Callable<Long> {

    private static final int QUANTITY_SALES = 10;
    private static final int MAX_PRICE = 1_000_000;

    private final String name;
    LongAdder cashbox;
    long checkSum;

    public Shop(LongAdder cashbox, String name) {
        this.cashbox = cashbox;
        this.name = name;
    }

    public Long call() {
        checkSum = 0;
        new Random().ints(QUANTITY_SALES, 0, MAX_PRICE).forEach(i -> {
            checkSum += i;
            cashbox.add(i);
        });
        System.out.printf("Cумма продаж %s : %s\n", getName(), checkSum);
        return checkSum;
    }

    public String getName() {
        return name;
    }

}
