import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    public static void main(String[] args) {

        LongAdder cashbox = new LongAdder();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Shop> shops = new ArrayList<>();
        shops.add(new Shop(cashbox,"Магазин 1"));
        shops.add(new Shop(cashbox,"Магазин 2"));
        shops.add(new Shop(cashbox,"Магазин 3"));

        try {
            List<Future<Long>> results = executorService.invokeAll(shops);
            long l = 0L;
            for (Future<Long> result : results) {
                l += result.get();
            }
            System.out.println("Итоговая выручка: "+cashbox.sum());
            System.out.println("Контрольная сумма: "+l);

            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
        executorService.shutdown();

    }

}
