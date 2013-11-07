package ru.ncedu.golmakov.controller;

import ru.ncedu.golmakov.objects.Folder;
import ru.ncedu.golmakov.objects.MyFile;
import ru.ncedu.golmakov.objects.User;

public class TestController {

	public static void main(String[] args) {
		Controller c=new Controller();
		MyFile mf=new MyFile(Long.valueOf(1),"second",Long.valueOf(2),"Test file",
				"txt",Long.valueOf(3),"rw","/home/profi/test");
		MyFile mf2=new MyFile(Long.valueOf(2),"third",Long.valueOf(55),
				"Second test file","txt",Long.valueOf(34),"rw","/home/profi");
		User u=new User(Long.valueOf(6),"Anton","Net parolya");
		Folder fold=new Folder(Long.valueOf(996),"Test",Long.valueOf(0),Long.valueOf(6),"No");
		c.loadXml("/home/profi/file.xml");
		//c.loadXml("/home/profi/user.xml");
		//c.loadXml("/home/profi/folder.xml");

		c.add(mf);
		c.add(mf2);
		c.add(u);
		c.add(fold);
		c.saveXml("/home/profi/newXml");
		//c.list();
		//c.remove(Long.valueOf(1));
		//c.saveXml("/home/profi/newXml1");
	
		//c.loadXml("/home/profi/file.xml");

	}

}
