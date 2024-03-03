    package controllers;

    import java.io.*;
    import java.net.HttpURLConnection;
    import java.net.URL;

    public class ChatGPTAPIExample {
        private static long lastRequestTime = 0;
        private static final long MINIMUM_TIME_INTERVAL_MS = 1000; // Minimum time interval between requests (in milliseconds)

        public static String chatGPT(String prompt) {
            long currentTime = System.currentTimeMillis();
            long timeSinceLastRequest = currentTime - lastRequestTime;

            if (timeSinceLastRequest < MINIMUM_TIME_INTERVAL_MS) {
                try {
                    Thread.sleep(MINIMUM_TIME_INTERVAL_MS - timeSinceLastRequest);
                } catch (InterruptedException e) {
                    // Handle interruption
                }
            }

            lastRequestTime = System.currentTimeMillis();

            String url = "https://api.openai.com/v1/chat/completions";
            String apiKey = "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";
            String model = "gpt-3.5-turbo";

            try {
                URL obj = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Authorization", "Bearer " + apiKey);
                connection.setRequestProperty("Content-Type", "application/json");

                // The request body
                String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
                connection.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(body);
                writer.flush();
                writer.close();

                // Response from ChatGPT
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();

                // calls the method to extract the message.
                return extractMessageFromJSONResponse(response.toString());

            } catch (IOException e) {
                throw new RuntimeException("Error connecting to the API: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                throw new RuntimeException("Error extracting message from JSON response: " + e.getMessage());
            }
        }

        public static String extractMessageFromJSONResponse(String response) {
            try {
                int start = response.indexOf("content") + 11;
                int end = response.indexOf("\"", start);
                return response.substring(start, end);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                throw new RuntimeException("Error extracting message from JSON response: " + e.getMessage());
            }
        }
    }
