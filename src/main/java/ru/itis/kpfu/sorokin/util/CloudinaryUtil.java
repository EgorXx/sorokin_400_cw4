package ru.itis.kpfu.sorokin.util;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryUtil {

    private static Cloudinary cloudinary;

    public static Cloudinary getInstance() {
        if (cloudinary == null) {
            String cloudName = System.getenv("CLOUD_NAME");
            String apiKey = System.getenv("API_KEY");
            String apiSecret = System.getenv("API_SECRET");


            Map<String, String> config = new HashMap<>();
            config.put("cloud_name", cloudName);
            config.put("api_key", apiKey);
            config.put("api_secret", apiSecret);


            cloudinary = new Cloudinary(config);
        }

        return cloudinary;
    }

}
