package org.gradientworks.passwordmanager.util.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class PasswordManagerException {

    public static void send(HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
        log.error("Error "+ex.getMessage());
        response.setHeader("errors", ex.getMessage());
        //response.sendError(FORBIDDEN.value());
        response.setStatus(FORBIDDEN.value());
        Map<String, String> errors = new HashMap<>();
        errors.put("error_message", ex.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), errors);
    }

}
