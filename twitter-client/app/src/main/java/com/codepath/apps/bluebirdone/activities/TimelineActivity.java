package com.codepath.apps.bluebirdone.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.codepath.apps.bluebirdone.R;
import com.codepath.apps.bluebirdone.TwitterClient;
import com.codepath.apps.bluebirdone.adapters.TweetAdapter;
import com.codepath.apps.bluebirdone.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends BaseBlueBirdOneActivity {

    @BindView(R.id.tweets_recycler_view)
    RecyclerView tweetsRecyclerView;

    List<Tweet> tweets = new ArrayList<>();
    TweetAdapter tweetAdapter = new TweetAdapter(tweets);

    @Inject
    TwitterClient twitterClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        getNetComponent().inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        ButterKnife.bind(this);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        tweetsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tweetsRecyclerView.setAdapter(tweetAdapter);


//        TwitterClient client = BlueBirdOneApplication.getRestClient();
//        client.
        twitterClient.getHomeTimeline(1, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
                Log.d("DEBUG", "timeline: " + jsonArray.toString());
                // Load json array into model classes
                tweets.clear();
                tweets.addAll(Tweet.fromJson(jsonArray));
                tweetAdapter.notifyDataSetChanged();

            }
        });
    }

}
