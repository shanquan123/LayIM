package com.pers.yefei.LayIM.pojo;

public class SimpleComId implements CombinedId {

	long primaryId;
	String uuid;
	
	
	public SimpleComId(String comID){
		this.setComId(comID);
	}
	
	public SimpleComId(Long primaryId ,String uuid){
		this.primaryId = primaryId;
		this.uuid = uuid;
	}
	

	@Override
	public String getComId() {
		return String.format("%s~%s", primaryId, uuid);
	}

	@Override
	public void setComId(String id) {
		String[] ids = id.split("~");
		this.primaryId = Long.parseLong(ids[0]);
		this.uuid = ids[1];
	}
	
	public long getPrimaryId() {
		return primaryId;
	}

	public void setPrimaryId(long primaryId) {
		this.primaryId = primaryId;
	}

	public String getUUID() {
		return uuid;
	}

	public void setUUID(String uuid) {
		this.uuid = uuid;
	}

	public String toString(){
		return primaryId+"~"+uuid;
	}
}
