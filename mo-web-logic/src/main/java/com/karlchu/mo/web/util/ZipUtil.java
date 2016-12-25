package com.karlchu.mo.web.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Huy
 * Date: 8/28/15
 * Time: 3:03 PM
 * To change this template use File | Settings | File Templates.
 */

public class ZipUtil {

    protected static final Log LOG = LogFactory.getLog(ZipUtil.class);
    private static final int SCHUNK = 8192;
    private static final int BUFFER = 2048;

    public static boolean zip(String strSource, String strTarget) {
        try {
            File cpFile = new File(strSource);
            if (!cpFile.isFile() && !cpFile.isDirectory()) {
                LOG.error("\nSource file/directory " + strSource + " Not Found!");
                return false;
            }
            if (countFiles(cpFile) > 0) {
                FileOutputStream fos = new FileOutputStream(strTarget);
                ZipOutputStream cpZipOutputStream = new ZipOutputStream(fos);
                cpZipOutputStream.setLevel(9);
                zipFiles(cpZipOutputStream, cpFile, strSource, strTarget);
                cpZipOutputStream.finish();
                cpZipOutputStream.close();
            }
        } catch (Exception e) {
            LOG.error("Fail to create zip file!" + e.getMessage());
            return false;
        }
        return true;
    }

    private static int countFiles(File cpFile) {

        int numOfFiles = 0;
        if (cpFile.isDirectory()) {
            File[] fList = cpFile.listFiles();
            for (int i = 0; i < fList.length; i++) {
                numOfFiles += countFiles(fList[i]);
            }
        } else {
            numOfFiles++;
        }
        return numOfFiles;
    }


    public static File[] getListFiles(File cpFile) {
        File[] result = null;
        if (cpFile.isDirectory()) {
            File[] fList = cpFile.listFiles();
        }
        return result;
    }

    public static void extractAllSubZip(String dir) {
        File f = new File(dir);
        if (f.isDirectory()) {
            String path = f.getAbsolutePath();
            if (!path.endsWith("" + File.separatorChar)) {
                path += File.separatorChar;
            }
            String[] names = f.list();
            for (String name : names) {
                if(name.split("\\.").length == 2 && name.split("\\.")[1].equals("zip")){
                    extract(path + name);
                }

            }
        }
    }

