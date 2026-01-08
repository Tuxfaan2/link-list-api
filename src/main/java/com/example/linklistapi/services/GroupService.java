package com.example.linklistapi.services;

import com.example.linklistapi.api.GroupApi;
import com.example.linklistapi.model.CreateGroupRequest;
import com.example.linklistapi.model.GroupDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GroupService {
    GroupDto createGroup(CreateGroupRequest createGroupRequest);

    List<GroupDto> getGroups();

    GroupDto joinGroup(Long groupId);
}
