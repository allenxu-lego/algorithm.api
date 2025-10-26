package com.playground.algorithmapi.model;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@Builder
public class TreeNode {
     private int val;
     private TreeNode left;
     private TreeNode right;

}
