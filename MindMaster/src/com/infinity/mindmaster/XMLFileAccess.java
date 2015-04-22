package com.infinity.mindmaster;

import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.util.Log;

public class XMLFileAccess {
	
	public int SoundStatus =1;
	public int DifficultyLevel=1;
	public String Path="test1XML.xml";
	   
	// <editor-fold defaultstate="collapsed" desc="Saving to the XML file">  
	   
	   public void SaveSettingsToXML() {
	        
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder;
	        try {
	            dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.newDocument();            
	            Element rootElement = doc.createElementNS("http://www.InfinityMindMaster.com/settings", "settings");            
	            doc.appendChild(rootElement);
	            rootElement.appendChild((org.w3c.dom.Node) getSetting(doc, getSoundStatus(), getDifficultyLevel())); //should get the setting and difficulty level form a set of variables
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	            DOMSource source = new DOMSource(doc);
	            Log.v("OK1: ", "ajshdfkasydfaksdb ahskdjashd aksdh ");
	            
	            StreamResult file = new StreamResult(new File(getPath()));
	            
	            transformer.transform(source, file);
	            Log.v("OK: ", "ajshdfkasydfaksdb ahskdjashd aksdh ");
	 
	        } catch (Exception e) {
	        	Log.v("Error: ", e.getMessage());
	        }
	    }
		
		public static Element getSetting(Document doc, int soundStatus, int difficultyLevel) {  
	      
	    Element settingNode = doc.createElement("settings");
	        settingNode.appendChild((org.w3c.dom.Node) getSettingElements(doc, "soundStatus", String.valueOf(soundStatus)));
	        settingNode.appendChild((org.w3c.dom.Node) getSettingElements(doc, "difficultyLevel", String.valueOf(difficultyLevel)));
	         return settingNode;
	    }
	    
	    public static Element getSettingElements(Document doc, String name, String value) {
	        Element node = doc.createElement(name);
	        node.appendChild(doc.createTextNode(value));
	        return node;
	    }
	    
	   // </editor-fold>  

	    // <editor-fold defaultstate="collapsed" desc="Reading from the XML file">  
	    
	    
	    public void loadTheXMLFile() 
	   {
	      
	      try {
	         File fXmlFile = new File(getPath());
	         if(fXmlFile.exists()){
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(fXmlFile);

	         doc.getDocumentElement().normalize();

	         
	         NodeList nList = doc.getElementsByTagName("settings");
	         int listSize=nList.getLength();
	         
	         for (int temp = 0; temp < listSize; temp++) {

	           org.w3c.dom.Node nNode = nList.item(temp);
	           getSettings((nNode));
	             
	         }
	         }
	         
	      } catch (Exception e) {
	    	  Log.v("Error: ", e.getMessage());
	      }    
	   }
	   
	   public void getSettings(org.w3c.dom.Node nNode) {
	        try {           
	            
	           Element eElement = (Element) nNode;
	           setSoundStatus(Integer.valueOf(eElement.getElementsByTagName("soundStatus").item(0).getTextContent()));
	           setDifficultyLevel(Integer.valueOf(eElement.getElementsByTagName("difficultyLevel").item(0).getTextContent()));
	            		
	        } catch (Exception e) {
	        	Log.v("Error: ", e.getMessage());
	        }
	        
	    }
	    
	    // </editor-fold>  
	    
	   
	   
	   // <editor-fold defaultstate="collapsed" desc="Get and Set Methods"> .
	    public int getSoundStatus(){return SoundStatus;}  
	    public int getDifficultyLevel(){return DifficultyLevel;}    
	    public String getPath(){return Path;}
	    
	    public void setSoundStatus(int para1){SoundStatus=para1;}  
	    public void setDifficultyLevel(int para1){DifficultyLevel=para1;}    
	    
	   // </editor-fold>  

}
