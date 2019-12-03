INSERT into db_issuetracker.role(description,name) values('Developer','Dev')
INSERT into db_issuetracker.role(description,name) values('Project Manager','MGR')

INSERT INTO `db_issuetracker`.`project` (`description`, `key_id`, `name`) VALUES ('stock market ', 'sto', 'stock market system');
INSERT INTO `db_issuetracker`.`project` (`description`, `key_id`, `name`) VALUES ('student system', 'stu', 'student system');

INSERT INTO `db_issuetracker`.`user` (`email`, `firstname`, `last_name`, `middle_name`) VALUES ( 'rob@gmail.com', 'rob', 'stark', '');
INSERT INTO `db_issuetracker`.`user` (`email`, `firstname`, `last_name`, `middle_name`) VALUES ('ryan@gmail.com', 'ryan', 'howard', 'gary');

INSERT INTO `db_issuetracker`.`project_user` (`project_id`, `user_id`) VALUES ('1', '1');
INSERT INTO `db_issuetracker`.`project_user` (`project_id`, `user_id`) VALUES ('2', '2');

INSERT INTO `db_issuetracker`.`user_role_mapping` (`project_id`, `role_id`, `user_id`) VALUES ('1', '1', '1');
INSERT INTO `db_issuetracker`.`user_role_mapping` (`project_id`, `role_id`, `user_id`) VALUES ('2', '1', '2');

INSERT INTO `db_issuetracker`.`issue` (`created_date`, `description`, `due_date`, `issue_type`, `last_modified_date`, `name`, `priority_level`, `status`, `assignee_id`, `creator_id`, `project_id`) VALUES ('2019-12-12', 'user api error', '2019-12-29', 'Bug', '2019-11-11', 'no user API', 'HIGH', 'NEW', '1', '1', '1');


INSERT INTO `db_issuetracker`.`user` (`email`, `firstname`, `last_name`, `middle_name`) VALUES ('mike@gmail.com', 'mike', 'scott', 'bob');
INSERT INTO `db_issuetracker`.`project_user` (`project_id`, `user_id`) VALUES ('1', '3');
INSERT INTO `db_issuetracker`.`user_role_mapping` (`project_id`, `role_id`, `user_id`) VALUES ('1', '1', '3');


INSERT INTO project(name, key_id, description) VALUES('Issue Tracking', 1, 'Tracking different issues in a project');
INSERT INTO project(name, key_id, description) VALUES('Library Management', 2, 'Manage the book lending services');
INSERT INTO project(name, key_id, description) VALUES('Code Duplication', 3, 'Find out the type of code duplication in a project');
INSERT INTO project(name, key_id, description) VALUES('Property Management', 4, 'List and sell properties');

INSERT INTO role(name,description) VALUES('Project Manager','Creates and Manages Projects for a team');
INSERT INTO role(name,description) VALUES('Developer','Solves issues created in a project');
INSERT INTO role(name,description) VALUES('Tester','Verifies the solution made by the developer');
INSERT INTO role(name,description) VALUES('Business Analyst','Creates issues in a Project');

INSERT INTO user(email, firstname, lastname) VALUES ('pratistha.shrestha@email.com','Pratistha','Shrestha');
INSERT INTO user(email, firstname, lastname) VALUES ('sumit.shrestha@email.com','Sumit','Shrestha');



