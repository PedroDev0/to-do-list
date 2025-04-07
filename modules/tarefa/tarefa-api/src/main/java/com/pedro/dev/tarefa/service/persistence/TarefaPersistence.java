/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.pedro.dev.tarefa.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.pedro.dev.tarefa.exception.NoSuchTarefaException;
import com.pedro.dev.tarefa.model.Tarefa;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the tarefa service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TarefaUtil
 * @generated
 */
@ProviderType
public interface TarefaPersistence extends BasePersistence<Tarefa> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TarefaUtil} to access the tarefa persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the tarefas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching tarefas
	 */
	public java.util.List<Tarefa> findByUuid(String uuid);

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
	public java.util.List<Tarefa> findByUuid(String uuid, int start, int end);

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
	public java.util.List<Tarefa> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator);

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
	public java.util.List<Tarefa> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first tarefa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tarefa
	 * @throws NoSuchTarefaException if a matching tarefa could not be found
	 */
	public Tarefa findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
				orderByComparator)
		throws NoSuchTarefaException;

	/**
	 * Returns the first tarefa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public Tarefa fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator);

	/**
	 * Returns the last tarefa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tarefa
	 * @throws NoSuchTarefaException if a matching tarefa could not be found
	 */
	public Tarefa findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
				orderByComparator)
		throws NoSuchTarefaException;

	/**
	 * Returns the last tarefa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public Tarefa fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator);

	/**
	 * Returns the tarefas before and after the current tarefa in the ordered set where uuid = &#63;.
	 *
	 * @param tarefaId the primary key of the current tarefa
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tarefa
	 * @throws NoSuchTarefaException if a tarefa with the primary key could not be found
	 */
	public Tarefa[] findByUuid_PrevAndNext(
			long tarefaId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
				orderByComparator)
		throws NoSuchTarefaException;

	/**
	 * Removes all the tarefas where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of tarefas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching tarefas
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the tarefa where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchTarefaException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching tarefa
	 * @throws NoSuchTarefaException if a matching tarefa could not be found
	 */
	public Tarefa findByUUID_G(String uuid, long groupId)
		throws NoSuchTarefaException;

	/**
	 * Returns the tarefa where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public Tarefa fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the tarefa where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public Tarefa fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the tarefa where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the tarefa that was removed
	 */
	public Tarefa removeByUUID_G(String uuid, long groupId)
		throws NoSuchTarefaException;

	/**
	 * Returns the number of tarefas where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching tarefas
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the tarefas where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching tarefas
	 */
	public java.util.List<Tarefa> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<Tarefa> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<Tarefa> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator);

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
	public java.util.List<Tarefa> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first tarefa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tarefa
	 * @throws NoSuchTarefaException if a matching tarefa could not be found
	 */
	public Tarefa findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
				orderByComparator)
		throws NoSuchTarefaException;

	/**
	 * Returns the first tarefa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public Tarefa fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator);

	/**
	 * Returns the last tarefa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tarefa
	 * @throws NoSuchTarefaException if a matching tarefa could not be found
	 */
	public Tarefa findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
				orderByComparator)
		throws NoSuchTarefaException;

	/**
	 * Returns the last tarefa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public Tarefa fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator);

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
	public Tarefa[] findByUuid_C_PrevAndNext(
			long tarefaId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
				orderByComparator)
		throws NoSuchTarefaException;

	/**
	 * Removes all the tarefas where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of tarefas where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching tarefas
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the tarefas where userId = &#63; and groupId = &#63; and tarefaPaiId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param tarefaPaiId the tarefa pai ID
	 * @return the matching tarefas
	 */
	public java.util.List<Tarefa> findBychildren(
		long userId, long groupId, long tarefaPaiId);

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
	public java.util.List<Tarefa> findBychildren(
		long userId, long groupId, long tarefaPaiId, int start, int end);

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
	public java.util.List<Tarefa> findBychildren(
		long userId, long groupId, long tarefaPaiId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator);

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
	public java.util.List<Tarefa> findBychildren(
		long userId, long groupId, long tarefaPaiId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator,
		boolean useFinderCache);

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
	public Tarefa findBychildren_First(
			long userId, long groupId, long tarefaPaiId,
			com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
				orderByComparator)
		throws NoSuchTarefaException;

	/**
	 * Returns the first tarefa in the ordered set where userId = &#63; and groupId = &#63; and tarefaPaiId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param tarefaPaiId the tarefa pai ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public Tarefa fetchBychildren_First(
		long userId, long groupId, long tarefaPaiId,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator);

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
	public Tarefa findBychildren_Last(
			long userId, long groupId, long tarefaPaiId,
			com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
				orderByComparator)
		throws NoSuchTarefaException;

	/**
	 * Returns the last tarefa in the ordered set where userId = &#63; and groupId = &#63; and tarefaPaiId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param tarefaPaiId the tarefa pai ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public Tarefa fetchBychildren_Last(
		long userId, long groupId, long tarefaPaiId,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator);

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
	public Tarefa[] findBychildren_PrevAndNext(
			long tarefaId, long userId, long groupId, long tarefaPaiId,
			com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
				orderByComparator)
		throws NoSuchTarefaException;

	/**
	 * Removes all the tarefas where userId = &#63; and groupId = &#63; and tarefaPaiId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param tarefaPaiId the tarefa pai ID
	 */
	public void removeBychildren(long userId, long groupId, long tarefaPaiId);

	/**
	 * Returns the number of tarefas where userId = &#63; and groupId = &#63; and tarefaPaiId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param tarefaPaiId the tarefa pai ID
	 * @return the number of matching tarefas
	 */
	public int countBychildren(long userId, long groupId, long tarefaPaiId);

	/**
	 * Returns all the tarefas where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the matching tarefas
	 */
	public java.util.List<Tarefa> findBygroupIdUser(long userId, long groupId);

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
	public java.util.List<Tarefa> findBygroupIdUser(
		long userId, long groupId, int start, int end);

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
	public java.util.List<Tarefa> findBygroupIdUser(
		long userId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator);

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
	public java.util.List<Tarefa> findBygroupIdUser(
		long userId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first tarefa in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tarefa
	 * @throws NoSuchTarefaException if a matching tarefa could not be found
	 */
	public Tarefa findBygroupIdUser_First(
			long userId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
				orderByComparator)
		throws NoSuchTarefaException;

	/**
	 * Returns the first tarefa in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public Tarefa fetchBygroupIdUser_First(
		long userId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator);

	/**
	 * Returns the last tarefa in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tarefa
	 * @throws NoSuchTarefaException if a matching tarefa could not be found
	 */
	public Tarefa findBygroupIdUser_Last(
			long userId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
				orderByComparator)
		throws NoSuchTarefaException;

	/**
	 * Returns the last tarefa in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public Tarefa fetchBygroupIdUser_Last(
		long userId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator);

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
	public Tarefa[] findBygroupIdUser_PrevAndNext(
			long tarefaId, long userId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
				orderByComparator)
		throws NoSuchTarefaException;

	/**
	 * Removes all the tarefas where userId = &#63; and groupId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 */
	public void removeBygroupIdUser(long userId, long groupId);

	/**
	 * Returns the number of tarefas where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the number of matching tarefas
	 */
	public int countBygroupIdUser(long userId, long groupId);

	/**
	 * Returns all the tarefas where userId = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching tarefas
	 */
	public java.util.List<Tarefa> findBybyStatus(
		long userId, long groupId, int status);

	/**
	 * Returns a range of all the tarefas where userId = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TarefaModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of tarefas
	 * @param end the upper bound of the range of tarefas (not inclusive)
	 * @return the range of matching tarefas
	 */
	public java.util.List<Tarefa> findBybyStatus(
		long userId, long groupId, int status, int start, int end);

	/**
	 * Returns an ordered range of all the tarefas where userId = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TarefaModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of tarefas
	 * @param end the upper bound of the range of tarefas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tarefas
	 */
	public java.util.List<Tarefa> findBybyStatus(
		long userId, long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator);

	/**
	 * Returns an ordered range of all the tarefas where userId = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TarefaModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of tarefas
	 * @param end the upper bound of the range of tarefas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tarefas
	 */
	public java.util.List<Tarefa> findBybyStatus(
		long userId, long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first tarefa in the ordered set where userId = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tarefa
	 * @throws NoSuchTarefaException if a matching tarefa could not be found
	 */
	public Tarefa findBybyStatus_First(
			long userId, long groupId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
				orderByComparator)
		throws NoSuchTarefaException;

	/**
	 * Returns the first tarefa in the ordered set where userId = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public Tarefa fetchBybyStatus_First(
		long userId, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator);

	/**
	 * Returns the last tarefa in the ordered set where userId = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tarefa
	 * @throws NoSuchTarefaException if a matching tarefa could not be found
	 */
	public Tarefa findBybyStatus_Last(
			long userId, long groupId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
				orderByComparator)
		throws NoSuchTarefaException;

	/**
	 * Returns the last tarefa in the ordered set where userId = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	public Tarefa fetchBybyStatus_Last(
		long userId, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator);

	/**
	 * Returns the tarefas before and after the current tarefa in the ordered set where userId = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param tarefaId the primary key of the current tarefa
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tarefa
	 * @throws NoSuchTarefaException if a tarefa with the primary key could not be found
	 */
	public Tarefa[] findBybyStatus_PrevAndNext(
			long tarefaId, long userId, long groupId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
				orderByComparator)
		throws NoSuchTarefaException;

	/**
	 * Removes all the tarefas where userId = &#63; and groupId = &#63; and status = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param status the status
	 */
	public void removeBybyStatus(long userId, long groupId, int status);

	/**
	 * Returns the number of tarefas where userId = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching tarefas
	 */
	public int countBybyStatus(long userId, long groupId, int status);

	/**
	 * Caches the tarefa in the entity cache if it is enabled.
	 *
	 * @param tarefa the tarefa
	 */
	public void cacheResult(Tarefa tarefa);

	/**
	 * Caches the tarefas in the entity cache if it is enabled.
	 *
	 * @param tarefas the tarefas
	 */
	public void cacheResult(java.util.List<Tarefa> tarefas);

	/**
	 * Creates a new tarefa with the primary key. Does not add the tarefa to the database.
	 *
	 * @param tarefaId the primary key for the new tarefa
	 * @return the new tarefa
	 */
	public Tarefa create(long tarefaId);

	/**
	 * Removes the tarefa with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tarefaId the primary key of the tarefa
	 * @return the tarefa that was removed
	 * @throws NoSuchTarefaException if a tarefa with the primary key could not be found
	 */
	public Tarefa remove(long tarefaId) throws NoSuchTarefaException;

	public Tarefa updateImpl(Tarefa tarefa);

	/**
	 * Returns the tarefa with the primary key or throws a <code>NoSuchTarefaException</code> if it could not be found.
	 *
	 * @param tarefaId the primary key of the tarefa
	 * @return the tarefa
	 * @throws NoSuchTarefaException if a tarefa with the primary key could not be found
	 */
	public Tarefa findByPrimaryKey(long tarefaId) throws NoSuchTarefaException;

	/**
	 * Returns the tarefa with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param tarefaId the primary key of the tarefa
	 * @return the tarefa, or <code>null</code> if a tarefa with the primary key could not be found
	 */
	public Tarefa fetchByPrimaryKey(long tarefaId);

	/**
	 * Returns all the tarefas.
	 *
	 * @return the tarefas
	 */
	public java.util.List<Tarefa> findAll();

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
	public java.util.List<Tarefa> findAll(int start, int end);

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
	public java.util.List<Tarefa> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator);

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
	public java.util.List<Tarefa> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Tarefa>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the tarefas from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of tarefas.
	 *
	 * @return the number of tarefas
	 */
	public int countAll();

}