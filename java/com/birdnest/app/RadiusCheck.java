package com.birdnest.app;

public class RadiusCheck {
    public static boolean isInCircle(float x, float y) {
        float distanceX = Math.abs(x - 250000);
        float distanceY = Math.abs(y - 250000);
        float distance = (float) Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        return distance <= 100000;
    }
    public static float distancefromCircle(float x, float y) {
        float distanceX = Math.abs(x - 250000);
        float distanceY = Math.abs(y - 250000);
        float distance = (float) Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        return distance;
    }

}
