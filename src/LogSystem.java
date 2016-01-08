import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by Gvozd on 09.01.2016.
 */
public class LogSystem {
    private static LogSystem instance = new LogSystem();
    private String filePath="";
    private String fileName = "system.log";
    private String tempFilePath="";
    private String tempFileName="system.log";

    private LogSystem() {
        fileCreate(filePath, fileName);
        initialization();
    }

    public static LogSystem getInstance(){
        return instance;
    }

    private boolean isFileCreated(){
        File fileTry = new File(filePath+fileName);
        return fileTry.exists();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.tempFileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.tempFilePath = filePath;
    }

    public boolean commitFileChanges(){
        if (fileCreate(tempFilePath, tempFileName)) {
            return true;
        } else return false;
    }

    public boolean fileCreate(String filePath, String fileName){
        File fileTry = new File(filePath+fileName);
        try {
            if (!fileTry.exists()){
                fileTry.createNewFile();
                return true;
            } else return false;
        } catch (IOException e) {
            this.filePath="";
            this.fileName="system.log";
            return false;
        }
    }

    private void initialization(){
        File fileInit = new File(filePath+fileName);
        Date date = new Date();

        try {
            PrintWriter out = new PrintWriter(fileInit.getAbsoluteFile());
            try {
                out.print("File created: "+date);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
