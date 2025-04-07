create index IX_944D626E on ToDoList_Tarefa (groupId, userId, status);
create index IX_51A8C620 on ToDoList_Tarefa (groupId, userId, tarefaPaiId);
create unique index IX_5883B4A6 on ToDoList_Tarefa (groupId, uuid_[$COLUMN_LENGTH:75$]);
create index IX_AE6A8F58 on ToDoList_Tarefa (uuid_[$COLUMN_LENGTH:75$]);