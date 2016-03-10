package com.adaptiv.humanresourcemanagement.mapper;

import java.util.List;

public interface Mapper<Entity, Dto> {

	public Entity fromDtoToEntity(Dto dto);

	public Dto fromEntityToDto(Entity entity);

	public List<Entity> fromDtoToEntity(List<Dto> dtos);

	public List<Dto> fromEntityToDto(List<Entity> entities);

}
