package com.gentleninja.dto;


import lombok.Data;
import java.util.List;

@Data
public class DeveloperAssignmentRequest {
    private List<Long> developerIds;
}
