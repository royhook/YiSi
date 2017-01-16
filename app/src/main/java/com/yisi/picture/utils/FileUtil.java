package com.yisi.picture.utils;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * File处理类
 *
 * @author YangGuoMing
 */
public class FileUtil {
    public static String TAG = "FileUtilFilter";

    /***
     * 检查文件是否存在
     *
     * @param sFilePath 文件完整路径
     * @return
     */
    @SuppressWarnings("finally")
    public static boolean checkFileExist(String sFilePath) {
        return checkExist(sFilePath, true);
    }

    /***
     * 检查路径或者目录是否存在
     *
     * @param sFilePath 文件完整路径
     * @return
     */
    @SuppressWarnings("finally")
    public static boolean checkDirectoryExist(String sFilePath) {
        return checkExist(sFilePath, false);
    }

    /**
     * 检查文件或者路径是否存在
     *
     * @param sFilePath
     * @param isFile
     * @return
     */
    @SuppressWarnings("finally")
    private static boolean checkExist(String sFilePath, boolean isFile) {
        boolean result = false;

        try {
            File file = new File(sFilePath);
            if (isFile && file.isFile() && file.exists()) {
                result = true;
            }
            if (!isFile && file.isDirectory() && file.exists()) {
                result = true;
            }
        } catch (Exception e) {
            Log.e(TAG, "检查文件失败：" + e.getMessage());
        } finally {
            /* return */
            return result;
        }
    }

    /***
     * 创建文件
     *
     * @param sFileDir  文件当前目录
     * @param sFileName 文件名称
     * @param hidden    是否为隐藏文件
     * @return true-成功
     * @throws IOException
     */
    private static boolean create(String sFileDir, String sFileName, boolean hidden) throws IOException {
        File fileDir = createDir(sFileDir);

        if (hidden) {
            sFileName = "." + sFileName;
        }

        sFileName = "/" + sFileName;

        if (fileDir == null) {
            throw new IOException("创建文件根路径失败");
        }

        File newFile = new File(fileDir, sFileName);

        return make(newFile);
    }

