package com.liuyihui.common.datastructure;

import java.nio.file.Path;

public class PathMenu {
    private String[] paths = new String[]{"/sdcard/oohlink/player/" + ".screen" +
            "/0A638DE2475566D0691CECD3F00B19D3",
            "/sdcard/oohlink/player/" + ".screen" +
                    "/549A2C1EBCC166B1CD6104B4BC0609A9"};

    private int i = 0;

    public String getNextPath() {
        String path;
        if (i > (paths.length - 1)) {
            i = 0;
        }
        path = paths[i];
        i += 1;
        return path;
    }


    public static void main(String[] args) {
        PathMenu pathMenu = new PathMenu();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(pathMenu.getNextPath());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }
}