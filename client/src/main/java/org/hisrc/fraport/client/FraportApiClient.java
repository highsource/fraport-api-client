package org.hisrc.fraport.client;

import java.util.List;

import org.hisrc.fraport.client.model.Gate;
import org.hisrc.fraport.client.model.ProcessSite;

public interface FraportApiClient {

	public Gate gate(String name);
	public List<Gate> gates();
	
	public ProcessSite processSite(String name);
	public List<ProcessSite> processSites();
	
}
