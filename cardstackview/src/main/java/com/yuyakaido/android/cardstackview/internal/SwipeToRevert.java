package com.yuyakaido.android.cardstackview.internal;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.yuyakaido.android.cardstackview.internal.SwipeToRevert.BOTTOM;
import static com.yuyakaido.android.cardstackview.internal.SwipeToRevert.DISABLED;
import static com.yuyakaido.android.cardstackview.internal.SwipeToRevert.LEFT;
import static com.yuyakaido.android.cardstackview.internal.SwipeToRevert.RIGHT;
import static com.yuyakaido.android.cardstackview.internal.SwipeToRevert.TOP;

/**
 * Swipe to revert accepted values
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef(value = {DISABLED, TOP, BOTTOM, RIGHT, LEFT})
public @interface SwipeToRevert {
    int DISABLED = 0;
    int TOP = 1;
    int BOTTOM = 2;
    int RIGHT = 3;
    int LEFT = 4;
}
