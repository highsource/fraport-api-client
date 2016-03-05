package org.hisrc.fraport.client;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.hisrc.fraport.client.api.DefaultApi;
import org.hisrc.fraport.client.invoker.ApiException;
import org.hisrc.fraport.client.invoker.CustomizedApiClient;
import org.hisrc.fraport.client.model.Gate;
import org.hisrc.fraport.client.model.GatesResponse;
import org.hisrc.fraport.client.model.ProcessSite;
import org.hisrc.fraport.client.model.WaitingperiodResponse;

public class DefaultFraportApiClient implements FraportApiClient {

	public final String API_KEY_PREFIX = "Bearer";

	private final DefaultApi api;

	public DefaultFraportApiClient(String accessToken) {
		Objects.requireNonNull(accessToken);
		this.api = new DefaultApi(new CustomizedApiClient());
		this.api.getApiClient().setApiKey(accessToken);
		this.api.getApiClient().setApiKeyPrefix(API_KEY_PREFIX);

	}

	@Override
	public Gate gate(String name) {
		try {
			GatesResponse response = api.gates10GatesGatenameGet(name);
			if (response != null && !response.isEmpty()) {
				return response.get(0).getGate();
			} else {
				return null;
			}
		} catch (ApiException apiex) {
			throw new FraportApiException("Error executing the API operation.", apiex);
		}
	}

	@Override
	public List<Gate> gates() {
		try {
			GatesResponse response = api.gates10GatesGet();
			if (response != null && !response.isEmpty()) {
				return response.stream().filter(Objects::nonNull).map(r -> r.getGate()).filter(Objects::nonNull)
						.collect(Collectors.toList());
			} else {
				return null;
			}
		} catch (ApiException apiex) {
			throw new FraportApiException("Error executing the API operation.", apiex);
		}
	}

	@Override
	public ProcessSite processSite(String name) {
		try {
			WaitingperiodResponse response = api.waitingperiods10WaitingperiodProcesssiteGet(name);
			if (response != null && !response.isEmpty()) {
				return response.get(0).getProcessSite();
			} else {
				return null;
			}
		} catch (ApiException apiex) {
			throw new FraportApiException("Error executing the API operation.", apiex);
		}
	}

	@Override
	public List<ProcessSite> processSites() {
		try {
			WaitingperiodResponse response = api.waitingperiods10WaitingperiodGet();
			if (response != null && !response.isEmpty()) {
				return response.stream().filter(Objects::nonNull).map(r -> r.getProcessSite()).filter(Objects::nonNull)
						.collect(Collectors.toList());
			} else {
				return null;
			}
		} catch (ApiException apiex) {
			throw new FraportApiException("Error executing the API operation.", apiex);
		}
	}

}
