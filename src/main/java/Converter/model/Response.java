package Converter.model;

import java.util.List;

public class Response{
	private List<ResponseItem> response;

	public List<ResponseItem> getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}