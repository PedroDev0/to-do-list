create table ToDoList_Tarefa (
	uuid_ VARCHAR(75) null,
	tarefaId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	titulo VARCHAR(75) null,
	descricao VARCHAR(2000) null,
	urlImagem TEXT null,
	status INTEGER,
	tarefaPaiId LONG,
	fileEntryId LONG
);