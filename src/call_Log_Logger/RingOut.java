package call_Log_Logger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class RingOut {
	public void deleteRingOut (String accessToken, String ringOutId) throws Exception {

		String url = "https://platform.devtest.ringcentral.com/restapi/v1.0/account/~/extension/~/ringout/"+ringOutId;
		HttpsURLConnection httpConn = null;
		BufferedReader in = null;

		try {

			URL request = new URL(url);
			httpConn = (HttpsURLConnection) request.openConnection();
			httpConn.setRequestMethod("DELETE");
			httpConn.setRequestProperty("Accept", "application/json");
			httpConn.setRequestProperty("Content-Type",
					"application/json; charset=UTF-8");
			httpConn.setRequestProperty(
					"Authorization",
					"Bearer "+accessToken);

			httpConn.setDoOutput(true);

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
			System.out.println("Json String Delete = " + json);

		} catch (java.io.IOException e) {

			System.out.println(e.getMessage());

		} finally {

			if (in != null)
				in.close();
			if (httpConn != null)
				httpConn.disconnect();
		}

		System.out.println("Delete Success!!!");
	}

	public String ringOut (String accessToken) throws Exception {

		String url = "https://platform.devtest.ringcentral.com/restapi/v1.0/account/~/extension/~/ringout";
		HttpsURLConnection httpConn = null;
		BufferedReader in = null;
		String payload = "{\r\n  \"to\": {\"phoneNumber\": \"16505154891\"},\r\n  \"from\": {\"phoneNumber\": \"16197619503\"},\r\n  \"callerId\": {\"phoneNumber\": \"15856234138\"},\r\n  \"playPrompt\": false\r\n}";

		String ringOutId="";
		try {

			URL request = new URL(url);
			httpConn = (HttpsURLConnection) request.openConnection();
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("Accept", "application/json");
			httpConn.setRequestProperty("Content-Type",
					"application/json; charset=UTF-8");
			httpConn.setRequestProperty(
					"Authorization",
					"Bearer "+accessToken);

			httpConn.setDoOutput(true);

			OutputStream postStream = httpConn.getOutputStream();
			postStream.write(payload.getBytes("UTF-8"), 0,
					payload.getBytes().length);
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
			String[] s = json.split(",");
			s = s[1].split(":");
			ringOutId=s[1].replace(" ", "");

		} catch (java.io.IOException e) {
			System.out.println(e.getMessage());
		} finally { 
			if (in != null)
				in.close();
			if (httpConn != null)
				httpConn.disconnect();
		}
		return ringOutId;
	}


}
