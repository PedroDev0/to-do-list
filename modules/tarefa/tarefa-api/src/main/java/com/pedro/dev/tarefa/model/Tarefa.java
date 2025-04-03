/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.pedro.dev.tarefa.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the Tarefa service. Represents a row in the &quot;ToDoList_Tarefa&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see TarefaModel
 * @generated
 */
@ImplementationClassName("com.pedro.dev.tarefa.model.impl.TarefaImpl")
@ProviderType
public interface Tarefa extends PersistedModel, TarefaModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.pedro.dev.tarefa.model.impl.TarefaImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Tarefa, Long> TAREFA_ID_ACCESSOR =
		new Accessor<Tarefa, Long>() {

			@Override
			public Long get(Tarefa tarefa) {
				return tarefa.getTarefaId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Tarefa> getTypeClass() {
				return Tarefa.class;
			}

		};

}