import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by Gvozd on 09.01.2016.
 */
public class LogSystem {
    private static LogSystem instance = new LogSystem();
    private String filePath="\\EventLog\\";
    private String fileName = "system";
    private String fileExtension ="log";
    private String tempFilePath=filePath;
    private String tempFileName=fileName;
    private String tempFileExtension =fileExtension;

    private LogSystem() {
        fileCreate(filePath, fileName, fileExtension);
        initialization();
    }

    public static LogSystem getInstance(){
        return instance;
    }

    private boolean isFileCreated(){
        File fileTry = new File(filePath, fileName+"."+fileExtension);
        return fileTry.exists();
    }

    private boolean fileCreate(String filePath, String fileName, String fileExtension){
        File fileTry = new File(filePath, fileName+"."+fileExtension);
        try {
            if (!fileTry.exists()){
                fileTry.createNewFile();
                return true;
            } else return false;
        } catch (IOException e) {
            this.filePath="";
            this.fileName="system";
            this.fileExtension="log";
            return false;
        }
    }

    private void initialization(){
        File fileInit = new File(filePath, fileName+"."+fileExtension);
        Date date = new Date();

        try {
            PrintWriter out = new PrintWriter(fileInit.getAbsoluteFile());
            try {
                out.print ("Program starts. Log system by Gvozd.");
                out.print("File created: "+date);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public boolean commitFileChanges(){
        if (fileCreate(tempFilePath, tempFileName, tempFileExtension)) {
            File fileInit = new File(filePath, fileName+"."+fileExtension);
            File fileInit2 = new File(tempFilePath, tempFileName+"."+tempFileExtension);
            fileInit.renameTo(fileInit2);
            return true;
        } else return false;
    }

//    public void update(String nameFile, String newText) throws FileNotFoundException {
//        exists(fileName);
//        StringBuilder sb = new StringBuilder();
//        String oldFile = read(nameFile);
//        sb.append(oldFile);
//        sb.append(newText);
//        write(nameFile, sb.toString());
//    }


}
