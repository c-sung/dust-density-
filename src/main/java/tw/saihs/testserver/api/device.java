package tw.saihs.testserver.api;

import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import tw.saihs.testserver.DAO.Service3DAO;

import java.io.IOException;

@Path("device")
public class device {
    private static final Gson GSON = new Gson();

    @Path("{time}")
    @POST
    public Response post(@PathParam("time") String time, String body) throws IOException {
        DetectData detectData = new DetectData();
        detectData = GSON.fromJson(body, detectData.getClass());
        Service3DAO service3DAO = new Service3DAO();
        service3DAO.putData(time, detectData);
        return null;
    }

}