    /**
     * 生成文件
     *
     * @param filePath 完整路径
     * @return
     */
    @SuppressWarnings("finally")
    private static boolean make(File filePath) {
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }
        boolean result = false;
        if (!filePath.exists()) {
            try {
                result = filePath.createNewFile();
            } catch (IOException e) {
                Log.e(TAG, "make--创建失败：" + e.getMessage());
            } finally {
                return result;
            }
        }
        return result;
    }

    /**
     * 创建隐藏文件
     *
     * @param sFileDir  隐藏文件目录
     * @param sFileName 隐藏文件名称
     * @return true-成功
     * @throws IOException
     */
    public static boolean createHiddenFile(String sFileDir, String sFileName) throws IOException {
        String fileName = sFileName;

        while (fileName.startsWith("/")) {
            fileName = fileName.substring(fileName.indexOf("/") + 1);
        }

        return create(sFileDir, fileName, true);
    }

    /**
     * 创建普通文件
     *
     * @param sFileDir  文件目录
     * @param sFileName 文件名称
     * @return true-成功
     * @throws IOException
     */
    public static boolean createFile(String sFileDir, String sFileName) throws IOException {
        return create(sFileDir, sFileName, false);
    }

    /**
     * 创建普通文件
     *
     * @param destination 完整路径
     * @return
     */
    public static boolean createFile(String destination) {
        return make(new File(destination));
    }

    /**
     * 创建目录
     *
     * @param dirPath 目录路径
     * @return File 被创建的目录
     */
    @SuppressWarnings("finally")
    public static File createDir(String dirPath) {
        File newDir = null;
        try {
            newDir = new File(dirPath);
            if (!newDir.exists()) {
                newDir.mkdirs();
            }
        } catch (Exception e) {
            newDir = null;
            Log.e(TAG, "创建目录失败：" + e.getMessage());
        } finally {
            return newDir;
        }
    }

    /***
     * @param collection 内容
     * @param singleLine 是否一行一行写
     * @param mark       自定义行分隔标记
     * @param path       文件路径
     * @param append     文件是否末尾追加
     * @return
     */
    public static boolean write(Collection<String> collection, boolean singleLine, String mark, String path,
                                boolean append) {
        return write(collection, singleLine, mark, path, append, "utf-8");
    }

    /***
     * @param collection 内容
     * @param singleLine 是否一行一行写
     * @param path       文件路径
     * @param append     文件是否末尾追加
     * @return
     */
    public static boolean write(Collection<String> collection, boolean singleLine, String path, boolean append) {
        return write(collection, singleLine, null, path, append, "utf-8");
    }

    /***
     * @param collection 内容
     * @param singleLine 是否一行一行写
     * @param mark       自定义行分隔标记
     * @param path       文件路径
     * @return
     */
    public static boolean write(Collection<String> collection, boolean singleLine, String mark, String path) {
        return write(collection, singleLine, mark, path, false, "utf-8");
    }

    /***
     * @param collection 内容
     * @param singleLine 是否一行一行写
     * @param path       文件路径
     * @return
     */
    public static boolean write(Collection<String> collection, boolean singleLine, String path) {
        return write(collection, singleLine, null, path, false, "utf-8");
    }

    /***
     * 写出
     *
     * @param collection  内容集合
     * @param singleLine  是否一行一行写
     * @param mark        自定义行分隔标记
     * @param path        文件路径
     * @param append      是否追加
     * @param charsetName 编码
     * @return
     */
    @SuppressWarnings("finally")
    public static boolean write(Collection<String> collection, boolean singleLine, String mark, String path,
                                boolean append, String charsetName) {
        boolean result = false;
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            File desFile = new File(path);

            String fileDirPath = path.substring(0, path.lastIndexOf("/"));
            File fileDIr = new File(fileDirPath);
            if (!fileDIr.exists()) {
                fileDIr.mkdir();
            }

            fileOutputStream = new FileOutputStream(desFile, append);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, charsetName);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            for (String content : collection) {
                if (mark != null) {
                    content += mark;
                }
                if (singleLine) {
                    bufferedWriter.newLine();
                }
                bufferedWriter.write(content);
                bufferedWriter.flush();
            }
            result = true;
        } catch (Exception e) {
            Log.e(TAG, "写出文件失败:" + e.getMessage());
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (final IOException e) {
                    Log.e(TAG, "BufferedWriter关闭失败:" + e.getMessage());
                } finally {
                    if (outputStreamWriter != null) {
                        try {
                            outputStreamWriter.close();
                        } catch (final IOException e) {
                            Log.e(TAG, "OutputStreamWriter关闭失败:" + e.getMessage());
                        } finally {
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (final IOException e) {
                                    Log.e(TAG, "FileOutputStream关闭失败:" + e.getMessage());
                                }
                            }
                        }
                    }
                }
            }
            return result;
        }
    }

    /**
     * 写出
     *
     * @param content     内容为字符串
     * @param singleLine  是否一行一行写
     * @param mark        自定义行分隔标记
     * @param path        文件路径
     * @param append      是否追加
     * @param charsetName 编码格式
     * @return
     */
    public static boolean writeSting(String content, boolean singleLine, String mark, String path, boolean append,
                                     String charsetName) {

        LinkedList<String> contentList = new LinkedList<String>();
        contentList.add(content);

        return write(contentList, singleLine, mark, path, append, charsetName);
    }

    /**
     * 写出
     *
     * @param content    内容
     * @param singleLine 是否一行一行写
     * @param path       文件路径
     * @param append     是否追加
     * @return
     */
    public static boolean writeSting(String content, boolean singleLine, String path, boolean append) {
        return writeSting(content, singleLine, null, path, append, "utf-8");
    }

    /**
     * 删除目录
     *
     * @param filePath 文件路径(绝对路径)
     * @return
     */
    public static void deleteFile(String filePath) {
        File delDir = new File(filePath);

        if (delDir.isDirectory()) {
            for (String dirName : delDir.list()) {
                deleteFile(filePath + "/" + dirName);
            }
        }else{
            deleteFileSafely(delDir);
        }
    }

    /**
     * 距离创建后time毫秒后删除文件
     *
     * @param path 路径
     * @param time 多久删除
     */
    public static void deleteFile(String path, long time) {
        File file = new File(path);
        if (file.exists()) {
            String[] files = file.getAbsoluteFile().list();
            for (String fileName : files) {
                File temporaryFile = new File(file, fileName);
                LogUtils.d(temporaryFile.getAbsolutePath());
                long createTime = temporaryFile.lastModified();
                if (System.currentTimeMillis() - createTime >= time) {
                    temporaryFile.delete();
                }
            }
        }
    }

    /**
     * 安全删除文件. 解决文件删除后重新创建导致报错的问题：open failed: EBUSY (Device or resource busy)
     * http://stackoverflow.com/questions/11539657/open-failed-ebusy-device-or-resource-busy
     *
     * @param file
     * @return
     */
    public static boolean deleteFileSafely(File file) {
        String tmpPath = file.getAbsolutePath() + System.currentTimeMillis();
        File tmp = new File(tmpPath);
        file.renameTo(tmp);
        return tmp.delete();
    }

    /***
     * 判断路径是否为只读
     *
     * @param path 路径
     * @return
     */
    @SuppressWarnings("finally")
    public static boolean readOnly(String path) {
        boolean result = false;
        String target = path + "/kyx_test";

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(path));
            fileOutputStream.write(1024);
        } catch (Exception e) {
            result = true;
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, "只读-fileOutputStream关闭失败");
                }
            }
            deleteFile(path);
            return result;
        }
    }

    /***
     * 根据制定符号标记读取
     *
     * @param source 文件完整路径
     * @param mark   制定字符串标记
     * @return
     * @throws IOException
     */
    @SuppressWarnings("finally")
    public static boolean read(String source, Collection<String> collection, String mark) throws IOException {
        @SuppressWarnings("unused")
        boolean result = false;

        File target = new File(source);

        if (!target.isFile() || !target.canRead() || !target.exists()) {
            throw new IOException("不是文件||文件不能读||文件不存在");
        }

        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        String line = null;
        try {
            fileInputStream = new FileInputStream(target);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                if (!TextUtils.isEmpty(line)) {
                    if (mark != null && line.endsWith(mark)) {
                        line = line.substring(0, line.lastIndexOf(mark));
                    }
                    collection.add(line);
                }
            }
            result = true;
        } catch (Exception e) {
            Log.e(TAG, "FileUtil-read-文件读取失败:" + e.getMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    Log.e(TAG, "FileUtil-read-bufferedReader关闭失败:" + e.getMessage());
                } finally {
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException e) {
                            Log.e(TAG, "FileUtil-read-inputStreamReader关闭失败:" + e.getMessage());
                        } finally {
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e) {
                                    Log.e(TAG, "FileUtil-read-fileInputStream关闭失败:" + e.getMessage());
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
    }

    /**
     * 读取文件
     *
     * @param source     文件完整路径
     * @param collection 结果集合
     * @return
     * @throws IOException
     */
    public static boolean read(String source, Collection<String> collection) throws IOException {
        return read(source, collection, null);
    }

    /**
     * 读取文件
     *
     * @param source 文件所在路径
     * @return 字符串
     * @throws IOException
     */
    @SuppressWarnings("finally")
    public static String read(String source) throws IOException {
        @SuppressWarnings("unused")
        StringBuffer sb = new StringBuffer();
        File target = new File(source);

        if (!target.isFile() || !target.canRead() || !target.exists()) {
            throw new IOException("不是文件||文件不能读||文件不存在");
        }

        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        String line = null;
        try {
            fileInputStream = new FileInputStream(target);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            sb.setLength(0);
            Log.e(TAG, "FileUtil-read-文件读取失败:" + e.getMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    Log.e(TAG, "FileUtil-read-bufferedReader关闭失败:" + e.getMessage());
                } finally {
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException e) {
                            Log.e(TAG, "FileUtil-read-inputStreamReader关闭失败:" + e.getMessage());
                        } finally {
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e) {
                                    Log.e(TAG, "FileUtil-read-fileInputStream关闭失败:" + e.getMessage());
                                }
                            }
                        }
                    }
                }
            }
            return sb.toString();
        }
    }

    @SuppressWarnings("unused")
    private static <T> void writeListToFile(List<T> t, String filename, String path, boolean append) {
        File saveFile = new File(path, filename);
        Gson gson = new Gson();
        String json = gson.toJson(t);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(saveFile, append);
            fos.write(json.getBytes("utf-8"));
            fos.write("\n".getBytes("utf-8"));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将对象转换成JSON写出到本地文件
     *
     * @param t          被写出对象
     * @param fileName   文件名
     * @param fileDir    文件根路径
     * @param singleLine 是否逐行写
     * @param append     是否追加
     * @return
     */
    public static <T> boolean writeObjectListToFile(T t, String fileDir, String fileName, boolean singleLine,
                                                    boolean append) {
        File saveFile = new File(fileDir, fileName);

        Gson gson = new Gson();
        String jsonResult = gson.toJson(t);

        return writeSting(jsonResult, singleLine, saveFile.getAbsolutePath(), append);
    }

    /**
     * 将对象转换成JSON写出到本地文件
     *
     * @param t          被写出对象
     * @param fileName   文件名
     * @param fileDir    文件根路径
     * @param singleLine 是否逐行写
     * @return
     */
    public static <T> boolean writeObjectToFile(T t, String fileDir, String fileName, boolean singleLine) {
        File saveFile = new File(fileDir, fileName);

        Gson gson = new Gson();
        String jsonResult = gson.toJson(t);

        return writeSting(jsonResult, singleLine, saveFile.getAbsolutePath(), false);
    }

    /**
     * 将对象转换成JSON写出到本地文件
     *
     * @param t        被写出对象
     * @param fileName 文件名
     * @param fileDir  文件根路径
     * @return
     */
    public static <T> boolean writeObjectToFile(T t, String fileDir, String fileName) {
        File saveFile = new File(fileDir, fileName);

        Gson gson = new Gson();
        String jsonResult = gson.toJson(t);

        return writeSting(jsonResult, false, saveFile.getAbsolutePath(), false);
    }

    /***
     * 流写出（下载小文件）
     *
     * @param inputStream
     * @param path        写出目的地
     */
    public static void inToWriteInternet(InputStream inputStream, String path) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int c = -1;
        try {
            c = bufferedInputStream.read();
            while (c != -1) {
                byteArrayOutputStream.write(c);
                c = bufferedInputStream.read();
            }
        } catch (IOException e) {
            Log.e(TAG, "BufferedInputStream读取失败！！" + e.getMessage());
        } finally {
            if (null != bufferedInputStream) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, "BufferedInputStream关闭失败！！" + e.getMessage());
                }
            }
        }
        try {
            writeSting(byteArrayOutputStream.toString(), false, path, false);
        } catch (Exception e) {
            Log.e(TAG, "byteArrayOutputStream转换失败！！" + e.getMessage());
        } finally {
            if (null != byteArrayOutputStream) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, "byteArrayOutputStream关闭失败！！" + e.getMessage());
                }
            }
        }

    }

    /**
     * 合并文件
     *
     * @param targetFile
     * @param childFiles
     * @throws IOException
     */
    public static void combine(File targetFile, File[] childFiles) {
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            // 处理目标文件
            if (targetFile.exists()) {
                targetFile.delete();
            }

            File fileParentDir = targetFile.getParentFile();
            if (!fileParentDir.exists()) {
                fileParentDir.mkdirs();
            }
            targetFile.createNewFile();
            // 合并
            byte[] buffer = new byte[1024];
            int len = 0;
            fos = new FileOutputStream(targetFile);
            for (int i = 0; i < childFiles.length; i++) {
                fis = new FileInputStream(childFiles[i]);
                while ((len = fis.read(buffer, 0, 1024)) != -1) {
                    fos.write(buffer, 0, len);
                    fos.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取文件大小
     *
     * @param
     * @return
     * @throws Exception
     */
    @SuppressWarnings("resource")
    public static long getFileSizes(File f) throws Exception {// 取得文件大小
        long s = 0;
        if (f.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(f);
            s = fis.available();
        }
        return s;
    }

    /**
     * 创建输出IO流
     *
     * @param file file文件对象
     * @throws FileNotFoundException
     */
    public static FileOutputStream createOutputStream(File file) throws FileNotFoundException {
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return new FileOutputStream(file);
    }

    /**
     * 创建输出IO流
     *
     * @param path 输出位置（绝对路径）
     * @return FileOutputStream
     * @throws FileNotFoundException
     */
    public static FileOutputStream createOutputStream(String path) throws FileNotFoundException {
        return createOutputStream(new File(path));
    }

    /**
     * 边度边写 , InputStream转换OutputStream
     *
     * @param inputStream  输入流
     * @param outputStream 输出流
     */
    public static void inToOut(InputStream inputStream, OutputStream outputStream) {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.flush();
            }
        } catch (Throwable e) {
            Log.e(TAG, "in2Out,转换失败");
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    Log.e(TAG, "in2Out,bufferedWriter关闭失败");
                    e.printStackTrace();
                } finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            Log.e(TAG, "in2Out，bufferedReader关闭失败");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 边度编写
     *
     * @param inputStream
     * @param path        写出位置（绝对路径）
     */
    public static void inToWriteNative(InputStream inputStream, String path) {
        try {
            inToOut(inputStream, createOutputStream(path));
        } catch (FileNotFoundException e) {
            Log.e(TAG, "创建输出流失败");
            e.printStackTrace();
        }
    }

    /**
     * 读TXT文件内容
     *
     * @param fileName
     * @return
     */
    public static String readTxtFile(File fileName) {
        String result = null;
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
            try {
                String read = null;
                while ((read = bufferedReader.readLine()) != null) {
                    result = read;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 读TXT文件内容
     *
     * @param
     * @return
     */
    public static String readTxtFile(InputStream inputStream) throws Exception {
        String result = null;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            try {
                String read;
                while ((read = bufferedReader.readLine()) != null) {
                    result = read;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return result;
    }

    public static boolean writeTxtFile(String content, File fileName) {
        boolean flag = false;
        FileOutputStream o = null;
        try {
            File parentpath = fileName.getParentFile();
            if (!parentpath.exists()) {
                parentpath.mkdirs();
            }
            o = new FileOutputStream(fileName);
            o.write(content.getBytes("UTF-8"));
            o.close();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (o != null)
                try {
                    o.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return flag;
    }

    /**
     * 获取该文件路径的文件后缀名  忽略大小写
     *
     * @param filePath
     * @return
     */
    public static String getFileExtension(String filePath) {
        if (TextUtils.isEmpty(filePath))
            return "";

        String path = filePath;
        if(path.startsWith("http")){
            path = path.substring(path.lastIndexOf("http"), path.length());
        }

        if (path.contains("?")) {
            path = path.substring(0, path.indexOf("?"));
        }
        int extenPosi = path.lastIndexOf(".");
        int filePosi = path.lastIndexOf(File.separator);
        if (extenPosi == -1)
            return "";
        return (filePosi >= extenPosi) ? "" : path.substring(extenPosi + 1).toLowerCase();
    }

    /**
     * Reads the given file, translating {@link IOException} to a
     * {@link RuntimeException} of some sort.
     *
     * @param file non-null; the file to read
     * @return non-null; contents of the file
     */
    public static byte[] readFile(File file)
            throws IOException {
        return readFile(file, 0, -1);
    }

    /**
     * Reads the specified block from the given file, translating
     * {@link IOException} to a {@link RuntimeException} of some sort.
     *
     * @param file   non-null; the file to read
     * @param offset the offset to begin reading
     * @param length the number of bytes to read, or -1 to read to the
     *               end of the file
     * @return non-null; contents of the file
     */
    public static byte[] readFile(File file, int offset, int length)
            throws IOException {
        if (!file.exists()) {
            throw new RuntimeException(file + ": file not found");
        }

        if (!file.isFile()) {
            throw new RuntimeException(file + ": not a file");
        }

        if (!file.canRead()) {
            throw new RuntimeException(file + ": file not readable");
        }

        long longLength = file.length();
        int fileLength = (int) longLength;
        if (fileLength != longLength) {
            throw new RuntimeException(file + ": file too long");
        }

        if (length == -1) {
            length = fileLength - offset;
        }

        if (offset + length > fileLength) {
            throw new RuntimeException(file + ": file too short");
        }

        FileInputStream in = new FileInputStream(file);

        int at = offset;
        while (at > 0) {
            long amt = in.skip(at);
            if (amt == -1) {
                throw new RuntimeException(file + ": unexpected EOF");
            }
            at -= amt;
        }

        byte[] result = readStream(in, length);

        in.close();

        return result;
    }

    public static byte[] readStream(InputStream in, int length)
            throws IOException {
        byte[] result = new byte[length];
        int at = 0;

        while (length > 0) {
            int amt = in.read(result, at, length);
            if (amt == -1) {
                throw new RuntimeException("unexpected EOF");
            }
            at += amt;
            length -= amt;
        }

        return result;
    }

    public static void UnZipFolder(ZipInputStream inZip, String outPathString) throws Exception {
        ZipEntry zipEntry;
        String szName = "";
        while ((zipEntry = inZip.getNextEntry()) != null) {
            szName = zipEntry.getName();
            if (zipEntry.isDirectory()) {
                // get the folder name of the widget
                szName = szName.substring(0, szName.length() - 1);
                File folder = new File(outPathString + File.separator + szName);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
            } else {

                File file = new File(outPathString + File.separator + szName);
                if (!file.getParentFile().exists()) {
                    boolean result = file.getParentFile().mkdirs();
                }
                if (!file.exists()) {
                    boolean result = file.createNewFile();
                }
                // get the output stream of the file
                FileOutputStream out = new FileOutputStream(file);
                int len;
                byte[] buffer = new byte[1024];
                // read (len) bytes into buffer
                while ((len = inZip.read(buffer)) != -1) {
                    // write (len) byte from buffer at the position 0
                    out.write(buffer, 0, len);
                    out.flush();
                }
                out.close();
            }
        }
        inZip.close();
    }

    /**
     * 移动文件或目录 重命名文件或目录
     *
     * @param oldPath
     * @param newPath
     * @return
     */
    public static boolean renameFileOrDir(String oldPath, String newPath) {
        try {
            File oldFile = new File(oldPath);
            if (!oldFile.exists())
                return false;
            File newFile = new File(newPath);
            if (newFile.exists())
                newFile.delete();
            return makeDirs(newPath) && oldFile.renameTo(newFile);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 创建文件夹
     */
    public static boolean makeDirs(String filePath) {
        String folderName = getFolderName(filePath);

        if (TextUtils.isEmpty(folderName))
            return false;

        File folder = new File(folderName);
        if (folder.exists() && folder.isDirectory())
            return true;

        return folder.mkdirs();
    }

    /**
     * 获取该文件的所属上级文件夹
     *
     * @param filePath
     * @return
     */
    public static String getFolderName(String filePath) {
        if (TextUtils.isEmpty(filePath))
            return filePath;

        File file = new File(filePath);
        if (file.exists() && file.isDirectory())
            return filePath;

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
    }

}
