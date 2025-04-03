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

import com.pedro.dev.tarefa.exception.NoSuchTarefasException;
import com.pedro.dev.tarefa.model.Tarefas;
import com.pedro.dev.tarefa.service.TarefasLocalServiceUtil;
import com.pedro.dev.tarefa.service.persistence.TarefasPersistence;
import com.pedro.dev.tarefa.service.persistence.TarefasUtil;

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
public class TarefasPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.pedro.dev.tarefa.service"));

	@Before
	public void setUp() {
		_persistence = TarefasUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Tarefas> iterator = _tarefases.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Tarefas tarefas = _persistence.create(pk);

		Assert.assertNotNull(tarefas);

		Assert.assertEquals(tarefas.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Tarefas newTarefas = addTarefas();

		_persistence.remove(newTarefas);

		Tarefas existingTarefas = _persistence.fetchByPrimaryKey(
			newTarefas.getPrimaryKey());

		Assert.assertNull(existingTarefas);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addTarefas();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Tarefas newTarefas = _persistence.create(pk);

		newTarefas.setUuid(RandomTestUtil.randomString());

		newTarefas.setGroupId(RandomTestUtil.nextLong());

		newTarefas.setCompanyId(RandomTestUtil.nextLong());

		newTarefas.setUserId(RandomTestUtil.nextLong());

		newTarefas.setUserName(RandomTestUtil.randomString());

		newTarefas.setCreateDate(RandomTestUtil.nextDate());

		newTarefas.setModifiedDate(RandomTestUtil.nextDate());

		newTarefas.setTitulo(RandomTestUtil.randomString());

		newTarefas.setDescricao(RandomTestUtil.randomString());

		newTarefas.setUrlImagem(RandomTestUtil.randomString());

		newTarefas.setStatus(RandomTestUtil.nextInt());

		newTarefas.setTarefaPaiId(RandomTestUtil.nextLong());

		_tarefases.add(_persistence.update(newTarefas));

		Tarefas existingTarefas = _persistence.findByPrimaryKey(
			newTarefas.getPrimaryKey());

		Assert.assertEquals(existingTarefas.getUuid(), newTarefas.getUuid());
		Assert.assertEquals(
			existingTarefas.getTarefaId(), newTarefas.getTarefaId());
		Assert.assertEquals(
			existingTarefas.getGroupId(), newTarefas.getGroupId());
		Assert.assertEquals(
			existingTarefas.getCompanyId(), newTarefas.getCompanyId());
		Assert.assertEquals(
			existingTarefas.getUserId(), newTarefas.getUserId());
		Assert.assertEquals(
			existingTarefas.getUserName(), newTarefas.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingTarefas.getCreateDate()),
			Time.getShortTimestamp(newTarefas.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingTarefas.getModifiedDate()),
			Time.getShortTimestamp(newTarefas.getModifiedDate()));
		Assert.assertEquals(
			existingTarefas.getTitulo(), newTarefas.getTitulo());
		Assert.assertEquals(
			existingTarefas.getDescricao(), newTarefas.getDescricao());
		Assert.assertEquals(
			existingTarefas.getUrlImagem(), newTarefas.getUrlImagem());
		Assert.assertEquals(
			existingTarefas.getStatus(), newTarefas.getStatus());
		Assert.assertEquals(
			existingTarefas.getTarefaPaiId(), newTarefas.getTarefaPaiId());
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
	public void testCountByfindByChildren() throws Exception {
		_persistence.countByfindByChildren(RandomTestUtil.nextLong());

		_persistence.countByfindByChildren(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Tarefas newTarefas = addTarefas();

		Tarefas existingTarefas = _persistence.findByPrimaryKey(
			newTarefas.getPrimaryKey());

		Assert.assertEquals(existingTarefas, newTarefas);
	}

	@Test(expected = NoSuchTarefasException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<Tarefas> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"ToDoList_Tarefas", "uuid", true, "tarefaId", true, "groupId", true,
			"companyId", true, "userId", true, "userName", true, "createDate",
			true, "modifiedDate", true, "titulo", true, "descricao", true,
			"urlImagem", true, "status", true, "tarefaPaiId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Tarefas newTarefas = addTarefas();

		Tarefas existingTarefas = _persistence.fetchByPrimaryKey(
			newTarefas.getPrimaryKey());

		Assert.assertEquals(existingTarefas, newTarefas);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Tarefas missingTarefas = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingTarefas);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		Tarefas newTarefas1 = addTarefas();
		Tarefas newTarefas2 = addTarefas();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newTarefas1.getPrimaryKey());
		primaryKeys.add(newTarefas2.getPrimaryKey());

		Map<Serializable, Tarefas> tarefases = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(2, tarefases.size());
		Assert.assertEquals(
			newTarefas1, tarefases.get(newTarefas1.getPrimaryKey()));
		Assert.assertEquals(
			newTarefas2, tarefases.get(newTarefas2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Tarefas> tarefases = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(tarefases.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		Tarefas newTarefas = addTarefas();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newTarefas.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Tarefas> tarefases = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, tarefases.size());
		Assert.assertEquals(
			newTarefas, tarefases.get(newTarefas.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Tarefas> tarefases = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(tarefases.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		Tarefas newTarefas = addTarefas();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newTarefas.getPrimaryKey());

		Map<Serializable, Tarefas> tarefases = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, tarefases.size());
		Assert.assertEquals(
			newTarefas, tarefases.get(newTarefas.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			TarefasLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Tarefas>() {

				@Override
				public void performAction(Tarefas tarefas) {
					Assert.assertNotNull(tarefas);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		Tarefas newTarefas = addTarefas();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Tarefas.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("tarefaId", newTarefas.getTarefaId()));

		List<Tarefas> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Tarefas existingTarefas = result.get(0);

		Assert.assertEquals(existingTarefas, newTarefas);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Tarefas.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("tarefaId", RandomTestUtil.nextLong()));

		List<Tarefas> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		Tarefas newTarefas = addTarefas();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Tarefas.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("tarefaId"));

		Object newTarefaId = newTarefas.getTarefaId();

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
			Tarefas.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("tarefaId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"tarefaId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		Tarefas newTarefas = addTarefas();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(newTarefas.getPrimaryKey()));
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

		Tarefas newTarefas = addTarefas();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Tarefas.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("tarefaId", newTarefas.getTarefaId()));

		List<Tarefas> result = _persistence.findWithDynamicQuery(dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(Tarefas tarefas) {
		Assert.assertEquals(
			tarefas.getUuid(),
			ReflectionTestUtil.invoke(
				tarefas, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "uuid_"));
		Assert.assertEquals(
			Long.valueOf(tarefas.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				tarefas, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "groupId"));
	}

	protected Tarefas addTarefas() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Tarefas tarefas = _persistence.create(pk);

		tarefas.setUuid(RandomTestUtil.randomString());

		tarefas.setGroupId(RandomTestUtil.nextLong());

		tarefas.setCompanyId(RandomTestUtil.nextLong());

		tarefas.setUserId(RandomTestUtil.nextLong());

		tarefas.setUserName(RandomTestUtil.randomString());

		tarefas.setCreateDate(RandomTestUtil.nextDate());

		tarefas.setModifiedDate(RandomTestUtil.nextDate());

		tarefas.setTitulo(RandomTestUtil.randomString());

		tarefas.setDescricao(RandomTestUtil.randomString());

		tarefas.setUrlImagem(RandomTestUtil.randomString());

		tarefas.setStatus(RandomTestUtil.nextInt());

		tarefas.setTarefaPaiId(RandomTestUtil.nextLong());

		_tarefases.add(_persistence.update(tarefas));

		return tarefas;
	}

	private List<Tarefas> _tarefases = new ArrayList<Tarefas>();
	private TarefasPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}