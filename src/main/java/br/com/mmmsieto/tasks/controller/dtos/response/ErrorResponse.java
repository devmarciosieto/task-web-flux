package br.com.mmmsieto.tasks.controller.dtos.response;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private int status;
    private String message;

    public ErrorResponse() {}

    public ErrorResponse(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Builder builder() {
        return new Builder();
    }
    
    public static Builder builderFrom(ErrorResponse errorResponse) {
        return new Builder(errorResponse);
    }

    public static ErrorResponse internalError(RuntimeException e) {
        return ErrorResponse.builder()
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .withMessage(e.getMessage())
                .build();
    }

    public static class Builder {
        private int status;
        private String message;

        public Builder(ErrorResponse errorResponse) {
            this.status = errorResponse.status;
            this.message = errorResponse.message;
        }

        public Builder() {}

        public Builder withStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }

    }

}
