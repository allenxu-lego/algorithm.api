package com.playground.algorithmapi.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class ListNode {
    int val;
    ListNode next;
    
}