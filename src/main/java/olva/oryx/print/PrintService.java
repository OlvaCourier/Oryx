package olva.oryx.print;


import com.jcabi.log.Logger;
import olva.oryx.template.VelocityService;
import olva.oryx.util.JsonUtil;
import java.io.StringWriter;
import java.util.Map;



/**
 * Print Service
 *
 * @author Carlos D Larico (clarico@olva.com.pe)
 */
public interface PrintService {


    void print(String template, String printer, String data);


    /**
     * Basic print service
     */
    final class BasicPrintService implements  PrintService{

        @Override
        public void print(String template, String printer, String data) {
            Map<String, String> $data = JsonUtil.parseJsonToMap(data);
            StringWriter writer = new VelocityService().transform(template, $data);
            Logger.info(this, "Data to print: %s", writer.toString());
            new Print().print(new StringBuilder(writer.toString() ), printer);
        }
    }
}
