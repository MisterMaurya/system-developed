package com.system.dao;

import com.system.entity.Tag;

public interface TagDAO {
	public boolean addTag(Tag tag);

	public Tag getTags(int tag_Id);

}
