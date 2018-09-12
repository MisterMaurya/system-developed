package com.system.dao;

import com.system.entity.Device;

public interface DeviceDAO {
	public boolean insertDevice(Device device);
    public Device getDevice(int device_Id);
    public boolean isDeviceExists(int device_Id);
}
