package com.system.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.dao.impl.TagDAOImpl;
import com.system.entity.Tag;
import com.system.services.FormatedDate;

@RestController
@ComponentScan(basePackages = { "com.system.dao.impl" })
public class TagController {

	@Autowired
	private TagDAOImpl tags;

	// add tags
	@RequestMapping(value = "/tags")
	public String addTags(@RequestParam Map<String, String> var) throws ParseException {
		Tag tag = null;
		String description = var.get("desc");
		String is_Active = var.get("status");
		String created_On = var.get("created");
		String mappingId = var.get("mappingId");
		boolean check = false;
		if (mappingId.length() != 0) {
			check = tags.isTagsExists(Integer.parseInt(mappingId));
			if (check) {
				tags.addTag(new Tag(Integer.parseInt(mappingId), description, is_Active,
						FormatedDate.dateFormat(created_On)));
				return "Tag successfully added";
			} else {
				return "Mapping id not found";
			}
		} else {
			try {
				tag = new Tag(description, is_Active, FormatedDate.dateFormat(created_On));
			} catch (Exception e) {
				e.printStackTrace();
				return "Something went wrong! Please provides valid parameter";
			}
			tag = tags.addTag(tag);
			tags.updateTagMapping(tag.getTag_Id());
			return "Tag successfully added";
		}
	}

	// Get all tags(Parent and child )
	@RequestMapping(value = "/tags/{tagId}")
	public List<Tag> getTags(@PathVariable("tagId") int tagId) throws Exception {
		return tags.getList(tagId);
	}

}
