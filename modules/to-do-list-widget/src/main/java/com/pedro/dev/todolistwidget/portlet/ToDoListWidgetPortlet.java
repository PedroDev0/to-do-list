package com.pedro.dev.todolistwidget.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.pedro.dev.todolistwidget.constants.ToDoListWidgetPortletKeys;


import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author vertigo
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=ToDoListWidget",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ToDoListWidgetPortletKeys.TODOLISTWIDGET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ToDoListWidgetPortlet extends MVCPortlet {
}