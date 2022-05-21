package monckey.chopper.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Time;
import java.time.Instant;

@AllArgsConstructor
@Getter
public class Error {
    private String error;
    private String message;
    private Integer status;
    private String url = "Not available.";
    private String reqMethod = "Not available.";
    private Instant timestamp;

    public Error(){
        super();
    }

    public void setError(String error){
        this.error = error;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public Error setUrl(String url){
        return this;
    }

    public Error setReqMethod(String reqMethod){
        return this;
    }

    public Error setTime(Instant timestamp){
        return this;
    }
}
