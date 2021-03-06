package Bean;

import java.io.Serializable;

public class Response implements Serializable {
    private Object result;
    private Boolean status;

    public Response(Object o, Boolean b){
        this.result = o;
        this.status = b;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
