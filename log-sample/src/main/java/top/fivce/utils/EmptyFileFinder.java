package top.fivce.utils;

import top.fivce.logback.NewLogbackXmlElement;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author wubin
 */
public class EmptyFileFinder {

    private static void findEmptyFile(File file, List<File> fileList){
        //如果文件夹为空
        if (file.isDirectory()&&Objects.requireNonNull(file.listFiles()).length==0){
            fileList.add(file);
        }else if (file.isDirectory()){
            File[] files = file.listFiles();
            Objects.requireNonNull(files);
            for (File f:files
                 ) {
                findEmptyFile(f,fileList);
            }
        }
    }

    private static List<File> judgeOverdueEmptyFile(List<File> fileList){
        NewLogbackXmlElement xmlElement = NewLogbackXmlElement.getInstance();
        String fileNamePattern = xmlElement.getFileNamePattern();
        String maxHistory = xmlElement.getMaxHistory();
        String timePattern = fileNamePattern.substring(fileNamePattern.indexOf("{"),fileNamePattern.indexOf("}")+1);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(timePattern);

        return null;

    }

    public static void main(String[] args) {
        File file = new File("/Users/wubin/Documents/workspace/RepoGit/experimental/log");
        List<File> fileList = new ArrayList<>();
        findEmptyFile(file,fileList);
        System.out.println(fileList);
    }
}
