package com.nautilus.pilot.gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;

public class MainFrame extends JFrame {
    public XYPlot plot;

    public XYPlot getPlot(){
        return this.plot;
    }

    public MainFrame(XYDataset dataset){
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // chart area
        JFreeChart chart = ChartFactory.createTimeSeriesChart("pilot Chart",
                "time",
                "values",
                dataset,
                true,
                true,
                true);
        ChartPanel cp = new ChartPanel(chart);
        add(cp);
        plot = chart.getXYPlot();

        /*
        // menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("파일");
        JMenu helpMenu = new JMenu("Help");

        // 파일 메뉴 생성
        fileMenu.add(new JMenuItem("새 파일"));
        fileMenu.getItem(0).setAccelerator
                (KeyStroke.getKeyStroke('N', InputEvent.CTRL_MASK ^ InputEvent.ALT_MASK)); //단축키설정
        fileMenu.add(new JMenuItem("열기"));
        fileMenu.add(new JMenuItem("저장"));
        fileMenu.addSeparator(); //구분선 추가
        fileMenu.add(new JMenuItem("종료"));

        // 도움 메뉴 생성
        helpMenu.add(new JMenuItem("버전"));
        helpMenu.add(new JMenuItem("정보"));

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
         */

        setVisible(true);
    }
}
