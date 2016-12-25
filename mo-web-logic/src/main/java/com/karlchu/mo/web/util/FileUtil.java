package com.karlchu.mo.web.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Huy
 * Date: 8/28/15
 * Time: 4:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileUtil {

    private static final int BUFSIZE = 2 << 15;
    protected static final Log log = LogFactory.getLog(FileUtil.class);
    public static String upload(HttpServletRequest request, String destFolder,
                                MultipartFile uplFile) throws IOException, ServletException {
        String fileName = normalizeFilename(uplFile.getOriginalFilename());
        File pathToSave = buildDestinationFile(request, destFolder, fileName);
        // Save to file
        uplFile.transferTo(pathToSave);
        return pathToSave.getName();
    }

    public static File buildDestinationFile(HttpServletRequest request,
                                            String destFolder, String fileName) {
        ServletContext context = request.getSession().getServletContext();
        String commonDirpath = context.getRealPath(destFolder);
        File baseFile = new File(commonDirpath);
        if (!baseFile.exists()) {
            baseFile.mkdir();
        }

        String newName = "";
        // Parse the request
        // Get just file name of upload file

        newName = normalizeFilename(fileName);
        // Get name withoout exension , get extension
        String nameWithoutExt = normalizeFilename(FilenameUtils.getBaseName(fileName));
        String ext = FilenameUtils.getExtension(fileName);

        // ********************PATH to SAVE FILE************************
        File pathToSave = new File(commonDirpath, fileName);

        int counter = 1;
        // Check if existed, generating new file name
        while (pathToSave.exists()) {
            // New filename = name(counter).ext
            StringBuffer buffer = new StringBuffer();
            buffer.append(nameWithoutExt).append("(").append(counter).append(")").append(".").append(ext);
            newName = buffer.toString();

            // Create new file to receive uploaded file
            pathToSave = new File(commonDirpath, newName);

            counter++;
        }
        return pathToSave;
    }

    private static String normalizeFilename(String fileName) {
        String res = fileName.replaceAll(" ", "_");
        return res;
    }

    public static void remove(String filename){
        File file = new File(filename);
        file.delete();
    }

    public static String uploadTempFile(HttpServletRequest request, String destFolder,
                                        MultipartFile uplFile) throws IOException, ServletException {
        String fileName = Long.valueOf(new Date().getTime()).toString()+"."+uplFile.getOriginalFilename().split("\\.")[1];
        File pathToSave = buildDestinationFile(request, destFolder, fileName);
        uplFile.transferTo(pathToSave);
        return pathToSave.getName();
    }

    public static void removeFileOrDirectory(File file) throws IOException{
        if(file.isDirectory()){
            if(file.list().length == 0){
                file.delete();
            }else{
                String files[] = file.list();
                for (String temp : files) {
                    File fileDelete = new File(file, temp);
                    removeFileOrDirectory(fileDelete);
                }
                if(file.list().length == 0){
                    file.delete();
                }
            }
        }else{
            file.delete();
        }
    }
}
