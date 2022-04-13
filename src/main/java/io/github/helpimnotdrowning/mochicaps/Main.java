package io.github.helpimnotdrowning.mochicaps;

import io.github.helpimnotdrowning.mochicaps.credentials.Credentials;

import java.util.Map;

import static io.github.helpimnotdrowning.mochicaps.credentials.CredentialFormat.TWITTER;

public class Main {
    private static final Log LOGGER = new Log(Main.class);

    public static void main(String[] args) {
        LOGGER.print("Hello World!");
        LOGGER.debug("Among");
        LOGGER.info("Us");
        LOGGER.warn("In Real Life");
        LOGGER.error("Test");

        Map<String, String> credM = Map.of(
                "accessToken",
                "AT_aaaaaa",
                "accessTokenSecret",
                "ATS_AAAAAAA",
                "consumerKey",
                "CK_AAAAAaaaaaaAAAAAAAA",
                "consumerKeySecret",
                "CKS_aaaAaAAAAaa"
        );

        Credentials t = new Credentials(TWITTER, credM);
        LOGGER.print(t.debugString());
        LOGGER.print(new EasyTime(8025).debugString());
        EasyTime time = new EasyTime(1,2,3,4);
        LOGGER.print(time.debugString());
    }
}