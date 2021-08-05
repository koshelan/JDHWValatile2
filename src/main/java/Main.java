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
        shops.add(new Shop(cashbox, "Магазин 1"));
        shops.add(new Shop(cashbox, "Магазин 2"));
        shops.add(new Shop(cashbox, "Магазин 3"));

        try {
            List<Future<Long>> results = executorService.invokeAll(shops);
            for (Future<Long> result : results) {
                result.get();
            }
            System.out.println("Итоговая выручка: " + cashbox.sum());
        } catch (Exception e) {
            e.printStackTrace();
        }
        executorService.shutdown();

    }

}
