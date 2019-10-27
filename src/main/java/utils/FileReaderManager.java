package utils;

public class FileReaderManager {
 
	private static FileReaderManager fileReaderManager = new FileReaderManager();
	private static ConfigFileReader configFileReader;
	private static JsonDataReader jsonDataReader;
	 
 
	private FileReaderManager() {
		
	
	}
 
	 public static FileReaderManager getInstance( ) {
		 
	      return fileReaderManager;
	 }
 
	 public ConfigFileReader getConfigReader() {
		
		if(configFileReader == null){
			configFileReader = new ConfigFileReader();
		}
		return configFileReader;
	 }
	 
	 public JsonDataReader getJsonReader(){
	
		 if(jsonDataReader == null){
			 jsonDataReader = new JsonDataReader();
			}
		return jsonDataReader;
	}
}