package oopds.assignment.DC.models;

public class DataResponse<T> {
    private T data = null;
    private String message = "";
    
    public DataResponse(){}
    public DataResponse(T data, String message){
        this.data = data;
        this.message = message;
    }

    public T getData(){
        return data;
    }

    public String getMessage(){
        return message;
    }
}
