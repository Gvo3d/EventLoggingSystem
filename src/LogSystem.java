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
    private final String FILENAME = "system";
    private String fileExtension ="log";
    private String tempFilePath=filePath;
    private String tempFileExtension =fileExtension;

    private LogSystem() {
        File tempFile = new File("");
        String tempPath = tempFile.getAbsolutePath()+"\\EventLog\\";
        System.out.println(tempPath);
        tempFile = new File (tempPath);
        tempFile.mkdirs();
        filePath = tempPath;
        fileCreate(filePath, fileExtension);
        initialization();
    }

    public static LogSystem getInstance(){
        return instance;
    }

    private boolean isFileCreated(){
        File fileTry = new File(filePath, FILENAME+"."+fileExtension);
        return fileTry.exists();
    }

    private boolean fileCreate(String filePath, String fileExtension){
        File fileTry = new File(filePath, FILENAME+getSystemDate()+"."+fileExtension);
        try {
            if (!fileTry.exists()){
                fileTry.createNewFile();
                return true;
            } else return false;
        } catch (IOException e) {
            File tempFile = new File("");
            String tempPath = tempFile.getAbsolutePath()+"\\EventLog\\";
            this.filePath=tempPath;
            this.fileExtension="log";
            return false;
        }
    }

    private void initialization(){
        File fileInit = new File(filePath, FILENAME+"."+fileExtension);
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

    public String getSystemDate(){
        Date date = new Date();
        String tempTime = date.toString();
        String tempMons, tempMon, tempDay, tempYear;
        tempMons=tempTime.substring(4,7);
        switch (tempMons){
            case "Jan":tempMon="01";
                break;
            case "Feb":tempMon="02";
                break;
            case "Mar":tempMon="03";
                break;
            case "Apr":tempMon="04";
                break;
            case "May":tempMon="05";
                break;
            case "Jun":tempMon="06";
                break;
            case "Jul":tempMon="07";
                break;
            case "Aug":tempMon="08";
                break;
            case "Sep":tempMon="09";
                break;
            case "Oct":tempMon="10";
                break;
            case "Nov":tempMon="11";
                break;
            case "Dec":tempMon="12";
                break;
            default: tempMon="00";
                break;
        }
        tempDay=tempTime.substring(8,10);
        tempYear=tempTime.substring(24,28);
        return tempMon+"-"+tempDay+"-"+tempYear;
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
        if (fileCreate(tempFilePath, tempFileExtension)) {
            File fileInit = new File(filePath, FILENAME+"."+fileExtension);
            File fileInit2 = new File(tempFilePath, FILENAME+"."+tempFileExtension);
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
