package io.github.helpimnotdrowning.mochicaps.apis;

import java.nio.file.Path;
import java.util.HashMap;

/**
 * Interface to make adding support for posting to more social medias easier
 */
public interface ISocialMediaAPI {
    /**
     * Login to bot account
     * @param keys A {@code HashMap} that holds the keys/tokens/whatevers to the social media API
     */
    public void authenticate(HashMap<String, String> keys);

    /**
     * Create a media post containing pictures and/or videos. Media limit is dependent on the social media.
     * @param mediaPaths Paths of media to post
     */
    public void createPost(Path... mediaPaths);

    /**
     * Create a text post. Character limit is dependent on the social media.
     * @param message Message to post
     */
    public void createPost(String message);

    /**
     * Create a post containing text and some amount of pictures/videos. Character limit of the message and media limit
     * is dependent on the social media.
     * @param message Message to post
     * @param mediaPaths Paths of media to post
     */
    public void createPost(String message, Path... mediaPaths);

    /**
     * Change bio of bot account.
     * @param bio String to set bio to
     */
    public void changeBio(String bio);

    /**
     * Send a message to the bot owner (that's you!)
     * @param message Message to send
     */
    public void sendMessageToOwner(String message);
}