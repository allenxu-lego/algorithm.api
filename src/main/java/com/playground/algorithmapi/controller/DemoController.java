package com.playground.algorithmapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.playground.algorithmapi.dto.AlgorithmRequest;
import com.playground.algorithmapi.model.ListNode;
import com.playground.algorithmapi.service.IListNodeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
@Validated
@Tag(name = "算法接口", description = "提供算法相关的API")
public class DemoController {

    private final IListNodeService listNodeService;

    public DemoController(IListNodeService listNodeService) {
        this.listNodeService = listNodeService;
    }

    @GetMapping("/invoke")
    @Operation(summary = "执行算法", description = "根据输入参数执行算法")
    public String invokeAlgorithm(
        @Parameter(name = "input", description = "输入值", example = "1")
        @RequestParam(name="input", defaultValue="1") 
        @Min(value = 1, message = "input必须大于0")
        @Max(value = 100, message = "input不能超过100") 
        Integer input, 
        @Parameter(name = "nth", description = "第n个节点", example = "1")
        @RequestParam(name="nth", defaultValue="1")
        @Min(value = 1, message = "nth必须大于0")
        @Max(value = 100, message = "nth不能超过100")
        Integer nth) {


        ListNode head = listNodeService.initListNode(input);

        
        return "Processed input: " + listNodeService.printListNode(head) + "\n Output: " + listNodeService.printListNode(listNodeService.removeNthNode(head, nth));
    }

    /**
     * 使用POST方法和请求体的API，展示@Valid校验
     */
    @PostMapping("/invoke-post")
    @Operation(summary = "通过POST执行算法", description = "使用POST请求体参数执行算法")
    public String invokeAlgorithmPost(@Valid @RequestBody AlgorithmRequest request) {
        ListNode head = listNodeService.initListNode(request.getInput());
        ListNode result = listNodeService.removeNthNode(head, request.getNth());
        
        return "Processed input: " + listNodeService.printListNode(head) + "\n Output: " + listNodeService.printListNode(result);
    }
}