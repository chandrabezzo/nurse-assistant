package com.widyatama.core.base;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("meta")
    @Expose
    Meta meta;

    @SerializedName("status")
    @Expose
    Integer code;

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("error")
    @Expose
    Error error;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public class Meta{
        Integer totalData;
        Integer totalPages;

        Meta(Integer totalData, Integer totalPages){
            this.totalData = totalData;
            this.totalPages = totalPages;
        }

        public Integer getTotalData() {
            return totalData;
        }

        public void setTotalData(Integer totalData) {
            this.totalData = totalData;
        }

        public Integer getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
        }
    }

    public class Error {
        String title;
        String detail;
        String code;

        Error(String title, String detail, String code){
            this.title = title;
            this.detail = detail;
            this.code = code;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
