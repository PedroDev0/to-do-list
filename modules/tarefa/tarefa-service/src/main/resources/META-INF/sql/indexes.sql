create index IX_51A8C620 on ToDoList_Tarefa (groupId, userId, tarefaPaiId);
create unique index IX_8ACAA852 on ToDoList_Tarefa (uuid_[$COLUMN_LENGTH:75$], groupId);