package net.helpimnotdrowning.mochicaps;

import twitter4j.Twitter;
import twitter4j.v1.MediaEntity;

import java.nio.file.Path;

public class TwitterAPI {
    Twitter api;

    public TwitterAPI(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
        //Instantiate a re-usable and thread-safe factory
        api = Twitter.newBuilder()
                .oAuthConsumer(consumerKey, consumerSecret)
                .oAuthAccessToken(accessToken, accessTokenSecret)
                .build();

    }

    public void tweet(String message, MediaEntity[] mediaEntities) {

    }

    public void tweet(String message) {
        api.v1().
    }

    public void tweet(MediaEntity[] mediaEntities) {

    }

    public MediaEntity uploadMedia(Path file) {

    }
}
