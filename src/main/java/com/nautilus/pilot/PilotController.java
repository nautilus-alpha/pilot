package com.nautilus.pilot;

import com.nautilus.pilot.gui.MainFrame;
import com.nautilus.pilot.util.ApiUtil;
import org.jfree.data.xy.DefaultXYDataset;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Iterator;

public class PilotController {

    public static void main(String[] args) {


        DefaultXYDataset chartDataSet = new DefaultXYDataset();
        double[][] tradePrice = new double[2][200];
        double[][] openingPrice = new double[2][200];
        double[][] highPrice = new double[2][200];
        double[][] lowPrice = new double[2][200];
        double[][] tradeVolume = new double[2][200];
        chartDataSet.addSeries("tradePrice", tradePrice);
        chartDataSet.addSeries("openingPrice", openingPrice);
        chartDataSet.addSeries("highPrice", highPrice);
        chartDataSet.addSeries("lowPrice", lowPrice);
        // chartDataSet.addSeries("tradeVolume", tradeVolume);
        MainFrame mainFrame = new MainFrame(chartDataSet);

        while(true) {
            try {
                JSONArray array = ApiUtil.getRecentPrices();

                int idx = 0;
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Iterator<JSONObject> objItr = array.iterator();
                while (objItr.hasNext()) {
                    JSONObject obj = objItr.next();
                    String candle_date_time_kst = (String) obj.get("candle_date_time_kst");
                    Long candleDateLong = format.parse(candle_date_time_kst).getTime();
                    Double candle_acc_trade_price = (Double) obj.get("candle_acc_trade_price");
                    Double candle_acc_trade_volume = (Double) obj.get("candle_acc_trade_volume");

                    tradePrice[0][idx] = candleDateLong;
                    openingPrice[0][idx] = candleDateLong;
                    highPrice[0][idx] = candleDateLong;
                    lowPrice[0][idx] = candleDateLong;
                    tradeVolume[0][idx] = candleDateLong;

                    tradePrice[1][idx] = (Double) obj.get("trade_price");
                    openingPrice[1][idx] = (Double) obj.get("opening_price");
                    highPrice[1][idx] = (Double) obj.get("high_price");
                    lowPrice[1][idx] = (Double) obj.get("low_price");
                    tradeVolume[1][idx] = candle_acc_trade_volume;
                    idx++;
                }
                mainFrame.plot.setDataset(chartDataSet);
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
