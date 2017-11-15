package tw.saihs.testserver;

import tw.saihs.testserver.api.Look_Up;
import tw.saihs.testserver.api.Device;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class ServerApplication extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> restServiceSet = new HashSet<Class<?>>();

		restServiceSet.add(Device.class);
		restServiceSet.add(Look_Up.class);

		return restServiceSet;
	}
}