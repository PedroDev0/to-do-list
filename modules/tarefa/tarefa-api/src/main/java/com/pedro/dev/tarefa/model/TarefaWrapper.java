/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.pedro.dev.tarefa.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Tarefa}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Tarefa
 * @generated
 */
public class TarefaWrapper
	extends BaseModelWrapper<Tarefa> implements ModelWrapper<Tarefa>, Tarefa {

	public TarefaWrapper(Tarefa tarefa) {
		super(tarefa);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("tarefaId", getTarefaId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("titulo", getTitulo());
		attributes.put("descricao", getDescricao());
		attributes.put("urlImagem", getUrlImagem());
		attributes.put("status", getStatus());
		attributes.put("tarefaPaiId", getTarefaPaiId());
		attributes.put("fileEntryId", getFileEntryId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long tarefaId = (Long)attributes.get("tarefaId");

		if (tarefaId != null) {
			setTarefaId(tarefaId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String titulo = (String)attributes.get("titulo");

		if (titulo != null) {
			setTitulo(titulo);
		}

		String descricao = (String)attributes.get("descricao");

		if (descricao != null) {
			setDescricao(descricao);
		}

		String urlImagem = (String)attributes.get("urlImagem");

		if (urlImagem != null) {
			setUrlImagem(urlImagem);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long tarefaPaiId = (Long)attributes.get("tarefaPaiId");

		if (tarefaPaiId != null) {
			setTarefaPaiId(tarefaPaiId);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}
	}

	@Override
	public Tarefa cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the company ID of this tarefa.
	 *
	 * @return the company ID of this tarefa
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this tarefa.
	 *
	 * @return the create date of this tarefa
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the descricao of this tarefa.
	 *
	 * @return the descricao of this tarefa
	 */
	@Override
	public String getDescricao() {
		return model.getDescricao();
	}

	/**
	 * Returns the file entry ID of this tarefa.
	 *
	 * @return the file entry ID of this tarefa
	 */
	@Override
	public long getFileEntryId() {
		return model.getFileEntryId();
	}

	/**
	 * Returns the group ID of this tarefa.
	 *
	 * @return the group ID of this tarefa
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this tarefa.
	 *
	 * @return the modified date of this tarefa
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this tarefa.
	 *
	 * @return the primary key of this tarefa
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the status of this tarefa.
	 *
	 * @return the status of this tarefa
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the tarefa ID of this tarefa.
	 *
	 * @return the tarefa ID of this tarefa
	 */
	@Override
	public long getTarefaId() {
		return model.getTarefaId();
	}

	/**
	 * Returns the tarefa pai ID of this tarefa.
	 *
	 * @return the tarefa pai ID of this tarefa
	 */
	@Override
	public long getTarefaPaiId() {
		return model.getTarefaPaiId();
	}

	/**
	 * Returns the titulo of this tarefa.
	 *
	 * @return the titulo of this tarefa
	 */
	@Override
	public String getTitulo() {
		return model.getTitulo();
	}

	/**
	 * Returns the url imagem of this tarefa.
	 *
	 * @return the url imagem of this tarefa
	 */
	@Override
	public String getUrlImagem() {
		return model.getUrlImagem();
	}

	/**
	 * Returns the user ID of this tarefa.
	 *
	 * @return the user ID of this tarefa
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this tarefa.
	 *
	 * @return the user name of this tarefa
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this tarefa.
	 *
	 * @return the user uuid of this tarefa
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this tarefa.
	 *
	 * @return the uuid of this tarefa
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this tarefa.
	 *
	 * @param companyId the company ID of this tarefa
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this tarefa.
	 *
	 * @param createDate the create date of this tarefa
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the descricao of this tarefa.
	 *
	 * @param descricao the descricao of this tarefa
	 */
	@Override
	public void setDescricao(String descricao) {
		model.setDescricao(descricao);
	}

	/**
	 * Sets the file entry ID of this tarefa.
	 *
	 * @param fileEntryId the file entry ID of this tarefa
	 */
	@Override
	public void setFileEntryId(long fileEntryId) {
		model.setFileEntryId(fileEntryId);
	}

	/**
	 * Sets the group ID of this tarefa.
	 *
	 * @param groupId the group ID of this tarefa
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this tarefa.
	 *
	 * @param modifiedDate the modified date of this tarefa
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this tarefa.
	 *
	 * @param primaryKey the primary key of this tarefa
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the status of this tarefa.
	 *
	 * @param status the status of this tarefa
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the tarefa ID of this tarefa.
	 *
	 * @param tarefaId the tarefa ID of this tarefa
	 */
	@Override
	public void setTarefaId(long tarefaId) {
		model.setTarefaId(tarefaId);
	}

	/**
	 * Sets the tarefa pai ID of this tarefa.
	 *
	 * @param tarefaPaiId the tarefa pai ID of this tarefa
	 */
	@Override
	public void setTarefaPaiId(long tarefaPaiId) {
		model.setTarefaPaiId(tarefaPaiId);
	}

	/**
	 * Sets the titulo of this tarefa.
	 *
	 * @param titulo the titulo of this tarefa
	 */
	@Override
	public void setTitulo(String titulo) {
		model.setTitulo(titulo);
	}

	/**
	 * Sets the url imagem of this tarefa.
	 *
	 * @param urlImagem the url imagem of this tarefa
	 */
	@Override
	public void setUrlImagem(String urlImagem) {
		model.setUrlImagem(urlImagem);
	}

	/**
	 * Sets the user ID of this tarefa.
	 *
	 * @param userId the user ID of this tarefa
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this tarefa.
	 *
	 * @param userName the user name of this tarefa
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this tarefa.
	 *
	 * @param userUuid the user uuid of this tarefa
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this tarefa.
	 *
	 * @param uuid the uuid of this tarefa
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected TarefaWrapper wrap(Tarefa tarefa) {
		return new TarefaWrapper(tarefa);
	}

}