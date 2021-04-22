package com.obieliakov.tasksmanager.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/comments")
@SecurityRequirement(name = "identity")
public class CommentController {
}
