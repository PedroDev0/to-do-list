/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.pedro.dev.tarefa.service;

import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * Provides a wrapper for {@link TarefaLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TarefaLocalService
 * @generated
 */
public class TarefaLocalServiceWrapper
	implements ServiceWrapper<TarefaLocalService>, TarefaLocalService {

	public TarefaLocalServiceWrapper() {
		this(null);
	}

	public TarefaLocalServiceWrapper(TarefaLocalService tarefaLocalService) {
		_tarefaLocalService = tarefaLocalService;
	}

	@Override
	public com.pedro.dev.tarefa.model.Tarefa addTarefa(
			long groupId, String titulo, String descricao, String urlImagem,
			long idPai, long fileEntryId, long userId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tarefaLocalService.addTarefa(
			groupId, titulo, descricao, urlImagem, idPai, fileEntryId, userId,
			serviceContext);
	}

	/**
	 * Adds the tarefa to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TarefaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param tarefa the tarefa
	 * @return the tarefa that was added
	 */
	@Override
	public com.pedro.dev.tarefa.model.Tarefa addTarefa(
		com.pedro.dev.tarefa.model.Tarefa tarefa) {

		return _tarefaLocalService.addTarefa(tarefa);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tarefaLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new tarefa with the primary key. Does not add the tarefa to the database.
	 *
	 * @param tarefaId the primary key for the new tarefa
	 * @return the new tarefa
	 */
	@Override
	public com.pedro.dev.tarefa.model.Tarefa createTarefa(long tarefaId) {
		return _tarefaLocalService.createTarefa(tarefaId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tarefaLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the tarefa with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TarefaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param tarefaId the primary key of the tarefa
	 * @return the tarefa that was removed
	 * @throws PortalException if a tarefa with the primary key could not be found
	 */
	@Override
	public com.pedro.dev.tarefa.model.Tarefa deleteTarefa(long tarefaId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tarefaLocalService.deleteTarefa(tarefaId);
	}

	/**
	 * Deletes the tarefa from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TarefaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param tarefa the tarefa
	 * @return the tarefa that was removed
	 */
	@Override
	public com.pedro.dev.tarefa.model.Tarefa deleteTarefa(
		com.pedro.dev.tarefa.model.Tarefa tarefa) {

		return _tarefaLocalService.deleteTarefa(tarefa);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _tarefaLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _tarefaLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _tarefaLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _tarefaLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.pedro.dev.tarefa.model.impl.TarefaModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _tarefaLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.pedro.dev.tarefa.model.impl.TarefaModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _tarefaLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _tarefaLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _tarefaLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.pedro.dev.tarefa.model.Tarefa fetchTarefa(long tarefaId) {
		return _tarefaLocalService.fetchTarefa(tarefaId);
	}

	/**
	 * Returns the tarefa matching the UUID and group.
	 *
	 * @param uuid the tarefa's UUID
	 * @param groupId the primary key of the group
	 * @return the matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	@Override
	public com.pedro.dev.tarefa.model.Tarefa fetchTarefaByUuidAndGroupId(
		String uuid, long groupId) {

		return _tarefaLocalService.fetchTarefaByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public java.util.List<com.pedro.dev.tarefa.model.Tarefa> findBybyStatus(
		long userId, long groupId, int status) {

		return _tarefaLocalService.findBybyStatus(userId, groupId, status);
	}

	@Override
	public java.util.List<com.pedro.dev.tarefa.model.Tarefa>
		findSubTarefasByStatus(
			long userId, long groupId, int status, long tarefaPaiId) {

		return _tarefaLocalService.findSubTarefasByStatus(
			userId, groupId, status, tarefaPaiId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _tarefaLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _tarefaLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _tarefaLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _tarefaLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tarefaLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.pedro.dev.tarefa.model.Tarefa> getSubTarefas(
		long tarefaPaiId, long groupId, long userId) {

		return _tarefaLocalService.getSubTarefas(tarefaPaiId, groupId, userId);
	}

	@Override
	public java.util.List<com.pedro.dev.tarefa.model.Tarefa>
		getSubTarefasByKeywords(
			long groupId, int start, int end, long userId, long tarefaPaiId,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.pedro.dev.tarefa.model.Tarefa> comparator) {

		return _tarefaLocalService.getSubTarefasByKeywords(
			groupId, start, end, userId, tarefaPaiId, comparator);
	}

	/**
	 * Returns the tarefa with the primary key.
	 *
	 * @param tarefaId the primary key of the tarefa
	 * @return the tarefa
	 * @throws PortalException if a tarefa with the primary key could not be found
	 */
	@Override
	public com.pedro.dev.tarefa.model.Tarefa getTarefa(long tarefaId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tarefaLocalService.getTarefa(tarefaId);
	}

	/**
	 * Returns the tarefa matching the UUID and group.
	 *
	 * @param uuid the tarefa's UUID
	 * @param groupId the primary key of the group
	 * @return the matching tarefa
	 * @throws PortalException if a matching tarefa could not be found
	 */
	@Override
	public com.pedro.dev.tarefa.model.Tarefa getTarefaByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tarefaLocalService.getTarefaByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the tarefas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.pedro.dev.tarefa.model.impl.TarefaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tarefas
	 * @param end the upper bound of the range of tarefas (not inclusive)
	 * @return the range of tarefas
	 */
	@Override
	public java.util.List<com.pedro.dev.tarefa.model.Tarefa> getTarefas(
		int start, int end) {

		return _tarefaLocalService.getTarefas(start, end);
	}

	@Override
	public java.util.List<com.pedro.dev.tarefa.model.Tarefa>
		getTarefasByGroupId(long groupId, long userId) {

		return _tarefaLocalService.getTarefasByGroupId(groupId, userId);
	}

	@Override
	public java.util.List<com.pedro.dev.tarefa.model.Tarefa>
		getTarefasByGroupIdOrdenado(
			long groupId, long userId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.pedro.dev.tarefa.model.Tarefa> comparator) {

		return _tarefaLocalService.getTarefasByGroupIdOrdenado(
			groupId, userId, start, end, comparator);
	}

	@Override
	public java.util.List<com.pedro.dev.tarefa.model.Tarefa>
		getTarefasByKeywords(
			long groupId, String keywords, int start, int end, long userId,
			long tarefaPai,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.pedro.dev.tarefa.model.Tarefa> comparator) {

		return _tarefaLocalService.getTarefasByKeywords(
			groupId, keywords, start, end, userId, tarefaPai, comparator);
	}

	/**
	 * Returns all the tarefas matching the UUID and company.
	 *
	 * @param uuid the UUID of the tarefas
	 * @param companyId the primary key of the company
	 * @return the matching tarefas, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.pedro.dev.tarefa.model.Tarefa>
		getTarefasByUuidAndCompanyId(String uuid, long companyId) {

		return _tarefaLocalService.getTarefasByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of tarefas matching the UUID and company.
	 *
	 * @param uuid the UUID of the tarefas
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of tarefas
	 * @param end the upper bound of the range of tarefas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching tarefas, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.pedro.dev.tarefa.model.Tarefa>
		getTarefasByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.pedro.dev.tarefa.model.Tarefa> orderByComparator) {

		return _tarefaLocalService.getTarefasByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of tarefas.
	 *
	 * @return the number of tarefas
	 */
	@Override
	public int getTarefasCount() {
		return _tarefaLocalService.getTarefasCount();
	}

	@Override
	public java.util.List<com.pedro.dev.tarefa.model.Tarefa>
		getTarefasGroupPaginator(
			long groupId, long userId, int start, int end) {

		return _tarefaLocalService.getTarefasGroupPaginator(
			groupId, userId, start, end);
	}

	@Override
	public com.pedro.dev.tarefa.model.Tarefa updateTarefa(
			long id, String titulo, String descricao, String urlImagem,
			long fileEntryId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tarefaLocalService.updateTarefa(
			id, titulo, descricao, urlImagem, fileEntryId, serviceContext);
	}

	/**
	 * Updates the tarefa in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TarefaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param tarefa the tarefa
	 * @return the tarefa that was updated
	 */
	@Override
	public com.pedro.dev.tarefa.model.Tarefa updateTarefa(
		com.pedro.dev.tarefa.model.Tarefa tarefa) {

		return _tarefaLocalService.updateTarefa(tarefa);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _tarefaLocalService.getBasePersistence();
	}

	@Override
	public TarefaLocalService getWrappedService() {
		return _tarefaLocalService;
	}

	@Override
	public void setWrappedService(TarefaLocalService tarefaLocalService) {
		_tarefaLocalService = tarefaLocalService;
	}

	private TarefaLocalService _tarefaLocalService;

}