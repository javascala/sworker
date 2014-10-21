package com.sworker.enumutil;

public enum AttachmentType {

	img("0"),doc("1"),sound("2"),video("3");
	
	private String value;
	
	private AttachmentType(String value){
		
		this.value=value;
		
	}
	
	public String getValue(){
		
		return this.value;
		
	}
	
	
	
}
