package tw.kewang.testserver.DAO;

import com.google.gson.Gson;

import javax.ws.rs.core.Response;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import tw.kewang.testserver.api.DetectData;

import java.io.IOException;

public class Service3DAO {
    private static final byte[] DUST_DENSITY = Bytes.toBytes("dd");
    private static final byte[] HUMID = Bytes.toBytes("humid");
    private static final byte[] TEMPERATURE = Bytes.toBytes("temp");
    private static final byte[] CF = Bytes.toBytes("cf:");
    private static final Gson GSON = new Gson();
    private static final Configuration hbaseConfig = HBaseConfiguration.create();

    public Response putData(String time, DetectData detectData) throws IOException {
        String dd, humid, temp;
        dd = detectData.getDd();
        humid = detectData.getHumid();
        temp = detectData.getTemp();
        return hbaseConnect(time, dd, humid, temp);
    }

    private Response hbaseConnect(String time, String dd, String humid, String temp) throws IOException {
        hbaseConfig.set("hbase.zookeeper.quorum", "localhost");
        HTable table = new HTable(hbaseConfig, "data");
        Service3_DAO_Response service3_dao_response = new Service3_DAO_Response();
        if (!checkIfNull(time, dd, humid, temp)) {
            service3_dao_response.setRsp_code("999");
            service3_dao_response.setRsp_msg("bad request: 參數不完整");
        } else {
            table.put(put(time, dd, humid, temp));
            service3_dao_response.setRsp_msg("ok");
            service3_dao_response.setRsp_code("0");
        }
        return Response.ok().entity(GSON.toJson(service3_dao_response)).build();
    }

    private static boolean checkIfNull(String time, String dd, String humid, String temp) {
        boolean answer = false;
        if (!time.equals(null) && !dd.equals(null) && !humid.equals(null) && !temp.equals(null)) {
            answer = true;
            return answer;
        } else {
            answer = false;
            return answer;
        }
    }

    private static Put put(String time, String dd, String humid, String temp) {
        Put data_to_save = new Put(Bytes.toBytes(time));
        data_to_save.addColumn(CF, DUST_DENSITY, Bytes.toBytes(dd));
        data_to_save.addColumn(CF, HUMID, Bytes.toBytes(humid));
        data_to_save.addColumn(CF, TEMPERATURE, Bytes.toBytes(temp));
        return data_to_save;
    }

}
