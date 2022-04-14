package com.future.observercommon.util;

import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 文件读取工具类
 */
public final class FileUtil extends FileCopyUtils {

    /**
     * 根据文件的网络路径，返回文件的byte数组
     */
    public static byte[] receiveFile(String networkUrl) throws IOException {
        URL url = new URL(networkUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        InputStream in = conn.getInputStream();

        return copyToByteArray(in);
    }

    /**
     * 根据文件的物理路径，返回文件的byte数组
     */
    public static byte[] readFileAsBytes(String path) throws IOException {
        return copyToByteArray(new File(path));
    }

    /**
     * 根据文件的byte数组，在指定物理路径下创建文件
     */
    public static File createFile(byte[] in, String path) throws IOException {
        File file = new File(path);

        if (!file.exists()) {
            File dir = file.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            file.createNewFile();
        }

        if (in != null) {
            copy(in, file);
        }

        return file;
    }

    /**
     * 在指定物理路径下创建文件
     */
    public static File createFile(String path) throws IOException {
        return createFile(null, path);
    }

    /**
     * 在指定物理路径下创建目录
     */
    public static File createDir(String path) throws IOException {
        File dir = new File(path);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir;
    }
}
