/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.pedro.dev.tarefa.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TarefaService}.
 *
 * @author Brian Wing Shun Chan
 * @see TarefaService
 * @generated
 */
public class TarefaServiceWrapper
	implements ServiceWrapper<TarefaService>, TarefaService {

	public TarefaServiceWrapper() {
		this(null);
	}

	public TarefaServiceWrapper(TarefaService tarefaService) {
		_tarefaService = tarefaService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _tarefaService.getOSGiServiceIdentifier();
	}

	@Override
	public TarefaService getWrappedService() {
		return _tarefaService;
	}

	@Override
	public void setWrappedService(TarefaService tarefaService) {
		_tarefaService = tarefaService;
	}

	private TarefaService _tarefaService;

}