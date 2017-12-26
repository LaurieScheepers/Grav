package com.github.glomadrian.grav.generator.animation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;

import com.github.glomadrian.grav.figures.Grav;

public abstract class GravAnimatorGenerator<T extends Grav> {

    public ValueAnimator generateGravAnimator(T grav, int width, int height) {
        ValueAnimator valueAnimator = createValueAnimator(grav, width, height);
        UpdateGravListener<T> updateGravListenerListener = createUpdateListener();
        valueAnimator.addUpdateListener(new AnimatorUpdateListener(grav, updateGravListenerListener));
        return valueAnimator;
    }

    protected abstract ValueAnimator createValueAnimator(T grav, int width, int height);

    protected abstract UpdateGravListener<T> createUpdateListener();

    public abstract void configure(AttributeSet attributeSet, Context context);

    public interface UpdateGravListener<T> {
        void onUpdate(T grav, ValueAnimator animator);
    }

    private class AnimatorUpdateListener implements ValueAnimator.AnimatorUpdateListener {
        private final T grav;
        private final UpdateGravListener UpdateGravListener;

        private AnimatorUpdateListener(T grav, UpdateGravListener UpdateGravListener) {
            this.grav = grav;
            this.UpdateGravListener = UpdateGravListener;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            UpdateGravListener.onUpdate(grav, animation);
        }
    }
}
