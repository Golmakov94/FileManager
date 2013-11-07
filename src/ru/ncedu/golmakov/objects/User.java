package ru.ncedu.golmakov.objects;

public class User {
	
	public User(Long id, String name, String pathWord) {
		super();
		this.id = id;
		this.name = name;
		this.pathWord = pathWord;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPathWord() {
		return pathWord;
	}
	
	private Long id;
	private String name;
	private String pathWord;

}
