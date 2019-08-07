package com.grs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import io.dcloud.PandoraEntry;
import io.dcloud.PandoraEntryActivity;
import io.dcloud.WebAppActivity;


/**
 * @author:gaoruishan
 * @date:2019/3/12/16:13
 * @email:grs0515@163.com
 */
public class WelcomeActivity extends PandoraEntry {
    private static final String TAG = "WelcomeActivity";

    @SuppressLint({"ResourceType", "MissingSuperCall"})
    @Override
    protected void onCreate(Bundle bundle) {

//        ImageView imageView = new ImageView(this);
//        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        int img = getResources().getIdentifier(com.grs.oldsland.BuildConfig.SPLASH, "drawable", com.grs.oldsland.BuildConfig.APPLICATION_ID);
//        imageView.setImageResource(img);
//        setContentView(imageView);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                superOnCreate();
//                WelcomeActivity.this.finish();
//            }
//        }, 3000);

    }

    private void superOnCreate() {
        Intent var2 = this.getIntent();
        boolean var3 = false;

        try {
            var3 = var2.getBooleanExtra("is_stream_app", var3);
        } catch (Exception var5) {
            var5.printStackTrace();
            this.finish();
            return;
        }
        Log.e(TAG,"(WelcomeActivity.java:57) "+var3);
        if (var3) {
            var2.setClass(this, WebAppActivity.class);
            var2.putExtra("is_stream_app", true);
        } else {
            var2.putExtra("short_cut_class_name", PandoraEntry.class.getName());
            var2.setClass(this, PandoraEntryActivity.class);
        }

        this.startActivity(var2);
    }
}
