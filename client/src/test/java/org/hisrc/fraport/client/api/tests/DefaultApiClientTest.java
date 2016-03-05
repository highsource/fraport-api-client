package org.hisrc.fraport.client.api.tests;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import org.hisrc.fraport.client.api.DefaultApi;
import org.hisrc.fraport.client.invoker.ApiException;
import org.hisrc.fraport.client.invoker.CustomizedApiClient;
import org.hisrc.fraport.client.model.GatesResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DefaultApiClientTest {

	private static final String AUTH_KEY_PROPERTY_KEY = "accessToken";

	private static final String FRAPORT_API_PROPERTIES_RESOURCE_NAME = "fraport-api.properties";

	private DefaultApi sut;

	@Before
	public void createDefaultApi() throws IOException {
		final Properties properties = new Properties();
		final InputStream is = getClass().getClassLoader()
				.getResourceAsStream(FRAPORT_API_PROPERTIES_RESOURCE_NAME);
		Assert.assertNotNull(MessageFormat.format(
				"Could not find the [{0}] resource. For tests, please create src/test/resources/{0} with authKey=<authKey> property.",
				FRAPORT_API_PROPERTIES_RESOURCE_NAME));
		properties.load(is);
		final String authKey = properties.getProperty(AUTH_KEY_PROPERTY_KEY);
		sut = new DefaultApi(new CustomizedApiClient());
		sut.getApiClient().setApiKeyPrefix("Bearer");
		sut.getApiClient().setApiKey(authKey);
	}

	@Test
	public void returnsLocationResponse() throws ApiException {
		GatesResponse gatesGet = sut.gates10GatesGet();
		Assert.assertNotNull(gatesGet);
	}

}