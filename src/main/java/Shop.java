import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.LongAdder;

public class Shop extends Thread implements Callable<Long> {

    private static final int QUANTITY_SALES = 10;
    private static final int MAX_PRICE = 1_000_000;

    LongAdder cashbox;
    long checkSum = 0;


    public Shop(LongAdder cashbox, String name) {
        this.cashbox = cashbox;
        super.setName(name);
    }


    public Long call() {
        new Random().ints(QUANTITY_SALES, 0, MAX_PRICE).forEach(i -> {
            checkSum += i;
            cashbox.add(i);
        });
        System.out.printf("контрольная сумма %s : %s\n", getName(), checkSum);
        return checkSum;
    }

}
