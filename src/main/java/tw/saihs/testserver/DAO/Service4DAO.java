package tw.saihs.testserver.DAO;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class Service4DAO {
    private static final byte[] DUST_DENSITY = Bytes.toBytes("dd");
    private static final byte[] HUMID = Bytes.toBytes("humid");
    private static final byte[] TEMPERATURE = Bytes.toBytes("temp");
    private static final byte[] CF = Bytes.toBytes("cf:");
    private static final Configuration hbaseConfig = HBaseConfiguration.create();

    public Service4_DAO_Response getValueOfTime(String time) throws IOException {
        return hbaseConnect(time);
    }

    private Service4_DAO_Response hbaseConnect(String time) throws IOException {
        Result getBackValue = result(time);
        Service4_DAO_Response service4_dao_response = new Service4_DAO_Response();
        service4_dao_response.setDd(Bytes.toString(getBackValue.getValue(CF, DUST_DENSITY)));
        service4_dao_response.setHumid(Bytes.toString(getBackValue.getValue(CF, HUMID)));
        service4_dao_response.setTemp(Bytes.toString(getBackValue.getValue(CF, TEMPERATURE)));
        return service4_dao_response;
    }

    private Result result(String time) throws IOException {
        hbaseConfig.set("hbase.zookeeper.quorum", "localhost");
        HTable table = new HTable(hbaseConfig, "data");
        Get data = get(time);
        Result dataBack = table.get(data);
        return dataBack;
    }

    private Get get(String time) {
        Get get = new Get(Bytes.toBytes(time));
        get.addColumn(CF, DUST_DENSITY);
        get.addColumn(CF, TEMPERATURE);
        get.addColumn(CF, HUMID);
        return get;
    }
}
