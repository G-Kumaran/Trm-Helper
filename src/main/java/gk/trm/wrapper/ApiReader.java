package gk.trm.wrapper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ApiReader
{
	static String get(final String apiIp, final int apiPort, final String command)
	{
		log.debug("Connecting to {}:{} - {}", apiIp, apiPort, command);

		try
		{
			final String request = "{\"command\":\"" + command + "\"}";
			final byte[] stringBytes = (request + "\n").getBytes("UTF-8");

			@Cleanup
			final Socket s = new Socket();
			s.connect(new InetSocketAddress(apiIp, apiPort), 1000);
			log.debug("Connection to {}:{} - {} : {}", apiIp, apiPort, request, s.isConnected());

			@Cleanup
			final DataOutputStream APIConnection = new DataOutputStream(s.getOutputStream());
			APIConnection.write(stringBytes, 0, stringBytes.length);
			APIConnection.flush();

			@Cleanup
			final BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
			final char[] a = new char[1500];
			reader.read(a);

			final String response = String  .copyValueOf(a)
											.trim();

			log.debug("Receive : {}", response);
			return response;

		} catch (Exception e)
		{
			log.error("Error : ", e);
			return "{\"message\":\"Error getting API at " + apiIp + ":" + apiPort + "/" + command + ". Reason - " + e.getLocalizedMessage() + "\"}";
		}
	}
}
