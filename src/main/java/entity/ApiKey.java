package entity;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Returns some random api key (API key rotator).
 */

public class ApiKey {
    private static List<String> apiKeys = Arrays.asList(
//            "a3181a44255f4cd39e16cb8b37e3f115",
//            "62fb1e66d4be4351b17b5f5043ede6db"
            "e049a1035ff54ac1855cf584cbe48f2f"
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

