package com.yuyakaido.android.cardstackview.internal;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.yuyakaido.android.cardstackview.internal.SwipeToRevert.RIGHT_TO_LEFT;
import static com.yuyakaido.android.cardstackview.internal.SwipeToRevert.LEFT_TO_RIGHT;
import static com.yuyakaido.android.cardstackview.internal.SwipeToRevert.BOTTOM_TO_TOP;
import static com.yuyakaido.android.cardstackview.internal.SwipeToRevert.TOP_TO_BOTTOM;

/**
 * Swipe to revert accepted values
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef(value = {BOTTOM_TO_TOP, TOP_TO_BOTTOM, LEFT_TO_RIGHT, RIGHT_TO_LEFT})
public @interface SwipeToRevert {
    int TOP_TO_BOTTOM = 0;
    int BOTTOM_TO_TOP = 1;
    int LEFT_TO_RIGHT = 2;
    int RIGHT_TO_LEFT = 3;
}
