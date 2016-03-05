package org.hisrc.fraport.client;

import org.hisrc.fraport.client.invoker.ApiException;

public class FraportApiException extends RuntimeException {
	private static final long serialVersionUID = 8301313499805392414L;

	public FraportApiException(String message, ApiException cause) {
		super(message, cause);
	}

	@Override
	public synchronized ApiException getCause() {
		return (ApiException) super.getCause();
	}
}
