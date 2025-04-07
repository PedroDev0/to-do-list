/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.pedro.dev.tarefa.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import com.pedro.dev.tarefa.exception.NoSuchTarefaException;
import com.pedro.dev.tarefa.model.Tarefa;
import com.pedro.dev.tarefa.model.TarefaTable;
import com.pedro.dev.tarefa.model.impl.TarefaImpl;
import com.pedro.dev.tarefa.model.impl.TarefaModelImpl;
import com.pedro.dev.tarefa.service.persistence.TarefaPersistence;
import com.pedro.dev.tarefa.service.persistence.TarefaUtil;
import com.pedro.dev.tarefa.service.persistence.impl.constants.ToDoListPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the tarefa service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = TarefaPersistence.class)
public class TarefaPersistenceImpl
	extends BasePersistenceImpl<Tarefa> implements TarefaPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TarefaUtil</code> to access the tarefa persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TarefaImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the tarefas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching tarefas
	 */
	@Override
	public List<Tarefa> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Tarefa> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<Tarefa> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Tarefa> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<Tarefa> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Tarefa> orderByComparator, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<Tarefa> list = null;

		if (useFinderCache) {
			list = (List<Tarefa>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Tarefa tarefa : list) {
					if (!uuid.equals(tarefa.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_TAREFA_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(TarefaModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<Tarefa>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first tarefa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tarefa
	 * @throws NoSuchTarefaException if a matching tarefa could not be found
	 */
	@Override
	public Tarefa findByUuid_First(
			String uuid, OrderByComparator<Tarefa> orderByComparator)
		throws NoSuchTarefaException {

		Tarefa tarefa = fetchByUuid_First(uuid, orderByComparator);

		if (tarefa != null) {
			return tarefa;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchTarefaException(sb.toString());
	}

	/**
	 * Returns the first tarefa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	@Override
	public Tarefa fetchByUuid_First(
		String uuid, OrderByComparator<Tarefa> orderByComparator) {

		List<Tarefa> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tarefa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tarefa
	 * @throws NoSuchTarefaException if a matching tarefa could not be found
	 */
	@Override
	public Tarefa findByUuid_Last(
			String uuid, OrderByComparator<Tarefa> orderByComparator)
		throws NoSuchTarefaException {

		Tarefa tarefa = fetchByUuid_Last(uuid, orderByComparator);

		if (tarefa != null) {
			return tarefa;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchTarefaException(sb.toString());
	}

	/**
	 * Returns the last tarefa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	@Override
	public Tarefa fetchByUuid_Last(
		String uuid, OrderByComparator<Tarefa> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Tarefa> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Tarefa[] findByUuid_PrevAndNext(
			long tarefaId, String uuid,
			OrderByComparator<Tarefa> orderByComparator)
		throws NoSuchTarefaException {

		uuid = Objects.toString(uuid, "");

		Tarefa tarefa = findByPrimaryKey(tarefaId);

		Session session = null;

		try {
			session = openSession();

			Tarefa[] array = new TarefaImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, tarefa, uuid, orderByComparator, true);

			array[1] = tarefa;

			array[2] = getByUuid_PrevAndNext(
				session, tarefa, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Tarefa getByUuid_PrevAndNext(
		Session session, Tarefa tarefa, String uuid,
		OrderByComparator<Tarefa> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_TAREFA_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(TarefaModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(tarefa)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Tarefa> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tarefas where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Tarefa tarefa :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(tarefa);
		}
	}

	/**
	 * Returns the number of tarefas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching tarefas
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_TAREFA_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 = "tarefa.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(tarefa.uuid IS NULL OR tarefa.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the tarefa where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchTarefaException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching tarefa
	 * @throws NoSuchTarefaException if a matching tarefa could not be found
	 */
	@Override
	public Tarefa findByUUID_G(String uuid, long groupId)
		throws NoSuchTarefaException {

		Tarefa tarefa = fetchByUUID_G(uuid, groupId);

		if (tarefa == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("uuid=");
			sb.append(uuid);

			sb.append(", groupId=");
			sb.append(groupId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchTarefaException(sb.toString());
		}

		return tarefa;
	}

	/**
	 * Returns the tarefa where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	@Override
	public Tarefa fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the tarefa where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	@Override
	public Tarefa fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {uuid, groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof Tarefa) {
			Tarefa tarefa = (Tarefa)result;

			if (!Objects.equals(uuid, tarefa.getUuid()) ||
				(groupId != tarefa.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_TAREFA_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				List<Tarefa> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					Tarefa tarefa = list.get(0);

					result = tarefa;

					cacheResult(tarefa);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Tarefa)result;
		}
	}

	/**
	 * Removes the tarefa where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the tarefa that was removed
	 */
	@Override
	public Tarefa removeByUUID_G(String uuid, long groupId)
		throws NoSuchTarefaException {

		Tarefa tarefa = findByUUID_G(uuid, groupId);

		return remove(tarefa);
	}

	/**
	 * Returns the number of tarefas where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching tarefas
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_TAREFA_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"tarefa.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(tarefa.uuid IS NULL OR tarefa.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"tarefa.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the tarefas where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching tarefas
	 */
	@Override
	public List<Tarefa> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Tarefa> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
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
	@Override
	public List<Tarefa> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Tarefa> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
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
	@Override
	public List<Tarefa> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Tarefa> orderByComparator, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<Tarefa> list = null;

		if (useFinderCache) {
			list = (List<Tarefa>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Tarefa tarefa : list) {
					if (!uuid.equals(tarefa.getUuid()) ||
						(companyId != tarefa.getCompanyId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_TAREFA_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(TarefaModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				list = (List<Tarefa>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public Tarefa findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Tarefa> orderByComparator)
		throws NoSuchTarefaException {

		Tarefa tarefa = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (tarefa != null) {
			return tarefa;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchTarefaException(sb.toString());
	}

	/**
	 * Returns the first tarefa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	@Override
	public Tarefa fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<Tarefa> orderByComparator) {

		List<Tarefa> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Tarefa findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Tarefa> orderByComparator)
		throws NoSuchTarefaException {

		Tarefa tarefa = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (tarefa != null) {
			return tarefa;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchTarefaException(sb.toString());
	}

	/**
	 * Returns the last tarefa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	@Override
	public Tarefa fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<Tarefa> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Tarefa> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Tarefa[] findByUuid_C_PrevAndNext(
			long tarefaId, String uuid, long companyId,
			OrderByComparator<Tarefa> orderByComparator)
		throws NoSuchTarefaException {

		uuid = Objects.toString(uuid, "");

		Tarefa tarefa = findByPrimaryKey(tarefaId);

		Session session = null;

		try {
			session = openSession();

			Tarefa[] array = new TarefaImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, tarefa, uuid, companyId, orderByComparator, true);

			array[1] = tarefa;

			array[2] = getByUuid_C_PrevAndNext(
				session, tarefa, uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Tarefa getByUuid_C_PrevAndNext(
		Session session, Tarefa tarefa, String uuid, long companyId,
		OrderByComparator<Tarefa> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_TAREFA_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(TarefaModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(tarefa)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Tarefa> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tarefas where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Tarefa tarefa :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(tarefa);
		}
	}

	/**
	 * Returns the number of tarefas where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching tarefas
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_TAREFA_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"tarefa.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(tarefa.uuid IS NULL OR tarefa.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"tarefa.companyId = ?";

	private FinderPath _finderPathWithPaginationFindBychildren;
	private FinderPath _finderPathWithoutPaginationFindBychildren;
	private FinderPath _finderPathCountBychildren;

	/**
	 * Returns all the tarefas where userId = &#63; and groupId = &#63; and tarefaPaiId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param tarefaPaiId the tarefa pai ID
	 * @return the matching tarefas
	 */
	@Override
	public List<Tarefa> findBychildren(
		long userId, long groupId, long tarefaPaiId) {

		return findBychildren(
			userId, groupId, tarefaPaiId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<Tarefa> findBychildren(
		long userId, long groupId, long tarefaPaiId, int start, int end) {

		return findBychildren(userId, groupId, tarefaPaiId, start, end, null);
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
	@Override
	public List<Tarefa> findBychildren(
		long userId, long groupId, long tarefaPaiId, int start, int end,
		OrderByComparator<Tarefa> orderByComparator) {

		return findBychildren(
			userId, groupId, tarefaPaiId, start, end, orderByComparator, true);
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
	@Override
	public List<Tarefa> findBychildren(
		long userId, long groupId, long tarefaPaiId, int start, int end,
		OrderByComparator<Tarefa> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBychildren;
				finderArgs = new Object[] {userId, groupId, tarefaPaiId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBychildren;
			finderArgs = new Object[] {
				userId, groupId, tarefaPaiId, start, end, orderByComparator
			};
		}

		List<Tarefa> list = null;

		if (useFinderCache) {
			list = (List<Tarefa>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Tarefa tarefa : list) {
					if ((userId != tarefa.getUserId()) ||
						(groupId != tarefa.getGroupId()) ||
						(tarefaPaiId != tarefa.getTarefaPaiId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					5 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(5);
			}

			sb.append(_SQL_SELECT_TAREFA_WHERE);

			sb.append(_FINDER_COLUMN_CHILDREN_USERID_2);

			sb.append(_FINDER_COLUMN_CHILDREN_GROUPID_2);

			sb.append(_FINDER_COLUMN_CHILDREN_TAREFAPAIID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(TarefaModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(groupId);

				queryPos.add(tarefaPaiId);

				list = (List<Tarefa>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public Tarefa findBychildren_First(
			long userId, long groupId, long tarefaPaiId,
			OrderByComparator<Tarefa> orderByComparator)
		throws NoSuchTarefaException {

		Tarefa tarefa = fetchBychildren_First(
			userId, groupId, tarefaPaiId, orderByComparator);

		if (tarefa != null) {
			return tarefa;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", groupId=");
		sb.append(groupId);

		sb.append(", tarefaPaiId=");
		sb.append(tarefaPaiId);

		sb.append("}");

		throw new NoSuchTarefaException(sb.toString());
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
	@Override
	public Tarefa fetchBychildren_First(
		long userId, long groupId, long tarefaPaiId,
		OrderByComparator<Tarefa> orderByComparator) {

		List<Tarefa> list = findBychildren(
			userId, groupId, tarefaPaiId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Tarefa findBychildren_Last(
			long userId, long groupId, long tarefaPaiId,
			OrderByComparator<Tarefa> orderByComparator)
		throws NoSuchTarefaException {

		Tarefa tarefa = fetchBychildren_Last(
			userId, groupId, tarefaPaiId, orderByComparator);

		if (tarefa != null) {
			return tarefa;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", groupId=");
		sb.append(groupId);

		sb.append(", tarefaPaiId=");
		sb.append(tarefaPaiId);

		sb.append("}");

		throw new NoSuchTarefaException(sb.toString());
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
	@Override
	public Tarefa fetchBychildren_Last(
		long userId, long groupId, long tarefaPaiId,
		OrderByComparator<Tarefa> orderByComparator) {

		int count = countBychildren(userId, groupId, tarefaPaiId);

		if (count == 0) {
			return null;
		}

		List<Tarefa> list = findBychildren(
			userId, groupId, tarefaPaiId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Tarefa[] findBychildren_PrevAndNext(
			long tarefaId, long userId, long groupId, long tarefaPaiId,
			OrderByComparator<Tarefa> orderByComparator)
		throws NoSuchTarefaException {

		Tarefa tarefa = findByPrimaryKey(tarefaId);

		Session session = null;

		try {
			session = openSession();

			Tarefa[] array = new TarefaImpl[3];

			array[0] = getBychildren_PrevAndNext(
				session, tarefa, userId, groupId, tarefaPaiId,
				orderByComparator, true);

			array[1] = tarefa;

			array[2] = getBychildren_PrevAndNext(
				session, tarefa, userId, groupId, tarefaPaiId,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Tarefa getBychildren_PrevAndNext(
		Session session, Tarefa tarefa, long userId, long groupId,
		long tarefaPaiId, OrderByComparator<Tarefa> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_TAREFA_WHERE);

		sb.append(_FINDER_COLUMN_CHILDREN_USERID_2);

		sb.append(_FINDER_COLUMN_CHILDREN_GROUPID_2);

		sb.append(_FINDER_COLUMN_CHILDREN_TAREFAPAIID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(TarefaModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		queryPos.add(groupId);

		queryPos.add(tarefaPaiId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(tarefa)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Tarefa> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tarefas where userId = &#63; and groupId = &#63; and tarefaPaiId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param tarefaPaiId the tarefa pai ID
	 */
	@Override
	public void removeBychildren(long userId, long groupId, long tarefaPaiId) {
		for (Tarefa tarefa :
				findBychildren(
					userId, groupId, tarefaPaiId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(tarefa);
		}
	}

	/**
	 * Returns the number of tarefas where userId = &#63; and groupId = &#63; and tarefaPaiId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param tarefaPaiId the tarefa pai ID
	 * @return the number of matching tarefas
	 */
	@Override
	public int countBychildren(long userId, long groupId, long tarefaPaiId) {
		FinderPath finderPath = _finderPathCountBychildren;

		Object[] finderArgs = new Object[] {userId, groupId, tarefaPaiId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_TAREFA_WHERE);

			sb.append(_FINDER_COLUMN_CHILDREN_USERID_2);

			sb.append(_FINDER_COLUMN_CHILDREN_GROUPID_2);

			sb.append(_FINDER_COLUMN_CHILDREN_TAREFAPAIID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(groupId);

				queryPos.add(tarefaPaiId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CHILDREN_USERID_2 =
		"tarefa.userId = ? AND ";

	private static final String _FINDER_COLUMN_CHILDREN_GROUPID_2 =
		"tarefa.groupId = ? AND ";

	private static final String _FINDER_COLUMN_CHILDREN_TAREFAPAIID_2 =
		"tarefa.tarefaPaiId = ?";

	private FinderPath _finderPathWithPaginationFindBygroupIdUser;
	private FinderPath _finderPathWithoutPaginationFindBygroupIdUser;
	private FinderPath _finderPathCountBygroupIdUser;

	/**
	 * Returns all the tarefas where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the matching tarefas
	 */
	@Override
	public List<Tarefa> findBygroupIdUser(long userId, long groupId) {
		return findBygroupIdUser(
			userId, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Tarefa> findBygroupIdUser(
		long userId, long groupId, int start, int end) {

		return findBygroupIdUser(userId, groupId, start, end, null);
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
	@Override
	public List<Tarefa> findBygroupIdUser(
		long userId, long groupId, int start, int end,
		OrderByComparator<Tarefa> orderByComparator) {

		return findBygroupIdUser(
			userId, groupId, start, end, orderByComparator, true);
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
	@Override
	public List<Tarefa> findBygroupIdUser(
		long userId, long groupId, int start, int end,
		OrderByComparator<Tarefa> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBygroupIdUser;
				finderArgs = new Object[] {userId, groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBygroupIdUser;
			finderArgs = new Object[] {
				userId, groupId, start, end, orderByComparator
			};
		}

		List<Tarefa> list = null;

		if (useFinderCache) {
			list = (List<Tarefa>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Tarefa tarefa : list) {
					if ((userId != tarefa.getUserId()) ||
						(groupId != tarefa.getGroupId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_TAREFA_WHERE);

			sb.append(_FINDER_COLUMN_GROUPIDUSER_USERID_2);

			sb.append(_FINDER_COLUMN_GROUPIDUSER_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(TarefaModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(groupId);

				list = (List<Tarefa>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public Tarefa findBygroupIdUser_First(
			long userId, long groupId,
			OrderByComparator<Tarefa> orderByComparator)
		throws NoSuchTarefaException {

		Tarefa tarefa = fetchBygroupIdUser_First(
			userId, groupId, orderByComparator);

		if (tarefa != null) {
			return tarefa;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchTarefaException(sb.toString());
	}

	/**
	 * Returns the first tarefa in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	@Override
	public Tarefa fetchBygroupIdUser_First(
		long userId, long groupId,
		OrderByComparator<Tarefa> orderByComparator) {

		List<Tarefa> list = findBygroupIdUser(
			userId, groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Tarefa findBygroupIdUser_Last(
			long userId, long groupId,
			OrderByComparator<Tarefa> orderByComparator)
		throws NoSuchTarefaException {

		Tarefa tarefa = fetchBygroupIdUser_Last(
			userId, groupId, orderByComparator);

		if (tarefa != null) {
			return tarefa;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchTarefaException(sb.toString());
	}

	/**
	 * Returns the last tarefa in the ordered set where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tarefa, or <code>null</code> if a matching tarefa could not be found
	 */
	@Override
	public Tarefa fetchBygroupIdUser_Last(
		long userId, long groupId,
		OrderByComparator<Tarefa> orderByComparator) {

		int count = countBygroupIdUser(userId, groupId);

		if (count == 0) {
			return null;
		}

		List<Tarefa> list = findBygroupIdUser(
			userId, groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Tarefa[] findBygroupIdUser_PrevAndNext(
			long tarefaId, long userId, long groupId,
			OrderByComparator<Tarefa> orderByComparator)
		throws NoSuchTarefaException {

		Tarefa tarefa = findByPrimaryKey(tarefaId);

		Session session = null;

		try {
			session = openSession();

			Tarefa[] array = new TarefaImpl[3];

			array[0] = getBygroupIdUser_PrevAndNext(
				session, tarefa, userId, groupId, orderByComparator, true);

			array[1] = tarefa;

			array[2] = getBygroupIdUser_PrevAndNext(
				session, tarefa, userId, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Tarefa getBygroupIdUser_PrevAndNext(
		Session session, Tarefa tarefa, long userId, long groupId,
		OrderByComparator<Tarefa> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_TAREFA_WHERE);

		sb.append(_FINDER_COLUMN_GROUPIDUSER_USERID_2);

		sb.append(_FINDER_COLUMN_GROUPIDUSER_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(TarefaModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(tarefa)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Tarefa> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tarefas where userId = &#63; and groupId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 */
	@Override
	public void removeBygroupIdUser(long userId, long groupId) {
		for (Tarefa tarefa :
				findBygroupIdUser(
					userId, groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(tarefa);
		}
	}

	/**
	 * Returns the number of tarefas where userId = &#63; and groupId = &#63;.
	 *
	 * @param userId the user ID
	 * @param groupId the group ID
	 * @return the number of matching tarefas
	 */
	@Override
	public int countBygroupIdUser(long userId, long groupId) {
		FinderPath finderPath = _finderPathCountBygroupIdUser;

		Object[] finderArgs = new Object[] {userId, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_TAREFA_WHERE);

			sb.append(_FINDER_COLUMN_GROUPIDUSER_USERID_2);

			sb.append(_FINDER_COLUMN_GROUPIDUSER_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(groupId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUPIDUSER_USERID_2 =
		"tarefa.userId = ? AND ";

	private static final String _FINDER_COLUMN_GROUPIDUSER_GROUPID_2 =
		"tarefa.groupId = ?";

	public TarefaPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(Tarefa.class);

		setModelImplClass(TarefaImpl.class);
		setModelPKClass(long.class);

		setTable(TarefaTable.INSTANCE);
	}

	/**
	 * Caches the tarefa in the entity cache if it is enabled.
	 *
	 * @param tarefa the tarefa
	 */
	@Override
	public void cacheResult(Tarefa tarefa) {
		entityCache.putResult(TarefaImpl.class, tarefa.getPrimaryKey(), tarefa);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {tarefa.getUuid(), tarefa.getGroupId()}, tarefa);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the tarefas in the entity cache if it is enabled.
	 *
	 * @param tarefas the tarefas
	 */
	@Override
	public void cacheResult(List<Tarefa> tarefas) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (tarefas.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Tarefa tarefa : tarefas) {
			if (entityCache.getResult(
					TarefaImpl.class, tarefa.getPrimaryKey()) == null) {

				cacheResult(tarefa);
			}
		}
	}

	/**
	 * Clears the cache for all tarefas.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TarefaImpl.class);

		finderCache.clearCache(TarefaImpl.class);
	}

	/**
	 * Clears the cache for the tarefa.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Tarefa tarefa) {
		entityCache.removeResult(TarefaImpl.class, tarefa);
	}

	@Override
	public void clearCache(List<Tarefa> tarefas) {
		for (Tarefa tarefa : tarefas) {
			entityCache.removeResult(TarefaImpl.class, tarefa);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(TarefaImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(TarefaImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(TarefaModelImpl tarefaModelImpl) {
		Object[] args = new Object[] {
			tarefaModelImpl.getUuid(), tarefaModelImpl.getGroupId()
		};

		finderCache.putResult(_finderPathCountByUUID_G, args, Long.valueOf(1));
		finderCache.putResult(_finderPathFetchByUUID_G, args, tarefaModelImpl);
	}

	/**
	 * Creates a new tarefa with the primary key. Does not add the tarefa to the database.
	 *
	 * @param tarefaId the primary key for the new tarefa
	 * @return the new tarefa
	 */
	@Override
	public Tarefa create(long tarefaId) {
		Tarefa tarefa = new TarefaImpl();

		tarefa.setNew(true);
		tarefa.setPrimaryKey(tarefaId);

		String uuid = PortalUUIDUtil.generate();

		tarefa.setUuid(uuid);

		tarefa.setCompanyId(CompanyThreadLocal.getCompanyId());

		return tarefa;
	}

	/**
	 * Removes the tarefa with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tarefaId the primary key of the tarefa
	 * @return the tarefa that was removed
	 * @throws NoSuchTarefaException if a tarefa with the primary key could not be found
	 */
	@Override
	public Tarefa remove(long tarefaId) throws NoSuchTarefaException {
		return remove((Serializable)tarefaId);
	}

	/**
	 * Removes the tarefa with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the tarefa
	 * @return the tarefa that was removed
	 * @throws NoSuchTarefaException if a tarefa with the primary key could not be found
	 */
	@Override
	public Tarefa remove(Serializable primaryKey) throws NoSuchTarefaException {
		Session session = null;

		try {
			session = openSession();

			Tarefa tarefa = (Tarefa)session.get(TarefaImpl.class, primaryKey);

			if (tarefa == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTarefaException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(tarefa);
		}
		catch (NoSuchTarefaException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Tarefa removeImpl(Tarefa tarefa) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(tarefa)) {
				tarefa = (Tarefa)session.get(
					TarefaImpl.class, tarefa.getPrimaryKeyObj());
			}

			if (tarefa != null) {
				session.delete(tarefa);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (tarefa != null) {
			clearCache(tarefa);
		}

		return tarefa;
	}

	@Override
	public Tarefa updateImpl(Tarefa tarefa) {
		boolean isNew = tarefa.isNew();

		if (!(tarefa instanceof TarefaModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(tarefa.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(tarefa);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in tarefa proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Tarefa implementation " +
					tarefa.getClass());
		}

		TarefaModelImpl tarefaModelImpl = (TarefaModelImpl)tarefa;

		if (Validator.isNull(tarefa.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			tarefa.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (tarefa.getCreateDate() == null)) {
			if (serviceContext == null) {
				tarefa.setCreateDate(date);
			}
			else {
				tarefa.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!tarefaModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				tarefa.setModifiedDate(date);
			}
			else {
				tarefa.setModifiedDate(serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(tarefa);
			}
			else {
				tarefa = (Tarefa)session.merge(tarefa);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(TarefaImpl.class, tarefaModelImpl, false, true);

		cacheUniqueFindersCache(tarefaModelImpl);

		if (isNew) {
			tarefa.setNew(false);
		}

		tarefa.resetOriginalValues();

		return tarefa;
	}

	/**
	 * Returns the tarefa with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the tarefa
	 * @return the tarefa
	 * @throws NoSuchTarefaException if a tarefa with the primary key could not be found
	 */
	@Override
	public Tarefa findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTarefaException {

		Tarefa tarefa = fetchByPrimaryKey(primaryKey);

		if (tarefa == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTarefaException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return tarefa;
	}

	/**
	 * Returns the tarefa with the primary key or throws a <code>NoSuchTarefaException</code> if it could not be found.
	 *
	 * @param tarefaId the primary key of the tarefa
	 * @return the tarefa
	 * @throws NoSuchTarefaException if a tarefa with the primary key could not be found
	 */
	@Override
	public Tarefa findByPrimaryKey(long tarefaId) throws NoSuchTarefaException {
		return findByPrimaryKey((Serializable)tarefaId);
	}

	/**
	 * Returns the tarefa with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param tarefaId the primary key of the tarefa
	 * @return the tarefa, or <code>null</code> if a tarefa with the primary key could not be found
	 */
	@Override
	public Tarefa fetchByPrimaryKey(long tarefaId) {
		return fetchByPrimaryKey((Serializable)tarefaId);
	}

	/**
	 * Returns all the tarefas.
	 *
	 * @return the tarefas
	 */
	@Override
	public List<Tarefa> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Tarefa> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<Tarefa> findAll(
		int start, int end, OrderByComparator<Tarefa> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<Tarefa> findAll(
		int start, int end, OrderByComparator<Tarefa> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<Tarefa> list = null;

		if (useFinderCache) {
			list = (List<Tarefa>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_TAREFA);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_TAREFA;

				sql = sql.concat(TarefaModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Tarefa>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the tarefas from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Tarefa tarefa : findAll()) {
			remove(tarefa);
		}
	}

	/**
	 * Returns the number of tarefas.
	 *
	 * @return the number of tarefas
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_TAREFA);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "tarefaId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_TAREFA;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TarefaModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the tarefa persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);

		_finderPathFetchByUUID_G = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, true);

		_finderPathCountByUUID_G = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, false);

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathCountByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, false);

		_finderPathWithPaginationFindBychildren = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBychildren",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"userId", "groupId", "tarefaPaiId"}, true);

		_finderPathWithoutPaginationFindBychildren = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBychildren",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			new String[] {"userId", "groupId", "tarefaPaiId"}, true);

		_finderPathCountBychildren = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBychildren",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			new String[] {"userId", "groupId", "tarefaPaiId"}, false);

		_finderPathWithPaginationFindBygroupIdUser = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBygroupIdUser",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"userId", "groupId"}, true);

		_finderPathWithoutPaginationFindBygroupIdUser = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBygroupIdUser",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"userId", "groupId"}, true);

		_finderPathCountBygroupIdUser = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBygroupIdUser",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"userId", "groupId"}, false);

		TarefaUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		TarefaUtil.setPersistence(null);

		entityCache.removeCache(TarefaImpl.class.getName());
	}

	@Override
	@Reference(
		target = ToDoListPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = ToDoListPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = ToDoListPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_TAREFA =
		"SELECT tarefa FROM Tarefa tarefa";

	private static final String _SQL_SELECT_TAREFA_WHERE =
		"SELECT tarefa FROM Tarefa tarefa WHERE ";

	private static final String _SQL_COUNT_TAREFA =
		"SELECT COUNT(tarefa) FROM Tarefa tarefa";

	private static final String _SQL_COUNT_TAREFA_WHERE =
		"SELECT COUNT(tarefa) FROM Tarefa tarefa WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "tarefa.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Tarefa exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Tarefa exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		TarefaPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}