package ru.ncedu.golmakov.objects;

public class Folder {
	
	public Folder(Long id, String name, Long parentId, Long creatorId, String rights) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.creatorId = creatorId;
		this.rights = rights;
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
	public Long getCreatorId() {
		return creatorId;
	}
	public String getRights() {
		return rights;
	}

	private Long id;
	private String name;
	private Long parentId;
	private Long creatorId;
	private String rights;
	
}
