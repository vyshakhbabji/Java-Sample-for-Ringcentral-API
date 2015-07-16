package call_Log_Logger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import java.util.Date;

import javax.net.ssl.HttpsURLConnection;


public class CopyOfCallLog {
	public  void callLog(String accessToken) throws Exception {

		String url = "https://platform.ringcentral.com/restapi/v1.0/account/~/phone-number/";
		
		//
		HttpsURLConnection httpConn = null;
		BufferedReader in = null;

		String output =  "";
		try {

			StringBuilder data = new StringBuilder();
			byte[] byteArray = data.toString().getBytes("UTF-8");

			URL request = new URL(url);
			httpConn = (HttpsURLConnection) request.openConnection();
			httpConn.setRequestMethod("GET");
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
			Date date = new Date();
			output = "--------------"+date+"-------------------\n";
			output = output+ json;
			//System.out.println("Call-log : "+ json);

		} catch (java.io.IOException e) {
			output = output+ e.getMessage();
			//System.out.println(e.getMessage());

		} finally {
			if (in != null)
				in.close();
			if (httpConn != null)
				httpConn.disconnect();
		}

		output=output+"\n --------------------------------------------\n\n\n";

		//writing to file
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/Users/vyshakh.babji/Logger_folder/log.txt", true)));
			out.println(output);
			out.close();
		} catch (IOException e) {
			//exception handling left as an exercise for the reader
		}


	}
}
