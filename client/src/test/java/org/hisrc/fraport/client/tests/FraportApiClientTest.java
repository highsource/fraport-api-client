package org.hisrc.fraport.client.tests;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;

import org.hisrc.fraport.client.DefaultFraportApiClient;
import org.hisrc.fraport.client.FraportApiClient;
import org.hisrc.fraport.client.invoker.ApiException;
import org.hisrc.fraport.client.model.Gate;
import org.hisrc.fraport.client.model.ProcessSite;
import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FraportApiClientTest {

	private static final String ACCESS_TOKEN_PROPERTY_KEY = "accessToken";

	private static final String FRAPORT_API_PROPERTIES_RESOURCE_NAME = "fraport-api.properties";

	private FraportApiClient sut;

	@Before
	public void createDefaultApi() throws IOException {
		final Properties properties = new Properties();
		final InputStream is = getClass().getClassLoader()
				.getResourceAsStream(FRAPORT_API_PROPERTIES_RESOURCE_NAME);
		Assert.assertNotNull(MessageFormat.format(
				"Could not find the [{0}] resource. For tests, please create src/test/resources/{0} with accessToken=<accessToken> property.",
				FRAPORT_API_PROPERTIES_RESOURCE_NAME));
		properties.load(is);
		final String accessToken = properties.getProperty(ACCESS_TOKEN_PROPERTY_KEY);
		sut = new DefaultFraportApiClient(accessToken);
	}

	@Test
	public void returnsGates() throws ApiException {
		List<Gate> gates = sut.gates();
		Assert.assertNotNull(gates);
		Assert.assertTrue(gates.size() > 1);
	}

	@Test
	public void returnsGate() throws ApiException {
		Gate gate = sut.gate("A11");
		Assert.assertEquals("A11", gate.getName());
	}
	
	@Test
	public void returnsProcessSites() throws ApiException {
		List<ProcessSite> processSites = sut.processSites();
		Assert.assertNotNull(processSites);
		Assert.assertTrue(processSites.size() > 1);
	}
	@Test
	public void returnsProcessSite() throws ApiException {
		ProcessSite processSite = sut.processSite("Central Security-Check A");
		Assert.assertEquals("Central Security-Check A", processSite.getName());
	}
}