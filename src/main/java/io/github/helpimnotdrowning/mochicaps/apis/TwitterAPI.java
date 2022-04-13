package io.github.helpimnotdrowning.mochicaps.apis;

import io.github.helpimnotdrowning.mochicaps.IBetterRepresentation;
import io.github.helpimnotdrowning.mochicaps.Log;
import io.github.helpimnotdrowning.mochicaps.credentials.Credentials;
import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.dto.tweet.TweetParameters;
import io.github.redouane59.twitter.signature.TwitterCredentials;

import java.nio.file.Path;
import java.util.Arrays;

import static io.github.helpimnotdrowning.mochicaps.Utils.safeShutdown;
import static io.github.helpimnotdrowning.mochicaps.credentials.CredentialFormat.TWITTER;

public class TwitterAPI implements IBetterRepresentation, ISocialMediaAPI {
    private static final Log LOGGER = new Log(TwitterAPI.class);
    private TwitterClient API;

    /**
     * Authenticate to the Twitter API.
     * @param account Credentials in TWITTER format.
     */
    public TwitterAPI(Credentials account) {
        if (account.getFormat() == TWITTER) {
            this.API = new TwitterClient(TwitterCredentials.builder()
                    .apiKey(account.getConsumerKey())
                    .apiSecretKey(account.getConsumerKeySecret())
                    .accessToken(account.getAccessToken())
                    .accessTokenSecret(account.getAccessTokenSecret())
                    .build());
        } else {
            LOGGER.error(String.format("Could not login to Twitter: Credentials are in %s format!", account.getFormat()));
            safeShutdown(1);
        }
    }

    /**
     * Upload up to 4 media (images/video/GIF). There is no GIF/video detection yet, so please only add one or it will
     * error out (probably)
     * @param mediaPaths Paths of media to post
     */
    private String[] uploadMedia(Path... mediaPaths) {
        String[] mediaIDs = new String[4];

        if(mediaPaths.length > 4) {
            throw new IllegalArgumentException("A tweet cannot more than 4 media (pictures) attached to a tweet.");
        }

        for (int i=0; i < mediaPaths.length; i++) {
            mediaIDs[i] = API.uploadMedia(mediaPaths[i].toFile(), null).getMediaId();
        }

        return mediaIDs;
    }

    @Override
    public void createPost(Path... mediaPaths) {
        createPost(null, mediaPaths);
    }

    @Override
    public void createPost(String message) {
        createPost(message, (Path)null);

    }

    @Override
    public void createPost(String message, Path... mediaPaths) {
        TweetParameters.Media media;
        TweetParameters tweet;

        // make sure we dont have a null tweet, which would probably break something
        if (message == null && mediaPaths == null) {
            throw new IllegalArgumentException("Both the message and media can't be null.");
        }

        if (mediaPaths != null) {
            media = TweetParameters.Media.builder()
                    .mediaIds(Arrays.asList(uploadMedia(mediaPaths)))
                    .build();
            // if there is both a message and media
            if (message != null) {
                if (message.length() > 280) {
                    throw new IllegalArgumentException("Message \" + message + \"is longer than Twitter length limit.");
                }
                tweet = TweetParameters.builder()
                        .text(message)
                        .media(media)
                        .build();
            // if there is media but no message
            } else {
                tweet = TweetParameters.builder()
                        .media(media)
                        .build();
            }
        // if there is a message but no media
        } else {
            tweet = TweetParameters.builder()
                    .text(message)
                    .build();
        }

        API.postTweet(tweet);
    }

    @Override
    public void changeBio(String bio) {
        //User.setDescription("d");
        //throw new NotImplementedException("Setting the user bio is not available yet in twittered!");
        LOGGER.error("Setting the user bio is not available yet within the twittered library!");
        safeShutdown(1);
    }

    @Override
    public void sendMessageToOwner(String message) {
        //TODO: load owner id from config
        API.postDm(message, "0");
    }

    @Override
    public String friendlyString() {
        return "TwitterAPI[user=" + API.getUserIdFromAccessToken() + "]";
    }

    @Override
    public String infoString() {
        return friendlyString();
    }

    @Override
    public String debugString() {
        return infoString();
    }
}
