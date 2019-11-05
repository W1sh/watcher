package com.w1sh.watcher.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {

    private HttpStatus status;
    private String message;
    private List<String> errors;

}
