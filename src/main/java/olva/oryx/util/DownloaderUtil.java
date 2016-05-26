package olva.oryx.util;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by clarico on 03/05/2016.
 */
public class DownloaderUtil {

    private String responseMessage = "Installation Successful";

    private String init() throws IOException, ZipException {
        downloadFile();
        unZip(Constants.TMP_FILE_ZIP, Constants.TMP_PATH);
        copyFiles();
        return responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public DownloaderUtil() {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
            responseMessage = e.getMessage();
        } catch (ZipException e) {
            responseMessage = e.getMessage();
        }
    }

    /**
     *
     * Descarga el archivo de la url registrada en <code>Constants.URL_DOWNLOAD</code>
     * y lo guarda en la ubicacion temporal  registrada <code>Constants.TMP_FILE_ZIP</code>
     * @throws IOException
     *
     */
    public void downloadFile() throws IOException {
        URL urlDownload = new URL(Constants.URL_DOWNLOAD);
        ReadableByteChannel rbc = Channels.newChannel(urlDownload.openStream());
        FileOutputStream fos = new FileOutputStream(Constants.TMP_FILE_ZIP);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
    }

    /**
     * Descomprime un archivo zip
     * @param zipFile path del archivo zip a descomprimir
     * @param outputFolder lugar donde se descomprimira el archivo
     * @throws ZipException
     */
    public void unZip(String zipFile, String outputFolder) throws ZipException {
        System.out.println("Descomprimiendo "+outputFolder);
        ZipFile zip = new ZipFile(zipFile);
        zip.extractAll(outputFolder);
    }

    public void copyFiles() throws IOException {
        listDirectory(Constants.TMP_PATH + Constants.TMP_FILENAME, 0);
    }

    /**
     * Metodo recursivo para copiar todos los archivos de la carpeta temporal a
     *  la carpeta final
     *
     * @param dirPath carpeta a recorrer
     * @param level nivel de recursividad, <code>0<code> para default
     * @throws IOException
     */
    public void listDirectory(String dirPath, int level) throws IOException {
        File dir = new File(dirPath);
        File[] firstLevelFiles = dir.listFiles();
        if (firstLevelFiles != null && firstLevelFiles.length > 0) {
            for (File aFile : firstLevelFiles) {
                for (int i = 0; i < level; i++) {
                    System.out.print("");
                }
                if (aFile.isDirectory()) {
                    listDirectory(aFile.getAbsolutePath(), level + 1);
                } else {

                    copyFile(aFile.getAbsolutePath(),
                            Constants.ORYX_TEMPLATE_FOLDER +
                            aFile.getAbsolutePath().substring((Constants.TMP_PATH + Constants.TMP_FILENAME).length(),
                            aFile.getAbsolutePath().length()));

                }
            }
        }
    }

    public void createFolder(File theDir){

        if (!theDir.exists()) {
            boolean result = false;

            try{
                theDir.mkdir();
                result = true;
            }
            catch(SecurityException se){
                //handle it
            }
            if(result) {
                System.out.println("DIR created");
            }
        }
    }

    /**
     * Copia el archivo a la nueva ruta
     * @param input archivo origen
     * @param out archivo final
     * @throws IOException
     */
    public void copyFile(String input, String out) throws IOException {

        InputStream inStream = null;
        OutputStream outStream = null;
        File afile = new File(input);
        createFolder(afile);
        File bfile = new File(out);
        File parent = bfile.getParentFile();
        if(!parent.exists() && !parent.mkdirs()){
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }

        inStream = new FileInputStream(afile);
        outStream = new FileOutputStream(bfile);

        byte[] buffer = new byte[1024];

        int length;
        // copy the file content in bytes
        while ((length = inStream.read(buffer)) > 0) {
            outStream.write(buffer, 0, length);
        }

        inStream.close();
        outStream.close();
    }


}
