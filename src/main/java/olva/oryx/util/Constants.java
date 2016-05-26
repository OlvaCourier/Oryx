package olva.oryx.util;

public class Constants {

	/**
	 * Default port 666
	 */
	public static int PORT = 666;
	
	/**
	 * 
	 */
	public final static String SALTO = "\n";	
	
	/**
	 * 
	 */
	public final static String COMILLAS_DOBLES = "\"";
	
	/**
	 * 
	 */
	public final static String ORYX_TEMPLATE_FOLDER = System.getProperty("user.home")+"\\.Oryx\\";
	
	public final static String DEFAULT_PRINTER = "default";

	/**
	 * ruta de donde descargar los template
	 */
	public static String URL_DOWNLOAD = "";

	/*
	* path donde se guardaran los archivos temporales
	*/
	public static String TMP_PATH = System.getProperty("java.io.tmpdir")+"/";

	/**
	 * nombre del archivo temporal
	 */
	public static String TMP_FILENAME = "oryx_templates";


	/**
	 * path final del archivo temporal
	 */
	public static String TMP_FILE_ZIP = Constants.TMP_PATH+"/"+ Constants.TMP_FILENAME+".zip";

}
