package io.github.helpimnotdrowning.mochicaps.credentials;

import io.github.helpimnotdrowning.mochicaps.IBetterRepresentation;
import io.github.helpimnotdrowning.mochicaps.Log;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;

import java.util.Map;

import static io.github.helpimnotdrowning.mochicaps.Utils.censor;

public class Credentials implements IBetterRepresentation {
    private static final Log LOGGER = new Log(Credentials.class);

    private CredentialFormat format;
    private Map<String, String> keyring;

    private String consumerKey;
    private String consumerKeySecret;
    private String accessToken;
    private String accessTokenSecret;
    // more later...


    public Credentials(CredentialFormat format, Map<String, String> keys) {
        setFormat(format);
        setKeys(keys);
    }

    public void setFormat(CredentialFormat format) {
        this.format = format;

        if (keyring != null) {
            keyBuilder();
        }
    }

    public CredentialFormat getFormat() {
        return format;
    }

    public void setKeys(Map<String, String> keys) {
        keyring = keys;

        if (format != null) {
            keyBuilder();
        }
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public String getConsumerKeySecret() {
        return consumerKeySecret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }

    private void keyBuilder() {
        try {
            switch (getFormat()) {
                case TWITTER:
                    consumerKey = keyring.get("consumerKey");
                    consumerKeySecret = keyring.get("consumerKeySecret");
                    accessToken = keyring.get("accessToken");
                    accessTokenSecret = keyring.get("accessTokenSecret");
                    break;
                default:
                    throw new NotImplementedException(String.format(
                            "The account format \"%s\" isn't supported yet.", format.toString()
                    ));
            }
        // this looks and feels very wrong but idk how to do it better
        } catch (NotImplementedException e) {
            LOGGER.error(String.format("The account format \"%s\" isn't supported yet.", format.toString()));
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public String friendlyString() {
        switch (getFormat()) {
            case TWITTER:
                return String.format("Credentials[%s keys in \"%s\" format]", keyring.size(), format.toString());
            default:
                return "AAAA";
        }

    }

    @Override
    public String infoString() {
        switch (getFormat()) {
            case TWITTER:
                return String.format(
                        "Credentials[format = %s, consumerKey = %s, consumerKeySecret = %s, accessToken = %s, accessTokenSecret = %s]",
                        format,
                        censor(getConsumerKey()),
                        censor(getConsumerKeySecret()),
                        censor(getAccessToken()),
                        censor(getAccessTokenSecret())
                );
            default:
                return String.format("Credentials[format = %s <- UNSUPPORTED FORMAT!!]", format);
        }
    }

    @Override
    public String debugString() {
        return IBetterRepresentation.super.debugString();
    }
}
