import java.io.*;
import java.util.Date;

/**
 * Created by Gvozd on 09.01.2016.
 */
public class LogSystem {
    private static LogSystem instance = new LogSystem();
    private File logfile;
    private String configFileName = "LoggerConfig.log";

    private LogSystem() {
        getLoggerConfig();
    }

    public static LogSystem getInstance() {
        return instance;
    }

    private void getLoggerConfig(){
        boolean isAbsolutePath=false;
        String filePath="";
        String fileName="";
        String fileExtension="";

        File configFile = new File(configFileName);
        try {
            if (!configFile.exists()) {
                createConfigFile();
            }
            try {
                BufferedReader in = new BufferedReader(new FileReader(configFile.getAbsoluteFile()));
                try {
                    String s="";
                    for (int i=0; i<4; i++){
                        s=in.readLine();
                        switch (i){
                            case 0: try{
                                isAbsolutePath = Boolean.parseBoolean(s);
                            } catch (Exception e) {
                                createConfigFile();;
                            }
                                break;

                            case 1: try{
                                filePath = s;
                            } catch (Exception e) {
                                createConfigFile();
                            }
                                break;

                            case 2: try{
                                fileName = s;
                            } catch (Exception e) {
                                createConfigFile();
                            }
                                break;

                            case 3: try{
                                fileExtension = s;
                            } catch (Exception e) {
                                createConfigFile();
                            }
                                break;
                        }
                    }
                } finally {
                    in.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            setLogfile(isAbsolutePath, filePath, fileName, fileExtension);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void createConfigFile(){
        File configFile = new File(configFileName);
        try {
            configFile.createNewFile();
            PrintWriter configOut = new PrintWriter(configFile.getAbsoluteFile());
            configOut.print("false\n" +
                    "\\EventLog\\\n" +
                    "system\n" +
                    "log");
            configOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setLogfile(boolean isAbsolutePath, String filePath, String fileName, String fileExtension){
        File tempFile = new File("");
        String tempPath="";
        if (!isAbsolutePath) {
           tempPath = tempFile.getAbsolutePath()+filePath;
        } else tempPath=filePath;
            tempFile = new File (tempPath);
            tempFile.mkdirs();
        createLogFile(tempFile+"\\"+fileName, fileExtension);
    }

    private void createLogFile(String filePathAndName, String fileExtension) {
        File tempLog = new File(filePathAndName + "-" + getSystemDate() + "." + fileExtension);

            if (tempLog.exists()) {
                boolean isLogCreated=false;
                int perForAdding=0;
                while (!isLogCreated){
                    perForAdding++;
                    tempLog = new File (filePathAndName + "-" + getSystemDate() +"-ver"+perForAdding+"." + fileExtension);
                    if (!tempLog.exists()) {
                        try {
                            tempLog.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            isLogCreated=true;
                        }
                    }
                }
            } else {
                try { tempLog.createNewFile();
                    logfile = tempLog;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        write("Log file created.");
    }

    private String getSystemDate(){
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

    private void write(String s) {
    }

}