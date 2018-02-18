package com.yuyakaido.android.cardstackview.internal;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.view.View;

public class Util {

    private Util() {
    }

    public static float toPx(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    static double getRadian(float x1, float y1, float x2, float y2) {
        float width = x2 - x1;
        float height = y1 - y2;
        return Math.atan(Math.abs(height) / Math.abs(width));
    }

    static Point getTargetPoint(float x1, float y1, float x2, float y2) {
        float radius = 2000f;
        double radian = Util.getRadian(x1, y1, x2, y2);

        Quadrant quadrant = getQuadrant(x1, y1, x2, y2);
        if (quadrant == Quadrant.TopLeft) {
            double degree = Math.toDegrees(radian);
            degree = 180 - degree;
            radian = Math.toRadians(degree);
        } else if (quadrant == Quadrant.BottomLeft) {
            double degree = Math.toDegrees(radian);
            degree = 180 + degree;
            radian = Math.toRadians(degree);
        } else if (quadrant == Quadrant.BottomRight) {
            double degree = Math.toDegrees(radian);
            degree = 360 - degree;
            radian = Math.toRadians(degree);
        } else {
            double degree = Math.toDegrees(radian);
            radian = Math.toRadians(degree);
        }

        double x = radius * Math.cos(radian);
        double y = radius * Math.sin(radian);

        return new Point((int) x, (int) y);
    }

    static Quadrant getQuadrant(float x1, float y1, float x2, float y2) {
        if (x2 > x1) { // Right
            if (y2 > y1) { // Bottom
                return Quadrant.BottomRight;
            } else { // Top
                return Quadrant.TopRight;
            }
        } else { // Left
            if (y2 > y1) { // Bottom
                return Quadrant.BottomLeft;
            } else { // Top
                return Quadrant.TopLeft;
            }
        }
    }

    public static Point getSwipeToRevertPoint(@SwipeToRevert int swipeToRevert, @NonNull View container) {
        switch (swipeToRevert) {
            case SwipeToRevert.TOP_TO_BOTTOM:
                return new Point(0, container.getHeight());
            case SwipeToRevert.BOTTOM_TO_TOP:
                return new Point(0, -container.getHeight());
            case SwipeToRevert.LEFT_TO_RIGHT:
                return new Point(-container.getWidth(), 0);
            case SwipeToRevert.RIGHT_TO_LEFT:
                return new Point(container.getWidth(), 0);
            default:
                return new Point(0, 0);
        }

    }

}
