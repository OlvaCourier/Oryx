/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olva.oryx.print;

import com.jcabi.log.Logger;

import javax.print.*;
import javax.print.PrintService;
import java.security.AccessController;
import java.security.PrivilegedAction;


/**
 * Print
 *
 * @author Carlos D Larico (clarico@olva.com.pe)
 */
public class Print {


	private synchronized void doPrint (StringBuilder data, String printerName) throws PrintException {

		PrintService printService = PrinterUtil.getPrintServiceByName(printerName);

		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		DocPrintJob printerJob = printService.createPrintJob();
		byte[] bytes;

		bytes = data.toString().getBytes();
		Doc doc = new SimpleDoc(bytes, flavor, null);
		try {
			printerJob.print(doc, null);
		} catch (PrintException ex) {
			throw new PrintException("");
		}
	}

	public synchronized void print(final StringBuilder data, final String printerName) {

		AccessController.doPrivileged((PrivilegedAction) () -> {
            try {
                doPrint(data, printerName);
                return true;
            } catch (PrintException e) {
				Logger.info(this, "Printing Error %e", e);
				return false;
            }
        });
	}



}
