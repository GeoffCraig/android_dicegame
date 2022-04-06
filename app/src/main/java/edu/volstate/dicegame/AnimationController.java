package edu.volstate.dicegame;

import android.app.Activity;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AnimationController implements Animation.AnimationListener{
    Context context;
    Activity activity;

    public AnimationController(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void bounceFloatingButton(int viewObject, int duration) {
        Animation animBounce = AnimationUtils.loadAnimation(context, R.anim.bounce);
        animBounce.setDuration(duration);
        FloatingActionButton mFAB = activity.findViewById(viewObject);
        mFAB.startAnimation(animBounce);
    }

    public void bounceImage(int viewObject, int duration) {
        Animation animBounce = AnimationUtils.loadAnimation(context, R.anim.bounce);
        animBounce.setDuration(duration);
        ImageView imgV = activity.findViewById(viewObject);
        imgV.startAnimation(animBounce);
    }

    public void bounceEditText(int viewObject, int duration) {
        Animation animBounce = AnimationUtils.loadAnimation(context, R.anim.bounce);
        animBounce.setDuration(duration);
        EditText editText = activity.findViewById(viewObject);
        editText.startAnimation(animBounce);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
