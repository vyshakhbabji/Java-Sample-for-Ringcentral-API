import java.io.*;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class SendSMS {

	public static void main(final String[] args) throws Exception {

		String url = "https://platform.devtest.ringcentral.com/restapi/v1.0/account/~/extension/~/sms";
		HttpsURLConnection httpConn = null;
		BufferedReader in = null;
		String payload = "{\n  \"to\": [{\"phoneNumber\": \"16197619503\"}],\n  \"from\": {\"phoneNumber\": \"17322764490\"},\n  \"text\": \"Test SMS message from Platform server\"\n}";

		try {

			URL request = new URL(url);
			httpConn = (HttpsURLConnection) request.openConnection();
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("Accept", "application/json");
			httpConn.setRequestProperty("Content-Type",
					"application/json; charset=UTF-8");
			httpConn.setRequestProperty(
					"Authorization",
					"Bearer <Access_token>");

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
			System.out.println("Json String = " + json);

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