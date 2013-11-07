package ru.ncedu.golmakov.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ru.ncedu.golmakov.objects.*;

public class Controller {
	
	public Controller() {
		super();
		this.fileList = new LinkedList<MyFile>();
		this.userList = new LinkedList<User>();
		this.folderList = new LinkedList<Folder>();
	}
	
	public void loadXml(String xmlFileName){
		try {
			File xmlFile=new File(xmlFileName);
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			Element root=doc.getDocumentElement();
			
			if(root.getNodeName().equals("files")){

				NodeList nl=root.getChildNodes();
				for(int i=0;i<nl.getLength();i++){
					Node child=nl.item(i);
					if(child.getNodeName().equals("file")){
						NodeList fields=child.getChildNodes();

						Long id = null;
						String name=null;
						Long parentId = null;
						String description = null;
						String extension = null;
						Long creatorId = null;
						String rights = null;
						String path = null;
									
						for(int j=0;j<fields.getLength();j++){
							String fname=fields.item(j).getNodeName();

							if(child.getAttributes().item(0).getNodeName().equals("id"))
								id=Long.decode(child.getAttributes().item(0).getNodeValue());
							if(fname.equals("name"))
								name=fields.item(j).getTextContent();
							if(fname.equals("parentId"))
								parentId=Long.decode(fields.item(j).getTextContent());
							if(fname.equals("description"))
								description=fields.item(j).getTextContent();
							if(fname.equals("extension"))
								extension=fields.item(j).getTextContent();
							if(fname.equals("creatorId"))
								creatorId=Long.decode(fields.item(j).getTextContent());
							if(fields.item(j).getNodeName().equals("rights"))
								rights=fields.item(j).getTextContent();
							if(fname.equals("path"))
								path=fields.item(j).getTextContent();
						}
						
						if((!id.equals(null))&&(!name.equals(null))&&(!parentId.equals(null))
								&&(!description.equals(null))&&(!extension.equals(null))&&(!creatorId.equals(null))
								&&(!rights.equals(null))&&(!path.equals(null)))
							this.add(new MyFile(id,name,parentId,description,extension,creatorId,rights,path));
					} 				
				}
				
			} else 	if(root.getNodeName().equals("folders")){

				NodeList nl=root.getChildNodes();
				for(int i=0;i<nl.getLength();i++){
					Node child=nl.item(i);
					if(child.getNodeName().equals("folder")){
						NodeList fields=child.getChildNodes();

						Long id = null;
						String name=null;
						Long parentId = null;
						Long creatorId = null;
						String rights = null;
															
						for(int j=0;j<fields.getLength();j++){
							String fname=fields.item(j).getNodeName();

							if(child.getAttributes().item(0).getNodeName().equals("id"))
								id=Long.decode(child.getAttributes().item(0).getNodeValue());
							if(fname.equals("name"))
								name=fields.item(j).getTextContent();
							if(fname.equals("parentId"))
								parentId=Long.decode(fields.item(j).getTextContent());
							if(fname.equals("creatorId"))
								creatorId=Long.decode(fields.item(j).getTextContent());
							if(fields.item(j).getNodeName().equals("rights"))
								rights=fields.item(j).getTextContent();
						}
						
						if((!id.equals(null))&&(!name.equals(null))&&(!parentId.equals(null))&&
								(!creatorId.equals(null))&&(!rights.equals(null)))
							this.add(new Folder(id,name,parentId,creatorId,rights));
					} 				
				}
				
			} else 	if(root.getNodeName().equals("users")){

				NodeList nl=root.getChildNodes();
				for(int i=0;i<nl.getLength();i++){
					Node child=nl.item(i);
					if(child.getNodeName().equals("user")){
						NodeList fields=child.getChildNodes();

						Long id = null;
						String name=null;
						String pathword = null;
															
						for(int j=0;j<fields.getLength();j++){
							String fname=fields.item(j).getNodeName();

							if(child.getAttributes().item(0).getNodeName().equals("id"))
								id=Long.decode(child.getAttributes().item(0).getNodeValue());
							if(fname.equals("name"))
								name=fields.item(j).getTextContent();
							if(fields.item(j).getNodeName().equals("rights"))
								pathword=fields.item(j).getTextContent();
						}
						
						if((!id.equals(null))&&(!name.equals(null))&&(!pathword.equals(null)))
							this.add(new User(id,name,pathword));
					} 				
				}
			}		
			
		}catch (Exception e) {
			e.printStackTrace();
		} 	
			
	}
	
