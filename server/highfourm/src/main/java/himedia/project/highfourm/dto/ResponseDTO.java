package himedia.project.highfourm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
	
	private T data;
//	private boolean success;
//    private String message;
//    private Error error;
    
//    //성공 시
//    public static <T> ResponseDTO<T> success(T data) {
//    return new ResponseDTO<>(true, data, null);
//    }
//
//  	//실패 시
//  	public static <T> ResponseDTO<T> fail(String code, String message) {
//    return new ResponseDTO<>(false, null, new Error(code, message));
//  	}
// 
// 	@Getter
//  	@AllArgsConstructor
//  	static class Error {
//    	private String code;
//    	private String message;
//  	}
}