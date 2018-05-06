package com.vakuor.kingsandgoldmines.view;

import com.badlogic.gdx.graphics.Pixmap;

public class LoadingScreen {
//    long loadStart = TimeUtils.nanoTime();
//    public PreloaderCallback getPreloaderCallback () {
//        final Canvas canvas = Canvas.createIfSupported();
//        canvas.setWidth("" + (int)(config.width * 0.7f) + "px");
//        canvas.setHeight("70px");
//        getRootPanel().add(canvas);
//        final Context2d context = canvas.getContext2d();
//        context.setTextAlign(TextAlign.CENTER);
//        context.setTextBaseline(TextBaseline.MIDDLE);
//        context.setFont("18pt Calibri");
//
//        return new PreloaderCallback() {
//            @Override
//            public void done () {
//                context.fillRect(0, 0, 300, 40);
//            }
//
//            @Override
//            public void loaded (String file, int loaded, int total) {
//                System.out.println("loaded " + file + "," + loaded + "/" + total);
//                String color = Pixmap.make(30, 30, 30, 1);
//                context.setFillStyle(color);
//                context.setStrokeStyle(color);
//                context.fillRect(0, 0, 300, 70);
//                color = Pixmap.make(200, 200, 200, (((TimeUtils.nanoTime() - loadStart) % 1000000000) / 1000000000f));
//                context.setFillStyle(color);
//                context.setStrokeStyle(color);
//                context.fillRect(0, 0, 300 * (loaded / (float)total) * 0.97f, 70);
//
//                context.setFillStyle(Pixmap.make(50, 50, 50, 1));
//                context.fillText("loading", 300 / 2, 70 / 2);
//            }
//
//            @Override
//            public void error (String file) {
//                System.out.println("error: " + file);
//            }
//        };
//    }
}