	public void saveXml(String xmlFileName){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();        
        try {
            DocumentBuilder db = factory.newDocumentBuilder();
            DOMImplementation domImplementation = db.getDOMImplementation();
            Document doc = domImplementation.createDocument(null, "file_system", null);
            
            buildTree(doc, doc.getDocumentElement());
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            Properties transfProps = new Properties();
            transfProps.put("method", "xml");
            transfProps.put("indent", "yes");
            transformer.setOutputProperties(transfProps);
            
            DOMSource source = new DOMSource(doc);
            OutputStream out = new FileOutputStream(new File(xmlFileName));
            StreamResult result =  new StreamResult(out);
            
            transformer.transform(source, result);
            try {
                out.close();
            } catch (Exception ex) {}            
        } catch (Exception ex) {
            System.err.println("XML error\n"+ex.toString());
        }		        
	}
	
	
	public <T> void add(T object){
		if(object instanceof MyFile){
			fileList.add((MyFile) object);
		}
		if(object instanceof User){
			userList.add((User) object);
		}
		if(object instanceof Folder){
			folderList.add((Folder) object);
		}
	}
	
	public int remove(Long objId){
		for(MyFile i: fileList){
			if(i.getId().equals(objId)){
				fileList.remove(i);
				return 0;
			}
		}
		for(Folder i: folderList){
			if(i.getId().equals(objId)){
				folderList.remove(i);
				return 0;
			}
		}
		for(User i: userList){
			if(i.getId().equals(objId)){
				userList.remove(i);
				return 0;
			}
		}
		return 0;
	}
	
	public void list(){
		for(MyFile i: fileList){
			System.out.println(i.getName());
		}
		System.out.println();
		for(Folder i: folderList){
			System.out.println(i.getName());
		}
		System.out.println();
		for(User i: userList){
			System.out.println(i.getName());
		}
	}
	
	private void buildTree(Document doc, Element root) {
        
        doc.setXmlStandalone(true);
        doc.setStrictErrorChecking(true);
        doc.setXmlVersion("1.0");
        
        Element subRoot;     
        Element e;
        
        subRoot = createElement(doc, "files", null);  
        for(MyFile i: fileList){	
        	String[] args = {"name", "parentId", "description", "extension", "creatorId", "rights", "path"};
        	e = createXmlObj(doc,"file", args, 
        			i.getName(),
        			i.getParentId().toString(),
        			i.getDescription(),
        			i.getExtension(),
        			i.getCreatorId().toString(),
        			i.getRights(),
        			i.getPath());
        	e.setAttribute("id", i.getId().toString());
        	subRoot.appendChild(e); 
		}	  
        root.appendChild(subRoot);
        
        subRoot = createElement(doc, "folders", null);  
        for(Folder i: folderList){	
        	String[] args = {"name", "parentId", "creatorId", "rights"};
        	e = createXmlObj(doc,"folder", args, 
        			i.getName(),
        			i.getParentId().toString(),
        			i.getCreatorId().toString(),
        			i.getRights());
           	e.setAttribute("id", i.getId().toString());
        	subRoot.appendChild(e); 
		}	  
        root.appendChild(subRoot);
        
        subRoot = createElement(doc, "users", null);  
        for(User i: userList){	
        	String[] args = {"name", "pathword"};
        	e = createXmlObj(doc,"user", args, 
        			i.getName(),
        			i.getPathWord());
           	e.setAttribute("id", i.getId().toString());
        	subRoot.appendChild(e); 
		}	  
        root.appendChild(subRoot);
        
    }	
	
	private Element createXmlObj(Document doc, String root, String[] args, String ... values){
		Element e = createElement(doc, root, null);
		for(int i = 0; i < args.length; i++){
			e.appendChild(createElement(doc, args[i], values[i]));
		}
		return e;
	}	
	
	private Element createElement(Document doc, String name, String textContent) {
		Element elem = doc.createElement(name);        
        if(textContent!=null)
            elem.setTextContent(textContent);
        return elem;
    }
	
	private LinkedList<MyFile> fileList;
	private LinkedList<User> userList;
	private LinkedList<Folder> folderList;

}
