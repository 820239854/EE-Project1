package Bean;

public class Result {
    private Integer code;
    private Object data;
    private String message;

    public Integer getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Reslut{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
