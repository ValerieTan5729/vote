package com.suju.vote.utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.suju.vote.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class ExcelUtils {
    public static List<Account> importAccount(int type, MultipartFile excel) throws IOException {
        File file = changeFile(excel);
        List<Account> res = new ArrayList<>();
        // 字符集设为gbk, 避免出现中文乱码的问题(windows下为gbk, linux下为utf-8)
        String charset = "gbk";
        FileInputStream input = null;
        try {
            input = new FileInputStream(file);
            Reader reader = new InputStreamReader(input, charset);
            // 定义CSVParse, 使得读取csv文件的时候不把'\'当成转义符
            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
            CSVReader csvReader = new CSVReaderBuilder(new BufferedReader(reader)).withCSVParser(parser).build();
            for (String[] content : csvReader) {
                // Arrays.stream(content).forEach(System.out::println);
                res.add(readRow(content, type));
            }
        } catch (Exception e) {
            log.error("读取csv文件出错");
            System.out.println("读取csv文件出错");
            e.printStackTrace();
            throw e;
        } finally {
            // 关闭csv文件
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }

    // 检查文件是否符合Excel格式
    private static File changeFile(MultipartFile multipartFile) throws IOException {
        //判断文件是否存在
        if (multipartFile == null) {
            System.out.println("文件不存在!");
            throw new FileNotFoundException("文件不存在/文件受损!");
        }
        String fileName = multipartFile.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // log.info("prefix is {}", prefix);
        // 判断文件是否为excel文件
        if (!prefix.equals(".csv")) {
            log.info("{}不是csv文件，无法进行解析", fileName);
            throw new IOException(fileName + "不是csv文件，无法进行解析");
        } else {
            // 若需要防止生成的临时文件重复, 可以在文件名后添加随机码
            File file = File.createTempFile(fileName, prefix);
            multipartFile.transferTo(file);
            return file;
        }
    }

    private static Account readRow(String[] content, int type){
        Account account = new Account();
        account.setName(content[0]);
        account.setType(type);
        account.setNote(NoteUtils.note(UserUtils.getCurrentUser().getName(), "csv导入账号"));
        return account;
    }
}
