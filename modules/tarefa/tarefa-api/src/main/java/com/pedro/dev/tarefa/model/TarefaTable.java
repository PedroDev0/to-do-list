/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.pedro.dev.tarefa.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;ToDoList_Tarefa&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Tarefa
 * @generated
 */
public class TarefaTable extends BaseTable<TarefaTable> {

	public static final TarefaTable INSTANCE = new TarefaTable();

	public final Column<TarefaTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TarefaTable, Long> tarefaId = createColumn(
		"tarefaId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<TarefaTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<TarefaTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<TarefaTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<TarefaTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TarefaTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<TarefaTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<TarefaTable, String> titulo = createColumn(
		"titulo", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TarefaTable, String> descricao = createColumn(
		"descricao", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TarefaTable, String> urlImagem = createColumn(
		"urlImagem", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TarefaTable, Integer> status = createColumn(
		"status", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<TarefaTable, Long> tarefaPaiId = createColumn(
		"tarefaPaiId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<TarefaTable, Long> fileEntryId = createColumn(
		"fileEntryId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private TarefaTable() {
		super("ToDoList_Tarefa", TarefaTable::new);
	}

}