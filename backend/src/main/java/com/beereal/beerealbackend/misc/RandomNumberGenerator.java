package com.beereal.beerealbackend.misc;

public class RandomNumberGenerator {

    public int getIntBetween(int min, int max) {

        return (int) (Math.random()*max+min);
    }
}
