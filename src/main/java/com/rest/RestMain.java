package com.rest;

import com.rest.data.CaseDataHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.apache.http.util.TextUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuanke(zhouliwei1989 @ 126.com)
 * @createTime 2019-12-13-18:06
 * @description 程序的主入口
 */
@Slf4j
public class RestMain {

    // 命令行参数常量
    private final static String CmdHelp = "help";
    private final static String CmdCasePath = "casePath";
    private final static String CmdHarPath = "harFilePath";

    // 用来存放命令行参数
    private static Map<String, String> paramsValues;

    /**
     * 打印帮助信息
     */
    private static void printHelp() {
        Options options = buildOptions();
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("java -jar ${jar} [options]", options);
    }

    /**
     * 构建命令行的Options
     *
     * @return
     */
    private static Options buildOptions() {
        Options options = new Options();

        // 添加-h 打印帮助信息
        options.addOption(Option.builder("h").argName(CmdHelp).required(false).desc("show command help").build());
        // 添加casePath参数，指定接口配置
        options.addOption(Option.builder("cp").argName(CmdCasePath).hasArg().longOpt(CmdCasePath).required(false).desc("specify the case file path or folder path").build());
        // 添加harPath格式参数，指定录制接口的har文件的路径
        options.addOption(Option.builder("hp").argName(CmdHarPath).hasArg().longOpt(CmdHarPath).required(false).desc("specify the har path").build());


        return options;
    }

    /**
     * 对命令行参数进行解析
     *
     * @param arguments
     * @return
     */
    private static boolean pareArguments(String[] arguments) {
        boolean isSuccess = true;
        Options options = buildOptions();
        CommandLine commandLine = null;
        try {
            commandLine = new DefaultParser().parse(options, arguments);

            if (commandLine.hasOption(CmdHelp)) {
                printHelp();
                System.exit(0);
            }

            // 读取用例文件或目录
            String casePath = commandLine.getOptionValue(CmdCasePath, "");
            if (!TextUtils.isEmpty(casePath)){
                File caseFile = new File(casePath);
                if (caseFile.isFile()){
                    String fileName = caseFile.getName();
                    if (!TextUtils.isEmpty(fileName) && fileName.endsWith(".yml")){
                        paramsValues.put(CmdCasePath, casePath);
                    }else {
                        log.error("用输入正确的用例配置文件（以yml结尾的文件）");
                        isSuccess = false;
                    }
                }else {
                    File[] caseFiles = caseFile.listFiles(new FileFilter() {
                        public boolean accept(File pathname) {

                            return pathname.isFile() && pathname.getName().endsWith("yml");
                        }
                    });
                    if (caseFiles != null && caseFiles.length > 0){
                        paramsValues.put(CmdCasePath, casePath);
                    }else {
                        log.error("用输入正确的用例目录（目录包含yml用例配置文件）");
                        isSuccess = false;
                    }
                }
            }

            // 读取Har文件
            String harPath = commandLine.getOptionValue(CmdHarPath, "");
            if (!TextUtils.isEmpty(harPath)){
                File harFile = new File(harPath);
                if (harFile.isFile()){
                    String fileName = harFile.getName();
                    if (!TextUtils.isEmpty(fileName) && fileName.endsWith(".har")){
                        paramsValues.put(CmdHarPath, harPath);
                    }else {
                        log.error("用输入正确的har文件");
                        isSuccess = false;
                    }
                }
            }
        } catch (ParseException e) {
            log.error("解析命令时出现异常，异常信息是{}", e.getMessage());
            System.exit(1);
        }

        return isSuccess;
    }


    public static void main(String[] args) {
        // 初始化存放解析参数的结果
        paramsValues = new HashMap<String, String>();
        boolean result = pareArguments(args);
        if (!result){
            log.error("解析参数失败，程序退出运行");
            return;
        }

        // 开始执行接口
        if (paramsValues.size() > 0){
            String casePath = paramsValues.get(CmdCasePath);
            if (!TextUtils.isEmpty(casePath)){
                CaseDataHandler caseDataHandler = new CaseDataHandler(casePath);
                caseDataHandler.handleYmlPath();
            }
        }
    }
}