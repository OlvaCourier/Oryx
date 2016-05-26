package olva.oryx.controller;

import com.jcabi.log.Logger;
import olva.oryx.print.PrintService;
import olva.oryx.util.Constants;

import static spark.Spark.*;


public class OryxController {

	/**
	 * @author clarico
	 * @since  20.02.2016
	 */
    public OryxController() {

        Logger.info(this, "Running in port:%s", Constants.PORT);
        port(Constants.PORT);

        // TODO has been config
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "http://localhost:8080");
            response.header("Access-Control-Allow-Methods", "POST");
            response.header("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
            response.header("Access-Control-Allow-Credentials", "true");
        });

        new PrinterController(new PrintService.BasicPrintService());

	}
}
