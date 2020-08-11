package com.suju.vote.exception;

import com.suju.vote.utils.response.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public RespBean sqlException(SQLException e) {
        if (e instanceof SQLIntegrityConstraintViolationException) {
            log.info("exception message is {}", e.getMessage());
            return RespBean.error("该数据有关联数据，操作失败!错误信息为 " + e.getMessage());
        }
        return RespBean.error("数据库异常，操作失败!");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public RespBean usernameNotFoundException(UsernameNotFoundException e) {
        return RespBean.ok("系统找不到该用户的记录");
    }

    @ExceptionHandler(FileNotFoundException.class)
    public RespBean fileNotFoundException(FileNotFoundException e) {
        return RespBean.error("后端找不到相应的文件");
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity illegalStateException(IllegalStateException e) {
        return new ResponseEntity<Object>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        // return RespBean.error(e.getMessage());
    }
}
