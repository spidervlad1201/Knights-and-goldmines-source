package com.vakuor.kingsandgoldmines.desktop;

//import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class PackSkinsByMax {

    public static void main(String[] args) {
        TexturePacker.Settings s = new TexturePacker.Settings();
        TexturePacker.process(s,"Main/workfiles/finished", "Android/assets/data", "loading.pack");
        //TexturePacker2.Settings s = new TexturePacker2.Settings();
        //TexturePacker2.process(s, "Main/workfiles/finished", "Android/assets/data", "loading.pack");
    }
}
