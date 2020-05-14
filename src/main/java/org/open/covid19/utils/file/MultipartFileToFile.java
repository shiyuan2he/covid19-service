package org.open.covid19.utils.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * MultipartFile转为file类型
 * @author wuchao
 */
public class MultipartFileToFile {
    /**
     * MultipartFile 转 File
     *
     * @param file
     * @throws Exception
     */
    public static File toFile(MultipartFile file) {
        if (null == file || file.getSize() <= 0) {
            return null;
        }
        File toFile = null;
        try {
            InputStream ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toFile;
    }

    /**
     * 获取流文件
     * @param ins
     * @param file
     */
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除本地临时文件
     * @param file
     */
    public static void delteTempFile(File file) {
        if (file != null) {
            File del = new File(file.toURI());
            del.delete();
        }
    }
}
