package com.system.dao;

import java.util.List;

import com.system.entity.Protocol;

public interface ProtocolDAO {
	public boolean addProtocol(Protocol protocol);
	public List<Protocol> protocolList();
	
	
}
