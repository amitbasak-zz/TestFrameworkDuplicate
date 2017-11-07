package com.infrrd.testfrog.helper;

import java.io.File;

public class FileStructureHelper {
	
	public static void makeDirectory(String folderPath){
		
		File file = new File(folderPath);
		if(!file.exists()){
			file.mkdir();
		}
	}

}
