package com.example.linklistapi.services.impls;

import com.example.linklistapi.api.GroupApi;
import com.example.linklistapi.model.CreateGroupRequest;
import com.example.linklistapi.model.GroupDto;
import com.example.linklistapi.models.Group;
import com.example.linklistapi.repositories.GroupRepository;
import com.example.linklistapi.services.GroupService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public GroupDto createGroup(CreateGroupRequest createGroupRequest) {
        Group group = fromRequest(createGroupRequest);
        Group savedGroup = groupRepository.save(group);
        return toGroupDto(savedGroup);
    }

    @Override
    public List<GroupDto> getGroups() {
        List<Group> groups = new ArrayList<>();
        groupRepository.findAll().forEach(groups::add);
        return groups.stream().map(this::toGroupDto).toList();
    }

    @Override
    public GroupDto joinGroup(Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow();
        List<String> users = group.getUsers();
        users.add(getCurrentUserId());
        group.setUsers(users);
        Group savedGroup = groupRepository.save(group);
        return toGroupDto(savedGroup);
    }

    private GroupDto toGroupDto(Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setDescription(Optional.ofNullable(group.getDescription()));
        groupDto.setName(Optional.ofNullable(group.getName()));
        groupDto.setId(Optional.ofNullable(group.getId()));
        groupDto.setUsers(group.getUsers());
        return groupDto;
    }

    private Group fromRequest(CreateGroupRequest createGroupRequest) {
        Group group = new Group();
        group.setDescription(createGroupRequest.getDescription().orElse(null));
        group.setName(createGroupRequest.getDescription().orElse(null));
        group.setUsers(createGroupRequest.getUsers());
        return group;
    }

    private static String getCurrentUserId() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Map<String, String> mappedAuthorities = (HashMap<String, String>) authorities;
        return mappedAuthorities.get("sub");
    }
}
