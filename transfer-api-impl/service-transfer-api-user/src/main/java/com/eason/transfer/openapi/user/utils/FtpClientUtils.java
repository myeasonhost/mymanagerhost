package com.eason.transfer.openapi.user.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
@Slf4j
public class FtpClientUtils {
    @Value("${ftp.url}")
    private String url;
    @Value("${ftp.username}")
    private String username;
    @Value("${ftp.password}")
    private String password;
    @Value("${ftp.rootPath}")
    private String rootPath;

    private FTPClient connectFtpServer() throws Exception{
        FTPClient ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(1000*30);//设置连接超时时间
        ftpClient.setControlEncoding("utf-8");//设置ftp字符集
        ftpClient.enterLocalPassiveMode();//设置被动模式，文件传输端口设置
        try {
            ftpClient.connect(url);
            ftpClient.login(username,password);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置文件传输模式为二进制，可以保证传输的内容不会被改变
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)){
                log.error("FTP链接失败，详细原因：connect ftp {} failed",url);
                ftpClient.disconnect();
                throw new Exception("FTP链接失败");
            }
            log.info("replyCode==========={}",replyCode);
        } catch (IOException e) {
            log.error("FTP链接失败，详细原因：connect fail ------->>>{}",e.getCause());
            throw new Exception("FTP链接失败");
        }
        return ftpClient;
    }

    /**
     *
     * @param inputStream 待上传文件的输入流
     * @param originName 文件保存时的名字
     */
    public String uploadFile(InputStream inputStream,String path,String originName) throws Exception {
        FTPClient ftpClient = null;
        try {
            ftpClient = connectFtpServer();
            createDirecroty(ftpClient,path);
            ftpClient.changeWorkingDirectory(rootPath+"/"+path);//进入到文件保存的目录
            Boolean isSuccess = ftpClient.storeFile(originName,inputStream);//保存文件
            if (!isSuccess){
                throw new Exception(originName+"---》上传失败！");
            }
            log.info("{}---》上传成功！",originName);
            ftpClient.logout();
            return "http://"+url;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }finally {
            if (ftpClient.isConnected()){
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    log.error("disconnect fail ------->>>{}",e.getCause());
                }
            }
        }
    }

    //创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
    public static boolean createDirecroty(FTPClient ftpClient,String remote) throws IOException {
        boolean success = true;
        String directory = remote + "/";
//        String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(ftpClient,new String(directory))) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            String path = "";
            String paths = "";
            while (true) {

                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                path = path + "/" + subDirectory;
                if (!existFile(ftpClient,path)) {
                    if (makeDirectory(ftpClient,subDirectory)) {
                        changeWorkingDirectory(ftpClient,subDirectory);
                    } else {
                        log.debug("创建目录[" + subDirectory + "]失败");
                        changeWorkingDirectory(ftpClient,subDirectory);
                    }
                } else {
                    changeWorkingDirectory(ftpClient,subDirectory);
                }

                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }


    //判断ftp服务器文件是否存在
    public static boolean existFile(FTPClient ftpClient,String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }

    //创建目录
    public static boolean makeDirectory(FTPClient ftpClient,String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                log.debug("创建文件夹" + dir + " 成功！");

            } else {
                log.debug("创建文件夹" + dir + " 失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    //改变目录路径
    public static boolean changeWorkingDirectory(FTPClient ftpClient,String directory) {
        boolean flag = true;
        try {
            flag = ftpClient.changeWorkingDirectory(directory);
            if (flag) {
                log.debug("进入文件夹" + directory + " 成功！");

            } else {
                log.debug("进入文件夹" + directory + " 失败！");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return flag;
    }
}
