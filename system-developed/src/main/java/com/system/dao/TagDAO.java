package com.system.dao;

import java.util.List;

import com.system.entity.Tag;

public interface TagDAO {
	public Tag addTag(Tag tag);

	public Tag getTags(int tag_Id);

	public boolean updateTagMapping(int mapping_Id, int tag_Id);

	public boolean isTagsExists(int tag_Id);

	public List<Tag> getList(int mappingId);
}
