package olva.oryx.controller;

import olva.oryx.print.PrintService;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Created by clarico on 03/05/2016.
 */
public class PrinterController {

    public PrinterController(PrintService printService) {
        /**
         * Para analizar la peticion de el servicio de tipo:
         *
         * http://localhost:{PORT}/dinamicPrint?template={TEMPLATE_NAME}&printer={PRINTER_NAME}
         * Donde:
         * {TEMPLATE_NAME} 	=> nombre del template (es el mismo nombre del archivo .vm)
         * {PRINTER_NAME} 	=> nombre de la impresora, en caso de que no se envie se tomara
         * 					   la impresora por default
         *
         * Ademas el contenido (json) sera introducido en el template para generar el <StringBuilder>
         * y generar la impresion
         *
         */
        post("/print", (r, q) -> {
            String template = r.queryParams("template");
            String printer = r.queryParams("printer");

            if(printer == null || printer.isEmpty()){
                printer = "default";
            }
            printService.print(template, printer, r.body());
            q.header("Access-Control-Allow-Credentials", "true");
            return "Done";
        } );

        /**
         * Servicio devuelve los nombres de las impresoras configuradas al equipo
         */
        get("/printers", (r, q)->{
            return "";
            //return printService.getPrinters();
        });

    }
}
