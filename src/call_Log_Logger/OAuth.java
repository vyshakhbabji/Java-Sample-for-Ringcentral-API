package call_Log_Logger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/*
Please fill in your app key , app secret , username , password
*/
public class OAuth {
	public static String grantType = "password";
	public static String username = "";
	public static String password = "";
	public static String url = "https://platform.devtest.ringcentral.com/restapi/oauth/token";
	public static String key ="";
	public static String secret = "";
	

	public String OAuthorizer() throws Exception {

		String accessToken="";
		String keySec= key +":"+ secret;
		byte[] message = keySec.getBytes();
		String encoded = DatatypeConverter.printBase64Binary(message);
		//System.out.println("encoded value is " + new String(encoded ));
		HttpsURLConnection httpConn = null;
		BufferedReader in = null;

		try {
			StringBuilder data = new StringBuilder();
			data.append("grant_type=" + URLEncoder.encode(grantType, "UTF-8"));
			data.append("&username=" + URLEncoder.encode(username, "UTF-8"));
			data.append("&password=" + URLEncoder.encode(password, "UTF-8"));
			byte[] byteArray = data.toString().getBytes("UTF-8");
			URL request = new URL(url);
			httpConn = (HttpsURLConnection) request.openConnection();
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			httpConn.setRequestProperty(
					"Authorization",
					"Basic "+encoded);
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
			//System.out.println("Json String = " + json);
			Gson gson = new Gson();
			Type mapType = new TypeToken<Map<String, String>>() {
			}.getType();
			Map<String, String> ser = gson.fromJson(json, mapType);
			accessToken = ser.get("access_token");

			//System.out.println(ser.get("expires_in"));
			//String refreshToken= ser.get("refresh_token");
			//System.out.println("Access Token = " + accessToken);
			//System.out.println("Refresh Token = " + refreshToken);

		} catch (java.io.IOException e) {

			System.out.println(e.getMessage());

		} finally {

			if (in != null)
				in.close();
			if (httpConn != null)
				httpConn.disconnect();

		}
		return accessToken;

	}
}
