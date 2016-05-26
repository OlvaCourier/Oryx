package olva.oryx.service;

import com.jcabi.log.Logger;
import olva.oryx.controller.OryxController;
import olva.oryx.print.PrintService;
import olva.oryx.system.InitBehavior;
import olva.oryx.util.Constants;

/**
 * @since 15.02.2016
 * @author clarico
 *
 */
public class Init {

	/**
	 * Run and go
	 * @param args
     */
	public static void main(String[] args) {
		try {
			InitBehavior.run(args);
		} catch (Exception e) {
			e.printStackTrace();
            System.exit(0);
		}

		Logger.info(Init.class, "Starting..!");
		new OryxController();
	}



}
