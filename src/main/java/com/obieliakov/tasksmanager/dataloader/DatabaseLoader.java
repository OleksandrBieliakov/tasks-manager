package com.obieliakov.tasksmanager.dataloader;

import com.obieliakov.tasksmanager.dataloader.factory.*;
import com.obieliakov.tasksmanager.model.*;
import com.obieliakov.tasksmanager.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final AppUserFactory appUserFactory;
    private final GroupFactory groupFactory;
    private final RoleFactory roleFactory;
    private final GroupMembershipFactory groupMembershipFactory;
    private final TaskFactory taskFactory;
    private final AssignmentFactory assignmentFactory;
    private final StatusUpdateFactory statusUpdateFactory;
    private final GroupInviteFactory groupInviteFactory;
    private final CommentFactory commentFactory;

    private final AppUserRepository appUserRepository;
    private final GroupRepository groupRepository;
    private final RoleRepository roleRepository;
    private final GroupMembershipRepository groupMembershipRepository;
    private final TaskRepository taskRepository;
    private final AssignmentRepository assignmentRepository;
    private final StatusUpdateRepository statusUpdateRepository;
    private final GroupInviteRepository groupInviteRepository;
    private final CommentRepository commentRepository;

    public DatabaseLoader(AppUserFactory appUserFactory, GroupFactory groupFactory, RoleFactory roleFactory, GroupMembershipFactory groupMembershipFactory, TaskFactory taskFactory, AssignmentFactory assignmentFactory, StatusUpdateFactory statusUpdateFactory, GroupInviteFactory groupInviteFactory, CommentFactory commentFactory, AppUserRepository appUserRepository, GroupRepository groupRepository, RoleRepository roleRepository, GroupMembershipRepository groupMembershipRepository, TaskRepository taskRepository, StatusUpdateRepository statusUpdateRepository, AssignmentRepository assignmentRepository, GroupInviteRepository groupInviteRepository, CommentRepository commentRepository) {
        this.appUserFactory = appUserFactory;
        this.groupFactory = groupFactory;
        this.roleFactory = roleFactory;
        this.groupMembershipFactory = groupMembershipFactory;
        this.taskFactory = taskFactory;
        this.assignmentFactory = assignmentFactory;
        this.statusUpdateFactory = statusUpdateFactory;
        this.groupInviteFactory = groupInviteFactory;
        this.commentFactory = commentFactory;
        this.appUserRepository = appUserRepository;
        this.groupRepository = groupRepository;
        this.roleRepository = roleRepository;
        this.groupMembershipRepository = groupMembershipRepository;
        this.taskRepository = taskRepository;
        this.statusUpdateRepository = statusUpdateRepository;
        this.assignmentRepository = assignmentRepository;
        this.groupInviteRepository = groupInviteRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        AppUser appUser1 = appUserFactory.generate();
        AppUser appUser2 = appUserFactory.generate();
        AppUser appUser3 = appUserFactory.generate();
        AppUser appUser4 = appUserFactory.generate();
        AppUser appUser5 = appUserFactory.generate();
        AppUser appUser6 = appUserFactory.generate();

        appUserRepository.save(appUser1);
        appUserRepository.save(appUser2);
        appUserRepository.save(appUser3);
        appUserRepository.save(appUser4);
        appUserRepository.save(appUser5);
        appUserRepository.save(appUser6);


        Group group1 = groupFactory.generate();
        Group group2 = groupFactory.generate();

        groupRepository.save(group1);
        groupRepository.save(group2);


        Role group1Role1 = roleFactory.generateAndInit(group1);
        Role group1Role2 = roleFactory.generateAndInit(group1);
        Role group1Role3 = roleFactory.generateAndInit(group1);

        roleRepository.save(group1Role1);
        roleRepository.save(group1Role2);
        roleRepository.save(group1Role3);


        GroupMembership group1AppUser1 = groupMembershipFactory.generateAndInit(group1, appUser1, group1Role1);
        GroupMembership group1AppUser2 = groupMembershipFactory.generateAndInit(group1, appUser2, group1Role1);
        GroupMembership group1AppUser3 = groupMembershipFactory.generateAndInit(group1, appUser3, group1Role2);
        GroupMembership group1AppUser4 = groupMembershipFactory.generateAndInit(group1, appUser4);

        GroupMembership group2AppUser3 = groupMembershipFactory.generateAndInit(group2, appUser3);
        GroupMembership group2AppUser4 = groupMembershipFactory.generateAndInit(group2, appUser4);
        GroupMembership group2AppUser5 = groupMembershipFactory.generateAndInit(group2, appUser5);

        groupMembershipRepository.save(group1AppUser1);
        groupMembershipRepository.save(group1AppUser2);
        groupMembershipRepository.save(group1AppUser3);
        groupMembershipRepository.save(group1AppUser4);

        groupMembershipRepository.save(group2AppUser3);
        groupMembershipRepository.save(group2AppUser4);
        groupMembershipRepository.save(group2AppUser5);


        Task task1Group1AppUser1Pending = taskFactory.generateAndInit(group1, appUser1, TaskStatus.PENDING);
        Task task2Group1AppUser2Pending = taskFactory.generateAndInit(group1, appUser2, TaskStatus.PENDING);
        Task task3Group1AppUser3Pending = taskFactory.generateAndInit(group1, appUser3, TaskStatus.PENDING);
        Task task4Group1AppUser1InProgress = taskFactory.generateAndInit(group1, appUser1, TaskStatus.IN_PROGRESS);
        Task task5Group1AppUser2InProgress = taskFactory.generateAndInit(group1, appUser2, TaskStatus.IN_PROGRESS);
        Task task6Group1AppUser3Done = taskFactory.generateAndInit(group1, appUser3, TaskStatus.DONE);
        Task task7Group1AppUser2Archived = taskFactory.generateAndInit(group1, appUser2, TaskStatus.ARCHIVED);
        Task task8Group1AppUser3Archived = taskFactory.generateAndInit(group1, appUser3, TaskStatus.ARCHIVED);

        Task task9Group2AppUser3InPending = taskFactory.generateAndInit(group2, appUser3, TaskStatus.PENDING);
        Task task10Group2AppUser3InPending = taskFactory.generateAndInit(group2, appUser3, TaskStatus.PENDING);
        Task task11Group2AppUser5InPending = taskFactory.generateAndInit(group2, appUser5, TaskStatus.PENDING);

        taskRepository.save(task1Group1AppUser1Pending);
        taskRepository.save(task2Group1AppUser2Pending);
        taskRepository.save(task3Group1AppUser3Pending);
        taskRepository.save(task4Group1AppUser1InProgress);
        taskRepository.save(task5Group1AppUser2InProgress);
        taskRepository.save(task6Group1AppUser3Done);
        taskRepository.save(task7Group1AppUser2Archived);
        taskRepository.save(task8Group1AppUser3Archived);

        taskRepository.save(task9Group2AppUser3InPending);
        taskRepository.save(task10Group2AppUser3InPending);
        taskRepository.save(task11Group2AppUser5InPending);


        Assignment assignment1Task2AppUser2ToAppUser2 = assignmentFactory.generateAndInit(task2Group1AppUser2Pending, appUser2, appUser2);
        Assignment assignment2Task4AppUser1ToAppUser1 = assignmentFactory.generateAndInit(task4Group1AppUser1InProgress, appUser1, appUser1);
        Assignment assignment3Task4AppUser1ToAppUser2 = assignmentFactory.generateAndInit(task4Group1AppUser1InProgress, appUser1, appUser2);
        Assignment assignment3Task4AppUser1ToAppUser3 = assignmentFactory.generateAndInit(task4Group1AppUser1InProgress, appUser1, appUser3);
        Assignment assignment5Task5AppUser3ToAppUser2 = assignmentFactory.generateAndInit(task5Group1AppUser2InProgress, appUser3, appUser2);
        Assignment assignment6Task6AppUser2ToAppUser1 = assignmentFactory.generateAndInit(task6Group1AppUser3Done, appUser2, appUser1);
        Assignment assignment7Task6AppUser2ToAppUser3 = assignmentFactory.generateAndInit(task6Group1AppUser3Done, appUser2, appUser3);
        Assignment assignment8Task7AppUser2ToAppUser4 = assignmentFactory.generateAndInit(task7Group1AppUser2Archived, appUser2, appUser4);

        assignmentRepository.save(assignment1Task2AppUser2ToAppUser2);
        assignmentRepository.save(assignment2Task4AppUser1ToAppUser1);
        assignmentRepository.save(assignment3Task4AppUser1ToAppUser2);
        assignmentRepository.save(assignment3Task4AppUser1ToAppUser3);
        assignmentRepository.save(assignment5Task5AppUser3ToAppUser2);
        assignmentRepository.save(assignment6Task6AppUser2ToAppUser1);
        assignmentRepository.save(assignment7Task6AppUser2ToAppUser3);
        assignmentRepository.save(assignment8Task7AppUser2ToAppUser4);


        StatusUpdate task4InProgress = statusUpdateFactory.generateAndInit(task4Group1AppUser1InProgress, appUser1, TaskStatus.IN_PROGRESS);

        StatusUpdate task5InProgress = statusUpdateFactory.generateAndInit(task5Group1AppUser2InProgress, appUser2, TaskStatus.IN_PROGRESS);
        StatusUpdate task5InArchived = statusUpdateFactory.generateAndInit(task5Group1AppUser2InProgress, appUser2, TaskStatus.ARCHIVED);
        StatusUpdate task5BackInProgress = statusUpdateFactory.generateAndInit(task5Group1AppUser2InProgress, appUser3, TaskStatus.IN_PROGRESS);

        StatusUpdate task6InProgress = statusUpdateFactory.generateAndInit(task6Group1AppUser3Done, appUser2, TaskStatus.IN_PROGRESS);
        StatusUpdate task6Done = statusUpdateFactory.generateAndInit(task6Group1AppUser3Done, appUser3, TaskStatus.DONE);

        StatusUpdate task7InProgress = statusUpdateFactory.generateAndInit(task7Group1AppUser2Archived, appUser4, TaskStatus.IN_PROGRESS);
        StatusUpdate task7Done = statusUpdateFactory.generateAndInit(task7Group1AppUser2Archived, appUser4, TaskStatus.DONE);
        StatusUpdate task7Archived = statusUpdateFactory.generateAndInit(task7Group1AppUser2Archived, appUser2, TaskStatus.ARCHIVED);

        StatusUpdate task8Archived = statusUpdateFactory.generateAndInit(task8Group1AppUser3Archived, appUser1, TaskStatus.ARCHIVED);

        statusUpdateRepository.save(task4InProgress);
        statusUpdateRepository.save(task5InProgress);
        statusUpdateRepository.save(task5InArchived);
        statusUpdateRepository.save(task5BackInProgress);
        statusUpdateRepository.save(task6InProgress);
        statusUpdateRepository.save(task6Done);
        statusUpdateRepository.save(task7InProgress);
        statusUpdateRepository.save(task7Done);
        statusUpdateRepository.save(task7Archived);
        statusUpdateRepository.save(task8Archived);


        GroupInvite group1ByAppUser1ToAppUser6 = groupInviteFactory.generateAndInit(group1, appUser1, appUser6);

        groupInviteRepository.save(group1ByAppUser1ToAppUser6);


        Comment comment1Group1Task6AddedByAppUser2 = commentFactory.generateAndInit(task6Group1AppUser3Done, appUser2);
        Comment comment2Group1Task6AddedByAppUser2 = commentFactory.generateAndInit(task6Group1AppUser3Done, appUser2);
        Comment comment3Group1Task6AddedByAppUser3 = commentFactory.generateAndInit(task6Group1AppUser3Done, appUser3);

        commentRepository.save(comment1Group1Task6AddedByAppUser2);
        commentRepository.save(comment2Group1Task6AddedByAppUser2);
        commentRepository.save(comment3Group1Task6AddedByAppUser3);
    }
}