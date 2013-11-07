package ru.ncedu.golmakov.objects;

public class MyFile {
	
	public MyFile(Long id, String name, Long parentId, String description,
			String extension, Long creatorId, String rights, String path) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.description = description;
		this.extension = extension;
		this.creatorId = creatorId;
		this.rights = rights;
		this.path = path;
	}

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Long getParentId() {
		return parentId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getExtension() {
		return extension;
	}
	
	public Long getCreatorId() {
		return creatorId;
	}
	
	public String getRights() {
		return rights;
	}
	
	public String getPath() {
		return path;
	}
	
	private Long id;
	private String name;
	private Long parentId;
	private String description;
	private String extension;
	private Long creatorId;
	private String rights;
	private String path;
	
	
}
