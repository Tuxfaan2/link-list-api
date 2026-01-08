package com.example.linklistapi.controller;

import com.example.linklistapi.api.GroupApi;
import com.example.linklistapi.model.CreateGroupRequest;
import com.example.linklistapi.model.GroupDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

@Controller
public class GroupController implements GroupApi {

    @Override
    public ResponseEntity<GroupDto> createGroup(CreateGroupRequest createGroupRequest) {
        return GroupApi.super.createGroup(createGroupRequest);
    }

    @Override
    public ResponseEntity<List<GroupDto>> getGroups() {
        return GroupApi.super.getGroups();
    }

    @Override
    public ResponseEntity<GroupDto> joinGroup(Long groupId) {
        return GroupApi.super.joinGroup(groupId);
    }
}
