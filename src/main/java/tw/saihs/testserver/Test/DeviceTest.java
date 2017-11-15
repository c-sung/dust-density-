package tw.saihs.testserver.Test;

import org.junit.Test;
import tw.saihs.testserver.api.Device;

import java.io.IOException;

public class DeviceTest {
    private static final String TIME_DATA = "201711151728";
    private static final String BODY_DATA = "{\"dd\":\"13\",\"humid\":\"28\",\"temp\":\"32\"}";
    private static final String EXPECT_RESPONSE = "\"rsp_code\":\"0\",\"rsp_msg\":\"ok\"";

    @Test
    public void testDevice() throws IOException {
        Device device = new Device();
        String response = String.valueOf(device.post(TIME_DATA,BODY_DATA));
        if(response.equals(EXPECT_RESPONSE)) {
            System.out.println("OK");
        } else {
            System.out.println("fail");
        }
    }
}