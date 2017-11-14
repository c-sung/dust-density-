package tw.saihs.testserver.api;


import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import tw.saihs.testserver.DAO.Service4DAO;

import java.io.IOException;

@Path("{look_up}")
public class Look_Up {
    private static final Gson GSON = new Gson();

    @Path("{time}")
    @GET
    public Response get(@PathParam("time") String time) throws IOException {
        Service4DAO service4DAO = new Service4DAO();
        return Response.ok().entity(GSON.toJson(service4DAO.getValueOfTime(time))).build();
    }
}