/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
package com.pedro.dev.tarefa.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class TarefaExceptionException extends PortalException {

	public TarefaExceptionException() {
	}

	public TarefaExceptionException(String msg) {
		super(msg);
	}

	public TarefaExceptionException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public TarefaExceptionException(Throwable throwable) {
		super(throwable);
	}

}