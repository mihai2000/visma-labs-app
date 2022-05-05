package com.doubletex.app.errors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
@JsonIgnoreProperties({"suppressed", "cause", "stackTrace","localizedMessage"})
public class DbtNotFound extends RuntimeException {
    private Class<?> entityClass;
    private long id;
    private final LocalDateTime time = LocalDateTime.now();
    private final int httpCode = HttpStatus.NOT_FOUND.value();

    public DbtNotFound(Class<?> entityClass, long id) {
        this.entityClass = entityClass;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "An entity of type " + entityClass.getSimpleName() + "with id: " + id + " was not found";
    }
}