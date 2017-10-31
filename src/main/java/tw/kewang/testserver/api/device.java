package tw.kewang.testserver.api;

<<<<<<< HEAD
<<<<<<< HEAD
public class device {
=======
=======
>>>>>>> a900341ed2edd8982bd33a2a21999e33034199b5
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

@Path("device")
public class device {
    private static final Gson GSON = new Gson();
    @Path("{time}")
    @POST
    public Response post(@PathParam("time") String time, String body) {
        DetectData detectData = new DetectData();
        detectData = GSON.fromJson(body,detectData.getClass());
        System.out.println(detectData);
        return null;
    }

<<<<<<< HEAD
>>>>>>> device
=======
=======
public class device {
>>>>>>> fecf8af6c4739d93e87086c1e3d8c7a286030835
>>>>>>> a900341ed2edd8982bd33a2a21999e33034199b5
}
