package com.vakuor.kingsandgoldmines.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class AtlasPacker {
    public static void main (String[] args) {
        TexturePacker.process("input/Block", "output/Block", "Block");
        TexturePacker.process("input/Head", "output/Head", "Heads");
        TexturePacker.process("input/Archer", "output/Archer", "Archers");
    }
}
