package olva.oryx.controller;

import olva.oryx.util.DownloaderUtil;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Created by clarico on 03/05/2016.
 */
public class InstallController {

    public InstallController() {
        /**
         * Servicio devuelve los nombres de las impresoras configuradas al equipo
         */
        post("/install", (r, q)->{
            DownloaderUtil downloaderUtil = new DownloaderUtil();
            return downloaderUtil.getResponseMessage();
        });
    }

}
