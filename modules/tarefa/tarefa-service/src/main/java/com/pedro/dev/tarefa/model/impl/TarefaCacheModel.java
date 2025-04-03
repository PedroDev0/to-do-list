/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.pedro.dev.tarefa.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.pedro.dev.tarefa.model.Tarefa;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Tarefa in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TarefaCacheModel implements CacheModel<Tarefa>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TarefaCacheModel)) {
			return false;
		}

		TarefaCacheModel tarefaCacheModel = (TarefaCacheModel)object;

		if (tarefaId == tarefaCacheModel.tarefaId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, tarefaId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", tarefaId=");
		sb.append(tarefaId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", titulo=");
		sb.append(titulo);
		sb.append(", descricao=");
		sb.append(descricao);
		sb.append(", urlImagem=");
		sb.append(urlImagem);
		sb.append(", status=");
		sb.append(status);
		sb.append(", tarefaPaiId=");
		sb.append(tarefaPaiId);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Tarefa toEntityModel() {
		TarefaImpl tarefaImpl = new TarefaImpl();

		if (uuid == null) {
			tarefaImpl.setUuid("");
		}
		else {
			tarefaImpl.setUuid(uuid);
		}

		tarefaImpl.setTarefaId(tarefaId);
		tarefaImpl.setGroupId(groupId);
		tarefaImpl.setCompanyId(companyId);
		tarefaImpl.setUserId(userId);

		if (userName == null) {
			tarefaImpl.setUserName("");
		}
		else {
			tarefaImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			tarefaImpl.setCreateDate(null);
		}
		else {
			tarefaImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			tarefaImpl.setModifiedDate(null);
		}
		else {
			tarefaImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (titulo == null) {
			tarefaImpl.setTitulo("");
		}
		else {
			tarefaImpl.setTitulo(titulo);
		}

		if (descricao == null) {
			tarefaImpl.setDescricao("");
		}
		else {
			tarefaImpl.setDescricao(descricao);
		}

		if (urlImagem == null) {
			tarefaImpl.setUrlImagem("");
		}
		else {
			tarefaImpl.setUrlImagem(urlImagem);
		}

		tarefaImpl.setStatus(status);
		tarefaImpl.setTarefaPaiId(tarefaPaiId);
		tarefaImpl.setFileEntryId(fileEntryId);

		tarefaImpl.resetOriginalValues();

		return tarefaImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		tarefaId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		titulo = objectInput.readUTF();
		descricao = objectInput.readUTF();
		urlImagem = objectInput.readUTF();

		status = objectInput.readInt();

		tarefaPaiId = objectInput.readLong();

		fileEntryId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(tarefaId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (titulo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(titulo);
		}

		if (descricao == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(descricao);
		}

		if (urlImagem == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(urlImagem);
		}

		objectOutput.writeInt(status);

		objectOutput.writeLong(tarefaPaiId);

		objectOutput.writeLong(fileEntryId);
	}

	public String uuid;
	public long tarefaId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String titulo;
	public String descricao;
	public String urlImagem;
	public int status;
	public long tarefaPaiId;
	public long fileEntryId;

}