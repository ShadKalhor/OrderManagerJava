package Controllers;

import Entities.UserAddress;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.UUID;

public class AddressController {
    private static final String BASE_URL = "http://localhost:8081/address";

    private final Gson gson = new Gson();

    public String createAddress(UserAddress address) throws IOException {
        URL url = new URL(BASE_URL + "/create");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            os.write(gson.toJson(address).getBytes());
            os.flush();
        }

        return readResponse(connection);
    }

    public UserAddress getAddressById(UUID id) throws IOException {
        URL url = new URL(BASE_URL + "/get?id=" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        String response = readResponse(connection);
        return gson.fromJson(response, UserAddress.class);
    }

    public List<UserAddress> listAddressesByUserId(UUID userId) throws IOException {
        URL url = new URL(BASE_URL + "/list?userId=" + userId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        String response = readResponse(connection);
        return gson.fromJson(response, List.class);
    }

    public String updateAddress(UserAddress address) throws IOException {
        URL url = new URL(BASE_URL + "/update");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            os.write(gson.toJson(address).getBytes());
            os.flush();
        }

        return readResponse(connection);
    }

    public String deleteAddress(UUID id) throws IOException {
        URL url = new URL(BASE_URL + "/delete?id=" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");

        return readResponse(connection);
    }

    private String readResponse(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        InputStream inputStream = (responseCode >= 200 && responseCode < 300)
                ? connection.getInputStream()
                : connection.getErrorStream();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return response.toString();
        } finally {
            connection.disconnect();
        }
    }
}
