package com.codepath.apps.restclienttemplate.activities;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.codepath.apps.restclienttemplate.RestApplication;
import com.codepath.apps.restclienttemplate.dagger.NetComponent;

import butterknife.ButterKnife;

/**
 * Basic abstract activity class to contain the most common methods.
 */
public abstract class BaseBlueBirdOneActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    protected NetComponent getNetComponent() {
        return ((RestApplication) getApplication()).getNetComponent();
    }
}