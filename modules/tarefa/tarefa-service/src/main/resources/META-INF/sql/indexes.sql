create index IX_C609014E on ToDoList_Tarefa (groupId);
create index IX_F28B8F4C on ToDoList_Tarefa (tarefaPaiId);
create unique index IX_8ACAA852 on ToDoList_Tarefa (uuid_[$COLUMN_LENGTH:75$], groupId);