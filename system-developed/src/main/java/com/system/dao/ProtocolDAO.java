package com.system.dao;

import java.util.List;

import com.system.entity.Protocol;

public interface ProtocolDAO {
	public String addProtocol(Protocol protocol);
	public List<Protocol> protocolList();
	public Protocol getProtocol(int protocolId);
	boolean updateProtocol(int protocolId,int deviceId);
	
}