    public static boolean extract(String zipName) {
        Enumeration entries;
        ZipFile zipFile;
        File f = new File(zipName);
        File newDir = f.getParentFile();

        try {
            if (!newDir.isDirectory()) {
                newDir.mkdir();
            }
            zipFile = new ZipFile(zipName);

            entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();

                if (entry.isDirectory()) {
                    String dir = newDir.getPath() + File.separator + entry.getName();
                    // This is not robust, just for demonstration purposes.
                    (new File(dir)).mkdir();

                    continue;
                } else {
                    //Check create directory for output file first
                    String[] path2File = entry.getName().split("[\\/\\\\]");
                    String tempCurrDir = newDir.getPath();
                    for (int i = 0; i < path2File.length - 1; i++) {
                        tempCurrDir += File.separator + path2File[i];
                        File dir = new File(tempCurrDir);
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                    }

                }
                String fname = entry.getName().replace('\\', File.separatorChar).replace('/', File.separatorChar);
                String newFile = newDir.getPath() + File.separator + fname;
                copyInputStream(zipFile.getInputStream(entry),
                        new BufferedOutputStream(new FileOutputStream(newFile)));
            }

            zipFile.close();
            f.delete();
        } catch (IOException ioe) {
            LOG.error(ioe.getMessage(), ioe);
            return false;
        }
        return true;
    }


    public static File getFirstExcelFile(File cpFile) {
        File result = null;
        if (cpFile.isDirectory()) {
            File[] fList = cpFile.listFiles();
            for (int i = 0; i < fList.length; i++) {
                if(fList[i].getName().contains(".xls"))
                {
                    result = fList[i];
                    break;
                }
            }
        }
        return result;
    }

    public static File[] getListCSVFile(File cpFile) {
        File[] result = null;
        if (cpFile.isDirectory()) {
            File[] fList = cpFile.listFiles();
            int count = 0;
            result = new File[fList.length];
            for (int i = 0; i < fList.length; i++) {
                if(fList[i].getName().contains(".csv")){
                    result[i] = fList[i];
                }
            }
        }
        return result;
    }

    /**
     * Zip files and subdirs to a zip file
     * @param cpFile File
     */
    private static void zipFiles(ZipOutputStream cpZipOutputStream, File cpFile, String strSource, String strTarget) {

        int byteCount;
        final int DATA_BLOCK_SIZE = 2048;
        FileInputStream cpFileInputStream;

        if (cpFile.isDirectory()) {
            File[] fList = cpFile.listFiles();
            for (int i = 0; i < fList.length; i++) {
                zipFiles(cpZipOutputStream, fList[i], strSource, strTarget);
            }
        } else {
            try {
                if (cpFile.getAbsolutePath().equalsIgnoreCase(strTarget)) {
                    return;
                }
                String strAbsPath = cpFile.getPath();
                String strZipEntryName = strAbsPath.substring(strSource.length() + 1, strAbsPath.length());

                cpFileInputStream = new FileInputStream(cpFile);
                ZipEntry cpZipEntry = new ZipEntry(strZipEntryName);
                cpZipOutputStream.putNextEntry(cpZipEntry);

                byte[] b = new byte[DATA_BLOCK_SIZE];
                while ((byteCount = cpFileInputStream.read(b, 0, DATA_BLOCK_SIZE)) != -1) {
                    cpZipOutputStream.write(b, 0, byteCount);
                }

                cpZipOutputStream.closeEntry();
                cpFileInputStream.close();
            } catch (Exception e) {
                LOG.error("Cannot put file " + cpFile + "to zip", e);
            }
        }
    }

    private static void copyInputStream(InputStream in, OutputStream out)
            throws IOException {
        byte[] buffer = new byte[BUFFER];
        int len;

        while ((len = in.read(buffer)) >= 0) {
            out.write(buffer, 0, len);
        }

        in.close();
        out.close();
    }

    public static File getUnzipDir(String zipName) {
        File file = new File(zipName);
        File parent = file.getParentFile();
        File newDir = new File(parent.getPath() + File.separator
                + file.getName().substring(0, file.getName().length() - 4));
        return newDir;
    }

    public static boolean unzip(String zipName) {
        return unzip(zipName, false);
    }

    @SuppressWarnings("rawtypes")
    public static boolean unzip(String zipName, boolean showLog) {
        Enumeration entries;
        ZipFile zipFile;
        File f = new File(zipName);
        File newDir = getUnzipDir(zipName);

        try {
            if (!newDir.isDirectory()) {
                newDir.mkdir();
            }
            zipFile = new ZipFile(zipName);

            entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();

                if (entry.isDirectory()) {
                    String dir = newDir.getPath() + File.separator + entry.getName();
                    if (showLog) {
                        LOG.info("Extracting directory: " + dir);
                    }
                    // This is not robust, just for demonstration purposes.
                    (new File(dir)).mkdir();

                    continue;
                } else {
                    //Check create directory for output file first
                    String[] path2File = entry.getName().split("[\\/\\\\]");
                    String tempCurrDir = newDir.getPath();
                    for (int i = 0; i < path2File.length - 1; i++) {
                        tempCurrDir += File.separator + path2File[i];
                        File dir = new File(tempCurrDir);
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                    }

                }
                String fname = entry.getName().replace('\\', File.separatorChar).replace('/', File.separatorChar);
                String newFile = newDir.getPath() + File.separator + fname;
                if (showLog) {
                    LOG.info("Extracting file: " + newFile);
                }


                copyInputStream(zipFile.getInputStream(entry),
                        new BufferedOutputStream(new FileOutputStream(newFile)));
            }

            zipFile.close();
            f.delete();
        } catch (IOException ioe) {
            LOG.error(ioe.getMessage(), ioe);
            return false;
        }
        return true;
    }

    //private static String formatEntryName(String name)
    public static boolean gunzip(String zipName) {
        // Create input stream.
        String zipname, source;
        if (zipName.endsWith(".gz")) {
            zipname = zipName;
            source = zipName.substring(0, zipName.length() - 3);
        } else {
            zipname = zipName + ".gz";
            source = zipName;
        }
        GZIPInputStream zipin;
        try {
            FileInputStream in = new FileInputStream(zipname);
            zipin = new GZIPInputStream(in);
        } catch (IOException e) {
            return false;
        }
        byte[] buffer = new byte[SCHUNK];
        // Decompress the file.
        try {
            FileOutputStream out = new FileOutputStream(source);
            int length;
            while ((length = zipin.read(buffer, 0, SCHUNK)) != -1) {
                out.write(buffer, 0, length);
            }
            out.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static void unzipAll(String dir) {
        File f = new File(dir);
        if (!f.exists()) {
            LOG.error("File Or Folder not found: " + f.getName());
            return;
        }
        if (f.isFile()) {
            if (f.getAbsolutePath().endsWith(".gz")) {
                if (gunzip(f.getAbsolutePath())) {
                    f.delete();
                }
            } else if (f.getAbsolutePath().endsWith(".zip")) {
                if (unzip(f.getAbsolutePath())) {
                    f.delete();
                }
            }
        } else if (f.isDirectory()) {
            String path = f.getAbsolutePath();
            if (!path.endsWith("" + File.separatorChar)) {
                path += File.separatorChar;
            }
            String[] names = f.list();
            for (String name : names) {
                unzipAll(path + name);
            }
        } else {
            LOG.error("File Format is unregconized : " + f.getName());
            return;
        }
    }

    public static void main(String[] args) {
        String testFolder = System.getProperty("user.home") + File.separator + "integrationxxx";
        unzip(testFolder + ".zip");
    }

    public static boolean isZipFile(File file) throws IOException {
        if(file.isDirectory()) {
            return false;
        }
        if(!file.canRead()) {
            throw new IOException("Cannot read file "+file.getAbsolutePath());
        }
        if(file.length() < 4) {
            return false;
        }
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        int test = in.readInt();
        in.close();
        return test == 0x504b0304;
    }
}
