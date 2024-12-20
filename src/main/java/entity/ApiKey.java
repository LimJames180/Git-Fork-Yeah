package entity;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Returns some random api key (API key rotator).
 */

public class ApiKey {
    private static List<String> apiKeys = Arrays.asList(
            //"181e6ad964a84b0ebbdcd87b87098c0c"//,
            //"00910f2367a744149057dcddacb9f37f",
            //,"a3181a44255f4cd39e16cb8b37e3f115"//,
//            "62fb1e66d4be4351b17b5f5043ede6db"
            "8c539f487cf6406b908cb2c2a1078285"
            /*
            PUT API KEYS HERE
             */

    );
    private static Random rand = new Random();

    /**
     * Returns some random api key (API key rotator).
     */
    public static String getApiKeys() {
        return apiKeys.get(rand.nextInt(apiKeys.size()));
    }
}

