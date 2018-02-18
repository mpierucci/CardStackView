package com.yuyakaido.android.cardstackview.internal;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;

import com.yuyakaido.android.cardstackview.SwipeDirection;

/**
 * Delegate class to handle swipe to revert logic
 */
class SwipeToRevertDelegate {
    private boolean willSwipeToRevert; // flag to indicate if the user started a swipe that should revert
    boolean isSwipingToRevert; // indicates if the user is currently swiping to revert
    @SwipeToRevert
    private final int swipeToRevertDirection; // swipe direction that perform the revert
    final boolean swipeToRevertEnabled;

    SwipeToRevertDelegate(@NonNull CardStackOption cardStackOption) {
        this.swipeToRevertDirection = cardStackOption.swipeToReverseDirection;
        this.swipeToRevertEnabled = cardStackOption.isSwipeToRevertEnabled;
    }

    /**
     * Swipe actions was finished, clear swiping flags
     */
    void swipeActionFinished() {
        willSwipeToRevert = false;
        isSwipingToRevert = false;
    }

    /**
     * Called upon  {@link MotionEvent#ACTION_UP} finished, to determine if should notify about a revert due to swiping.
     *
     * @param currentSwipeDirection direction set in manifest or code accepted for swipe to revert.
     *                              see {@link SwipeToRevert}
     * @return true if was swiping to revert and the swioe was inthe declare3d direction
     */
    boolean shouldSwipeAndRevert(SwipeDirection currentSwipeDirection) {
        return isSwipingToRevert && wasSwipeToRevert(currentSwipeDirection);
    }

    /**
     * Checks on initial dragging/ swiping, if said effect should be locked due to being in the direction
     * of the settled {@link SwipeToRevert} direction.
     */
    void shouldLockSwiping(MotionEvent event, float motionOriginX, float motionOriginY) {
        if (swipeToRevertEnabled && !willSwipeToRevert) {

            if (isMovingInSwipeTORevertDirection(event, motionOriginX, motionOriginY)) {
                isSwipingToRevert = true;
            }
            willSwipeToRevert = true;
        }
    }

    /**
     * Determines if the user movement is toward selected {@link SwipeToRevert} direction.
     */
    private boolean isMovingInSwipeTORevertDirection(MotionEvent event, float motionOriginX, float motionOriginY) {
        switch (swipeToRevertDirection) {
            case SwipeToRevert.BOTTOM_TO_TOP:
                return motionOriginY > event.getRawY();
            case SwipeToRevert.TOP_TO_BOTTOM:
                return motionOriginY < event.getRawY();
            case SwipeToRevert.RIGHT_TO_LEFT:
                return motionOriginX > event.getRawX();
            case SwipeToRevert.LEFT_TO_RIGHT:
                return motionOriginX < event.getRawX();
            default:
                return false;
        }
    }

    /**
     * Updates current card translation, forbidding to be translated into settled {@link SwipeToRevert} direction.
     */
    void updateCardTranslation(MotionEvent event, final CardContainerView cardToTranslate,
                               float viewOriginX, float viewOriginY, float motionOriginX, float motionOriginY) {
        switch (swipeToRevertDirection) {
            case SwipeToRevert.TOP_TO_BOTTOM:
                if (motionOriginY > event.getRawY()) {
                    ViewCompat.setTranslationY(cardToTranslate, viewOriginY + event.getRawY() - motionOriginY);
                }
                ViewCompat.setTranslationX(cardToTranslate, viewOriginX + event.getRawX() - motionOriginX);
                break;
            case SwipeToRevert.BOTTOM_TO_TOP:
                if (motionOriginY < event.getRawY()) {
                    ViewCompat.setTranslationY(cardToTranslate, viewOriginY + event.getRawY() - motionOriginY);
                }
                ViewCompat.setTranslationX(cardToTranslate, viewOriginX + event.getRawX() - motionOriginX);
                break;
            case SwipeToRevert.RIGHT_TO_LEFT:
                if (motionOriginX < event.getRawX()) {
                    ViewCompat.setTranslationX(cardToTranslate, viewOriginX + event.getRawX() - motionOriginX);
                }
                ViewCompat.setTranslationY(cardToTranslate, viewOriginY + event.getRawY() - motionOriginY);
                break;
            case SwipeToRevert.LEFT_TO_RIGHT:
                if (motionOriginX > event.getRawX()) {
                    ViewCompat.setTranslationX(cardToTranslate, viewOriginX + event.getRawX() - motionOriginX);
                }
                ViewCompat.setTranslationY(cardToTranslate, viewOriginY + event.getRawY() - motionOriginY);
                break;
        }
    }

    /**
     * Verifies if the actual {@link SwipeDirection} matches the settled {@link SwipeToRevert} direction.
     */
    private boolean wasSwipeToRevert(SwipeDirection currentSwipeDirection) {
        switch (currentSwipeDirection) {
            case Top:
                return swipeToRevertDirection == SwipeToRevert.BOTTOM_TO_TOP;
            case Bottom:
                return swipeToRevertDirection == SwipeToRevert.TOP_TO_BOTTOM;
            case Right:
                return swipeToRevertDirection == SwipeToRevert.LEFT_TO_RIGHT;
            case Left:
                return swipeToRevertDirection == SwipeToRevert.RIGHT_TO_LEFT;
            default:
                return false;
        }
    }
}
