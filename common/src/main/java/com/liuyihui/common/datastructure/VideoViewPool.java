package com.liuyihui.common.datastructure;

import javax.swing.text.View;

/**
 * 双对象切换池
 */
public class VideoViewPool {
    //两个videoView
    private VideoView videoViewA;
    private VideoView videoViewB;
    //两个指针
    private VideoView pickedVideoView;
    private VideoView freedVideoView;

    public VideoViewPool() {
    }

    //获取一个未使用的videoView
    public synchronized VideoView pickFreeVideoView() {
        if (videoViewA == null) {
            videoViewA = createNewVideoView();
            pickedVideoView = videoViewA;
            return videoViewA;
        }
        if (videoViewB == null) {
            videoViewB = createNewVideoView();
            pickedVideoView = videoViewB;
            freedVideoView = videoViewA;
            return pickedVideoView;
        }

        VideoView ret = freedVideoView;

        //两个指针交换指向
        if (freedVideoView == videoViewA) {
            freedVideoView = videoViewB;
            pickedVideoView = videoViewA;
        } else {
            freedVideoView = videoViewA;
            pickedVideoView = videoViewB;
        }

        return ret;
    }

    public VideoView getPickedVideoView() {
        return pickedVideoView;
    }

    public VideoView getFreedVideoView() {
        return freedVideoView;
    }

    public VideoView createNewVideoView() {
        VideoView mVideoView = new VideoView();
        return mVideoView;
    }

    public static void main(String[] args) {
        VideoViewPool pool = new VideoViewPool();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    VideoView videoView = pool.pickFreeVideoView();
                    System.out.println(videoView);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}