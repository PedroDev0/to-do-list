/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.pedro.dev.tarefa.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.pedro.dev.tarefa.model.Tarefa;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the tarefa service. This utility wraps <code>com.pedro.dev.tarefa.service.persistence.impl.TarefaPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TarefaPersistence
 * @generated
 */
public class TarefaUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Tarefa tarefa) {
		getPersistence().clearCache(tarefa);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Tarefa> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Tarefa> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Tarefa> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Tarefa> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Tarefa> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Tarefa update(Tarefa tarefa) {
		return getPersistence().update(tarefa);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Tarefa update(Tarefa tarefa, ServiceContext serviceContext) {
		return getPersistence().update(tarefa, serviceContext);
	}

	/**
	 * Returns all the tarefas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching tarefas
	 */
	public static List<Tarefa> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the tarefas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TarefaModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of tarefas
	 * @param end the upper bound of the range of tarefas (not inclusive)
	 * @return the range of matching tarefas
	 */
	public static List<Tarefa> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the tarefas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TarefaModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of tarefas
	 * @param end the upper bound of the range of tarefas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tarefas
	 */
	public static List<Tarefa> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Tarefa> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the tarefas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TarefaModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of tarefas
	 * @param end the upper bound of the range of tarefas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tarefas
	 */
	public static List<Tarefa> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Tarefa> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first tarefa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tarefa
	 * @throws NoSuchTarefaException if a matching tarefa could not be found
	 */
	public static Tarefa findByUuid_First(
			String uuid, OrderByComparator<Tarefa> orderByComparator)
		throws com.pedro.dev.tarefa.exception.NoSuchTarefaException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first tarefa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public static Tarefa fetchByUuid_First(
		String uuid, OrderByComparator<Tarefa> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last tarefa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tarefa
	 * @throws NoSuchTarefaException if a matching tarefa could not be found
	 */
	public static Tarefa findByUuid_Last(
			String uuid, OrderByComparator<Tarefa> orderByComparator)
		throws com.pedro.dev.tarefa.exception.NoSuchTarefaException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last tarefa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public static Tarefa fetchByUuid_Last(
		String uuid, OrderByComparator<Tarefa> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the tarefas before and after the current tarefa in the ordered set where uuid = &#63;.
	 *
	 * @param tarefaId the primary key of the current tarefa
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tarefa
	 * @throws NoSuchTarefaException if a tarefa with the primary key could not be found
	 */
	public static Tarefa[] findByUuid_PrevAndNext(
			long tarefaId, String uuid,
			OrderByComparator<Tarefa> orderByComparator)
		throws com.pedro.dev.tarefa.exception.NoSuchTarefaException {

		return getPersistence().findByUuid_PrevAndNext(
			tarefaId, uuid, orderByComparator);
	}

	/**
	 * Removes all the tarefas where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of tarefas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching tarefas
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the tarefa where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchTarefaException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching tarefa
	 * @throws NoSuchTarefaException if a matching tarefa could not be found
	 */
	public static Tarefa findByUUID_G(String uuid, long groupId)
		throws com.pedro.dev.tarefa.exception.NoSuchTarefaException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the tarefa where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public static Tarefa fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the tarefa where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public static Tarefa fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the tarefa where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the tarefa that was removed
	 */
	public static Tarefa removeByUUID_G(String uuid, long groupId)
		throws com.pedro.dev.tarefa.exception.NoSuchTarefaException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of tarefas where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching tarefas
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the tarefas where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching tarefas
	 */
	public static List<Tarefa> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the tarefas where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TarefaModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of tarefas
	 * @param end the upper bound of the range of tarefas (not inclusive)
	 * @return the range of matching tarefas
	 */
	public static List<Tarefa> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the tarefas where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TarefaModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of tarefas
	 * @param end the upper bound of the range of tarefas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tarefas
	 */
	public static List<Tarefa> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Tarefa> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the tarefas where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TarefaModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of tarefas
	 * @param end the upper bound of the range of tarefas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tarefas
	 */
	public static List<Tarefa> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Tarefa> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first tarefa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tarefa
	 * @throws NoSuchTarefaException if a matching tarefa could not be found
	 */
	public static Tarefa findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Tarefa> orderByComparator)
		throws com.pedro.dev.tarefa.exception.NoSuchTarefaException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first tarefa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public static Tarefa fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<Tarefa> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last tarefa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tarefa
	 * @throws NoSuchTarefaException if a matching tarefa could not be found
	 */
	public static Tarefa findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Tarefa> orderByComparator)
		throws com.pedro.dev.tarefa.exception.NoSuchTarefaException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last tarefa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public static Tarefa fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<Tarefa> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the tarefas before and after the current tarefa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param tarefaId the primary key of the current tarefa
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tarefa
	 * @throws NoSuchTarefaException if a tarefa with the primary key could not be found
	 */
	public static Tarefa[] findByUuid_C_PrevAndNext(
			long tarefaId, String uuid, long companyId,
			OrderByComparator<Tarefa> orderByComparator)
		throws com.pedro.dev.tarefa.exception.NoSuchTarefaException {

		return getPersistence().findByUuid_C_PrevAndNext(
			tarefaId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the tarefas where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of tarefas where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching tarefas
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the tarefas where userId = &#63; and groupId = &#63; and tarefaPaiId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param tarefaPaiId the tarefa pai ID
	 * @return the matching tarefas
	 */
	public static List<Tarefa> findBychildren(
		long userId, long groupId, long tarefaPaiId) {

		return getPersistence().findBychildren(userId, groupId, tarefaPaiId);
	}

	/**
	 * Returns a range of all the tarefas where userId = &#63; and groupId = &#63; and tarefaPaiId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TarefaModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param tarefaPaiId the tarefa pai ID
	 * @param start the lower bound of the range of tarefas
	 * @param end the upper bound of the range of tarefas (not inclusive)
	 * @return the range of matching tarefas
	 */
	public static List<Tarefa> findBychildren(
		long userId, long groupId, long tarefaPaiId, int start, int end) {

		return getPersistence().findBychildren(
			userId, groupId, tarefaPaiId, start, end);
	}

	/**
	 * Returns an ordered range of all the tarefas where userId = &#63; and groupId = &#63; and tarefaPaiId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TarefaModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param tarefaPaiId the tarefa pai ID
	 * @param start the lower bound of the range of tarefas
	 * @param end the upper bound of the range of tarefas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tarefas
	 */
	public static List<Tarefa> findBychildren(
		long userId, long groupId, long tarefaPaiId, int start, int end,
		OrderByComparator<Tarefa> orderByComparator) {

		return getPersistence().findBychildren(
			userId, groupId, tarefaPaiId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the tarefas where userId = &#63; and groupId = &#63; and tarefaPaiId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TarefaModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param tarefaPaiId the tarefa pai ID
	 * @param start the lower bound of the range of tarefas
	 * @param end the upper bound of the range of tarefas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tarefas
	 */
	public static List<Tarefa> findBychildren(
		long userId, long groupId, long tarefaPaiId, int start, int end,
		OrderByComparator<Tarefa> orderByComparator, boolean useFinderCache) {

		return getPersistence().findBychildren(
			userId, groupId, tarefaPaiId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first tarefa in the ordered set where userId = &#63; and groupId = &#63; and tarefaPaiId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param tarefaPaiId the tarefa pai ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tarefa
	 * @throws NoSuchTarefaException if a matching tarefa could not be found
	 */
	public static Tarefa findBychildren_First(
			long userId, long groupId, long tarefaPaiId,
			OrderByComparator<Tarefa> orderByComparator)
		throws com.pedro.dev.tarefa.exception.NoSuchTarefaException {

		return getPersistence().findBychildren_First(
			userId, groupId, tarefaPaiId, orderByComparator);
	}

	/**
	 * Returns the first tarefa in the ordered set where userId = &#63; and groupId = &#63; and tarefaPaiId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param tarefaPaiId the tarefa pai ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public static Tarefa fetchBychildren_First(
		long userId, long groupId, long tarefaPaiId,
		OrderByComparator<Tarefa> orderByComparator) {

		return getPersistence().fetchBychildren_First(
			userId, groupId, tarefaPaiId, orderByComparator);
	}

	/**
	 * Returns the last tarefa in the ordered set where userId = &#63; and groupId = &#63; and tarefaPaiId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param tarefaPaiId the tarefa pai ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tarefa
	 * @throws NoSuchTarefaException if a matching tarefa could not be found
	 */
	public static Tarefa findBychildren_Last(
			long userId, long groupId, long tarefaPaiId,
			OrderByComparator<Tarefa> orderByComparator)
		throws com.pedro.dev.tarefa.exception.NoSuchTarefaException {

		return getPersistence().findBychildren_Last(
			userId, groupId, tarefaPaiId, orderByComparator);
	}

	/**
	 * Returns the last tarefa in the ordered set where userId = &#63; and groupId = &#63; and tarefaPaiId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param tarefaPaiId the tarefa pai ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public static Tarefa fetchBychildren_Last(
		long userId, long groupId, long tarefaPaiId,
		OrderByComparator<Tarefa> orderByComparator) {

		return getPersistence().fetchBychildren_Last(
			userId, groupId, tarefaPaiId, orderByComparator);
	}

	/**
	 * Returns the tarefas before and after the current tarefa in the ordered set where userId = &#63; and groupId = &#63; and tarefaPaiId = &#63;.
	 *
	 * @param tarefaId the primary key of the current tarefa
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param tarefaPaiId the tarefa pai ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tarefa
	 * @throws NoSuchTarefaException if a tarefa with the primary key could not be found
	 */
	public static Tarefa[] findBychildren_PrevAndNext(
			long tarefaId, long userId, long groupId, long tarefaPaiId,
			OrderByComparator<Tarefa> orderByComparator)
		throws com.pedro.dev.tarefa.exception.NoSuchTarefaException {

		return getPersistence().findBychildren_PrevAndNext(
			tarefaId, userId, groupId, tarefaPaiId, orderByComparator);
	}

	/**
	 * Removes all the tarefas where userId = &#63; and groupId = &#63; and tarefaPaiId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param tarefaPaiId the tarefa pai ID
	 */
	public static void removeBychildren(
		long userId, long groupId, long tarefaPaiId) {

		getPersistence().removeBychildren(userId, groupId, tarefaPaiId);
	}

	/**
	 * Returns the number of tarefas where userId = &#63; and groupId = &#63; and tarefaPaiId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param tarefaPaiId the tarefa pai ID
	 * @return the number of matching tarefas
	 */
	public static int countBychildren(
		long userId, long groupId, long tarefaPaiId) {

		return getPersistence().countBychildren(userId, groupId, tarefaPaiId);
	}

	/**
	 * Returns all the tarefas where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the matching tarefas
	 */
	public static List<Tarefa> findBygroupIdUser(long userId, long groupId) {
		return getPersistence().findBygroupIdUser(userId, groupId);
	}

	/**
	 * Returns a range of all the tarefas where userId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TarefaModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of tarefas
	 * @param end the upper bound of the range of tarefas (not inclusive)
	 * @return the range of matching tarefas
	 */
	public static List<Tarefa> findBygroupIdUser(
		long userId, long groupId, int start, int end) {

		return getPersistence().findBygroupIdUser(userId, groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the tarefas where userId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TarefaModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of tarefas
	 * @param end the upper bound of the range of tarefas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tarefas
	 */
	public static List<Tarefa> findBygroupIdUser(
		long userId, long groupId, int start, int end,
		OrderByComparator<Tarefa> orderByComparator) {

		return getPersistence().findBygroupIdUser(
			userId, groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the tarefas where userId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TarefaModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of tarefas
	 * @param end the upper bound of the range of tarefas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tarefas
	 */
	public static List<Tarefa> findBygroupIdUser(
		long userId, long groupId, int start, int end,
		OrderByComparator<Tarefa> orderByComparator, boolean useFinderCache) {

		return getPersistence().findBygroupIdUser(
			userId, groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first tarefa in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tarefa
	 * @throws NoSuchTarefaException if a matching tarefa could not be found
	 */
	public static Tarefa findBygroupIdUser_First(
			long userId, long groupId,
			OrderByComparator<Tarefa> orderByComparator)
		throws com.pedro.dev.tarefa.exception.NoSuchTarefaException {

		return getPersistence().findBygroupIdUser_First(
			userId, groupId, orderByComparator);
	}

	/**
	 * Returns the first tarefa in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public static Tarefa fetchBygroupIdUser_First(
		long userId, long groupId,
		OrderByComparator<Tarefa> orderByComparator) {

		return getPersistence().fetchBygroupIdUser_First(
			userId, groupId, orderByComparator);
	}

	/**
	 * Returns the last tarefa in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tarefa
	 * @throws NoSuchTarefaException if a matching tarefa could not be found
	 */
	public static Tarefa findBygroupIdUser_Last(
			long userId, long groupId,
			OrderByComparator<Tarefa> orderByComparator)
		throws com.pedro.dev.tarefa.exception.NoSuchTarefaException {

		return getPersistence().findBygroupIdUser_Last(
			userId, groupId, orderByComparator);
	}

	/**
	 * Returns the last tarefa in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public static Tarefa fetchBygroupIdUser_Last(
		long userId, long groupId,
		OrderByComparator<Tarefa> orderByComparator) {

		return getPersistence().fetchBygroupIdUser_Last(
			userId, groupId, orderByComparator);
	}

	/**
	 * Returns the tarefas before and after the current tarefa in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param tarefaId the primary key of the current tarefa
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tarefa
	 * @throws NoSuchTarefaException if a tarefa with the primary key could not be found
	 */
	public static Tarefa[] findBygroupIdUser_PrevAndNext(
			long tarefaId, long userId, long groupId,
			OrderByComparator<Tarefa> orderByComparator)
		throws com.pedro.dev.tarefa.exception.NoSuchTarefaException {

		return getPersistence().findBygroupIdUser_PrevAndNext(
			tarefaId, userId, groupId, orderByComparator);
	}

	/**
	 * Removes all the tarefas where userId = &#63; and groupId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 */
	public static void removeBygroupIdUser(long userId, long groupId) {
		getPersistence().removeBygroupIdUser(userId, groupId);
	}

	/**
	 * Returns the number of tarefas where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the number of matching tarefas
	 */
	public static int countBygroupIdUser(long userId, long groupId) {
		return getPersistence().countBygroupIdUser(userId, groupId);
	}

	/**
	 * Caches the tarefa in the entity cache if it is enabled.
	 *
	 * @param tarefa the tarefa
	 */
	public static void cacheResult(Tarefa tarefa) {
		getPersistence().cacheResult(tarefa);
	}

	/**
	 * Caches the tarefas in the entity cache if it is enabled.
	 *
	 * @param tarefas the tarefas
	 */
	public static void cacheResult(List<Tarefa> tarefas) {
		getPersistence().cacheResult(tarefas);
	}

	/**
	 * Creates a new tarefa with the primary key. Does not add the tarefa to the database.
	 *
	 * @param tarefaId the primary key for the new tarefa
	 * @return the new tarefa
	 */
	public static Tarefa create(long tarefaId) {
		return getPersistence().create(tarefaId);
	}

	/**
	 * Removes the tarefa with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tarefaId the primary key of the tarefa
	 * @return the tarefa that was removed
	 * @throws NoSuchTarefaException if a tarefa with the primary key could not be found
	 */
	public static Tarefa remove(long tarefaId)
		throws com.pedro.dev.tarefa.exception.NoSuchTarefaException {

		return getPersistence().remove(tarefaId);
	}

	public static Tarefa updateImpl(Tarefa tarefa) {
		return getPersistence().updateImpl(tarefa);
	}

	/**
	 * Returns the tarefa with the primary key or throws a <code>NoSuchTarefaException</code> if it could not be found.
	 *
	 * @param tarefaId the primary key of the tarefa
	 * @return the tarefa
	 * @throws NoSuchTarefaException if a tarefa with the primary key could not be found
	 */
	public static Tarefa findByPrimaryKey(long tarefaId)
		throws com.pedro.dev.tarefa.exception.NoSuchTarefaException {

		return getPersistence().findByPrimaryKey(tarefaId);
	}

	/**
	 * Returns the tarefa with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param tarefaId the primary key of the tarefa
	 * @return the tarefa, or <code>null</code> if a tarefa with the primary key could not be found
	 */
	public static Tarefa fetchByPrimaryKey(long tarefaId) {
		return getPersistence().fetchByPrimaryKey(tarefaId);
	}

	/**
	 * Returns all the tarefas.
	 *
	 * @return the tarefas
	 */
	public static List<Tarefa> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the tarefas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TarefaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tarefas
	 * @param end the upper bound of the range of tarefas (not inclusive)
	 * @return the range of tarefas
	 */
	public static List<Tarefa> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the tarefas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TarefaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tarefas
	 * @param end the upper bound of the range of tarefas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of tarefas
	 */
	public static List<Tarefa> findAll(
		int start, int end, OrderByComparator<Tarefa> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the tarefas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TarefaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tarefas
	 * @param end the upper bound of the range of tarefas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of tarefas
	 */
	public static List<Tarefa> findAll(
		int start, int end, OrderByComparator<Tarefa> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the tarefas from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of tarefas.
	 *
	 * @return the number of tarefas
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TarefaPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(TarefaPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile TarefaPersistence _persistence;

}