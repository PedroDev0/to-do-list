/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.pedro.dev.tarefa.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.pedro.dev.tarefa.exception.NoSuchTarefaException;
import com.pedro.dev.tarefa.model.Tarefa;
import com.pedro.dev.tarefa.service.TarefaLocalServiceUtil;
import com.pedro.dev.tarefa.service.persistence.TarefaPersistence;
import com.pedro.dev.tarefa.service.persistence.TarefaUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class TarefaPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.pedro.dev.tarefa.service"));

	@Before
	public void setUp() {
		_persistence = TarefaUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Tarefa> iterator = _tarefas.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Tarefa tarefa = _persistence.create(pk);

		Assert.assertNotNull(tarefa);

		Assert.assertEquals(tarefa.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Tarefa newTarefa = addTarefa();

		_persistence.remove(newTarefa);

		Tarefa existingTarefa = _persistence.fetchByPrimaryKey(
			newTarefa.getPrimaryKey());

		Assert.assertNull(existingTarefa);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addTarefa();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Tarefa newTarefa = _persistence.create(pk);

		newTarefa.setUuid(RandomTestUtil.randomString());

		newTarefa.setGroupId(RandomTestUtil.nextLong());

		newTarefa.setCompanyId(RandomTestUtil.nextLong());

		newTarefa.setUserId(RandomTestUtil.nextLong());

		newTarefa.setUserName(RandomTestUtil.randomString());

		newTarefa.setCreateDate(RandomTestUtil.nextDate());

		newTarefa.setModifiedDate(RandomTestUtil.nextDate());

		newTarefa.setTitulo(RandomTestUtil.randomString());

		newTarefa.setDescricao(RandomTestUtil.randomString());

		newTarefa.setUrlImagem(RandomTestUtil.randomString());

		newTarefa.setStatus(RandomTestUtil.nextInt());

		newTarefa.setTarefaPaiId(RandomTestUtil.nextLong());

		newTarefa.setFileEntryId(RandomTestUtil.nextLong());

		_tarefas.add(_persistence.update(newTarefa));

		Tarefa existingTarefa = _persistence.findByPrimaryKey(
			newTarefa.getPrimaryKey());

		Assert.assertEquals(existingTarefa.getUuid(), newTarefa.getUuid());
		Assert.assertEquals(
			existingTarefa.getTarefaId(), newTarefa.getTarefaId());
		Assert.assertEquals(
			existingTarefa.getGroupId(), newTarefa.getGroupId());
		Assert.assertEquals(
			existingTarefa.getCompanyId(), newTarefa.getCompanyId());
		Assert.assertEquals(existingTarefa.getUserId(), newTarefa.getUserId());
		Assert.assertEquals(
			existingTarefa.getUserName(), newTarefa.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingTarefa.getCreateDate()),
			Time.getShortTimestamp(newTarefa.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingTarefa.getModifiedDate()),
			Time.getShortTimestamp(newTarefa.getModifiedDate()));
		Assert.assertEquals(existingTarefa.getTitulo(), newTarefa.getTitulo());
		Assert.assertEquals(
			existingTarefa.getDescricao(), newTarefa.getDescricao());
		Assert.assertEquals(
			existingTarefa.getUrlImagem(), newTarefa.getUrlImagem());
		Assert.assertEquals(existingTarefa.getStatus(), newTarefa.getStatus());
		Assert.assertEquals(
			existingTarefa.getTarefaPaiId(), newTarefa.getTarefaPaiId());
		Assert.assertEquals(
			existingTarefa.getFileEntryId(), newTarefa.getFileEntryId());
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid("");

		_persistence.countByUuid("null");

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUUID_G() throws Exception {
		_persistence.countByUUID_G("", RandomTestUtil.nextLong());

		_persistence.countByUUID_G("null", 0L);

		_persistence.countByUUID_G((String)null, 0L);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C("", RandomTestUtil.nextLong());

		_persistence.countByUuid_C("null", 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountBychildren() throws Exception {
		_persistence.countBychildren(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countBychildren(0L, 0L, 0L);
	}

	@Test
	public void testCountBygroupIdUser() throws Exception {
		_persistence.countBygroupIdUser(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countBygroupIdUser(0L, 0L);
	}

	@Test
	public void testCountBybyStatus() throws Exception {
		_persistence.countBybyStatus(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countBybyStatus(0L, 0L, 0);
	}

	@Test
	public void testCountBysubTarefasByStatus() throws Exception {
		_persistence.countBysubTarefasByStatus(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt(), RandomTestUtil.nextLong());

		_persistence.countBysubTarefasByStatus(0L, 0L, 0, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Tarefa newTarefa = addTarefa();

		Tarefa existingTarefa = _persistence.findByPrimaryKey(
			newTarefa.getPrimaryKey());

		Assert.assertEquals(existingTarefa, newTarefa);
	}

	@Test(expected = NoSuchTarefaException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<Tarefa> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"ToDoList_Tarefa", "uuid", true, "tarefaId", true, "groupId", true,
			"companyId", true, "userId", true, "userName", true, "createDate",
			true, "modifiedDate", true, "titulo", true, "descricao", true,
			"urlImagem", true, "status", true, "tarefaPaiId", true,
			"fileEntryId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Tarefa newTarefa = addTarefa();

		Tarefa existingTarefa = _persistence.fetchByPrimaryKey(
			newTarefa.getPrimaryKey());

		Assert.assertEquals(existingTarefa, newTarefa);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Tarefa missingTarefa = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingTarefa);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		Tarefa newTarefa1 = addTarefa();
		Tarefa newTarefa2 = addTarefa();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newTarefa1.getPrimaryKey());
		primaryKeys.add(newTarefa2.getPrimaryKey());

		Map<Serializable, Tarefa> tarefas = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(2, tarefas.size());
		Assert.assertEquals(
			newTarefa1, tarefas.get(newTarefa1.getPrimaryKey()));
		Assert.assertEquals(
			newTarefa2, tarefas.get(newTarefa2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Tarefa> tarefas = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(tarefas.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		Tarefa newTarefa = addTarefa();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newTarefa.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Tarefa> tarefas = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, tarefas.size());
		Assert.assertEquals(newTarefa, tarefas.get(newTarefa.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Tarefa> tarefas = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(tarefas.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		Tarefa newTarefa = addTarefa();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newTarefa.getPrimaryKey());

		Map<Serializable, Tarefa> tarefas = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, tarefas.size());
		Assert.assertEquals(newTarefa, tarefas.get(newTarefa.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			TarefaLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Tarefa>() {

				@Override
				public void performAction(Tarefa tarefa) {
					Assert.assertNotNull(tarefa);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		Tarefa newTarefa = addTarefa();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Tarefa.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("tarefaId", newTarefa.getTarefaId()));

		List<Tarefa> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Tarefa existingTarefa = result.get(0);

		Assert.assertEquals(existingTarefa, newTarefa);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Tarefa.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("tarefaId", RandomTestUtil.nextLong()));

		List<Tarefa> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		Tarefa newTarefa = addTarefa();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Tarefa.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("tarefaId"));

		Object newTarefaId = newTarefa.getTarefaId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in("tarefaId", new Object[] {newTarefaId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingTarefaId = result.get(0);

		Assert.assertEquals(existingTarefaId, newTarefaId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Tarefa.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("tarefaId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"tarefaId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		Tarefa newTarefa = addTarefa();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(newTarefa.getPrimaryKey()));
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromDatabase()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(true);
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromSession()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(false);
	}

	private void _testResetOriginalValuesWithDynamicQuery(boolean clearSession)
		throws Exception {

		Tarefa newTarefa = addTarefa();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Tarefa.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("tarefaId", newTarefa.getTarefaId()));

		List<Tarefa> result = _persistence.findWithDynamicQuery(dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(Tarefa tarefa) {
		Assert.assertEquals(
			tarefa.getUuid(),
			ReflectionTestUtil.invoke(
				tarefa, "getColumnOriginalValue", new Class<?>[] {String.class},
				"uuid_"));
		Assert.assertEquals(
			Long.valueOf(tarefa.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				tarefa, "getColumnOriginalValue", new Class<?>[] {String.class},
				"groupId"));
	}

	protected Tarefa addTarefa() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Tarefa tarefa = _persistence.create(pk);

		tarefa.setUuid(RandomTestUtil.randomString());

		tarefa.setGroupId(RandomTestUtil.nextLong());

		tarefa.setCompanyId(RandomTestUtil.nextLong());

		tarefa.setUserId(RandomTestUtil.nextLong());

		tarefa.setUserName(RandomTestUtil.randomString());

		tarefa.setCreateDate(RandomTestUtil.nextDate());

		tarefa.setModifiedDate(RandomTestUtil.nextDate());

		tarefa.setTitulo(RandomTestUtil.randomString());

		tarefa.setDescricao(RandomTestUtil.randomString());

		tarefa.setUrlImagem(RandomTestUtil.randomString());

		tarefa.setStatus(RandomTestUtil.nextInt());

		tarefa.setTarefaPaiId(RandomTestUtil.nextLong());

		tarefa.setFileEntryId(RandomTestUtil.nextLong());

		_tarefas.add(_persistence.update(tarefa));

		return tarefa;
	}

	private List<Tarefa> _tarefas = new ArrayList<Tarefa>();
	private TarefaPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}