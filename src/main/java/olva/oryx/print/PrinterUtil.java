package olva.oryx.print;

import com.google.gson.Gson;


import javax.print.*;
import javax.print.PrintService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Printer Utils
 *
 * @author Carlos D Larico (clarico@kiwisoft.pe)
 */
public class PrinterUtil {

    /**
     * default printer
     */
    public static String DEFAULT_PRINTER = "default";

    /**
     * return array with all printer names
     * @return list strign
     */
    public static List<String> getPrinterByName() {
        List<String> listPrinters = new ArrayList<>();
        javax.print.PrintService[] printers = PrintServiceLookup.lookupPrintServices(null, null);
        IntStream.range(0, printers.length)
                .forEach(
                        i ->listPrinters.add(printers[i].getName())
                );
        return listPrinters;
    }

    /**
     * get json array with al printers
     * @return json array
     */
    public static String getPrinterAsJson(){
        return new Gson().toJson(getPrinterByName());
    }

    /**
     * get printerservice by printer name
     * @param name printer name
     * @return printer service
     */
    public synchronized static javax.print.PrintService getPrintServiceByName(String name) {

        if(name.equals(DEFAULT_PRINTER)){
            return PrintServiceLookup.lookupDefaultPrintService();
        }

        javax.print.PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            if(printService.getName().toUpperCase().contains(name.toUpperCase())){
                return printService;
            }
        }
        // FIXME: 26/05/2016 return a new exception like PrinterNOFound or use DefaultPrinter?
        throw new NullPointerException();
    }



}
