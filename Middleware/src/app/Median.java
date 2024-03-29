package app;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Median implements Runnable {
    
    private final String MEDIAN_IP = "http://10.180.14.103:8080/";

    private CountDownLatch countDownLatch;
    private String message;
    private double result;

    public Median(CountDownLatch countDownLatch, String message) {
        this.countDownLatch = countDownLatch;
        this.message = message;
    }

    public double getResult() {
        return result;
    }

    @Override
    public void run() {
        try {
            URL objUrl = new URL(MEDIAN_IP + "MedianServer/median/calculate;" + message);

            HttpURLConnection connection = (HttpURLConnection) objUrl.openConnection();
            connection.setRequestMethod("GET");

            System.out.println("Mean server response message: " + connection.getResponseMessage());

            Scanner s = new Scanner(connection.getInputStream());

            result = Double.parseDouble(s.nextLine());

            s.close();

            countDownLatch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}