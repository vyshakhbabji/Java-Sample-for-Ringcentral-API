import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.lang.reflect.Type;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Program {

	public static void main(final String[] args) throws Exception {
		// Provide your crentials in the variables below
		String apikey = "XXXXX";
		String apisecret = "XXXXX";
		String username = "XXXXX";
		String extension = "XXX";
		String password = "XXXXX";

		String grantType = "password";
		String url = "https://platform.devtest.ringcentral.com/restapi/oauth/token";
		HttpsURLConnection httpConn = null;
		BufferedReader in = null;

		try {

			StringBuilder data = new StringBuilder();
			data.append("grant_type=" + URLEncoder.encode(grantType, "UTF-8"));

			data.append("&username=" + URLEncoder.encode(username, "UTF-8"));
			//data.append("&extension=" + URLEncoder.encode(extension, "UTF-8"));
			data.append("&password=" + URLEncoder.encode(password, "UTF-8"));
		        

			byte[] byteArray = data.toString().getBytes("UTF-8");

			URL request = new URL(url);
			httpConn = (HttpsURLConnection) request.openConnection();
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			httpConn.setRequestProperty(
					"Authorization",
					"Basic <base64 converted apikey and secret>");
			httpConn.setDoOutput(true);

			OutputStream postStream = httpConn.getOutputStream();
			postStream.write(byteArray, 0, byteArray.length);
			postStream.close();

			InputStreamReader reader = new InputStreamReader(
					httpConn.getInputStream());
			in = new BufferedReader(reader);

			StringBuffer content = new StringBuffer();
			String line;
			while ((line = in.readLine()) != null) {
				content.append(line + "\n");
			}
			in.close();

			String json = content.toString();
			System.out.println("Json String = " + json);

			Gson gson = new Gson();
			Type mapType = new TypeToken<Map<String, String>>() {
			}.getType();
			Map<String, String> ser = gson.fromJson(json, mapType);
			String accessToken = ser.get("access_token");
			System.out.println("Access Token = " + accessToken);

		} catch (java.io.IOException e) {

			System.out.println(e.getMessage());

		} finally {

			if (in != null)
				in.close();
			if (httpConn != null)
				httpConn.disconnect();
		}
	}
}
