# EventLoggingSystem
Logging system "LogSystem" ver.7 by Yakimov Denis.
denis.yakimov89@gmail.com
Made for Hillel Java Elementary Courses.

Object LogSystem can be created only as a singleton by an getInstance() method. It has two logging methods - write(LogStringData) and writeWithClassName(whoThrowedLogString, LogStringData). First just logs into log file date and message LogStringData. Second besides date and data logs the classtype of object who send logging data into a logger.

LogSystem has a configuration file named "LoggerConfig.log" that has to be in a package directory. It's a plain text file with a properties of a log file that will contain logs.
The properties are:
1)boolean - is FilePath is absolute path or it's just a folder that roots from package.
2)File path for log file. It can be absolute or root from a package. (!!! Don't mistake that property, or you'll get an Exception !!!)
3)Filename for a log file.
4)Extension for a log file.

Standard configuration file has such data:
"
false
\EventLog\
system
log
"
So the name of new files will be system-mm-dd-yyyy(-verx).log in a directory EventLog in a package folder.

New log file created every time you starting a new copy of program that uses that singleton. When a log file is more than 100kb, new one file is created. All files are named by data and version of a file.

Good luck, use this logger.
