import java.io.*;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class AccountInformation {

	public static void main(final String[] args) throws Exception {

		String url = "https://platform.devtest.ringcentral.com/restapi/v1.0/account/~/";
		HttpsURLConnection httpConn = null;
		BufferedReader in = null;

		try {

			StringBuilder data = new StringBuilder();
			byte[] byteArray = data.toString().getBytes("UTF-8");

			URL request = new URL(url);
			httpConn = (HttpsURLConnection) request.openConnection();
			httpConn.setRequestMethod("GET");
			httpConn.setRequestProperty(
					"Authorization",
					"Bearer <access_token>");
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
