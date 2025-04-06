package com.pedro.dev.todolistwidget.portlet.tarefa.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;

public class UrlLoginUtil {

    public static void createUrlLogin(RenderRequest renderRequest) throws WindowStateException, PortletModeException, PortalException {

        HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(renderRequest);
        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

        // Busca a página pelo friendly URL "/home"
        Layout layout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), // grupo atual
                false, // false = layout público
                "/home");

        // Garante que o layout tem o portlet do login
        String portletId = PortletKeys.LOGIN;

        // Cria a URL amigável
        PortletURL loginURL = PortletURLFactoryUtil.create(httpRequest, portletId, layout.getPlid(), // usa o layout da página "/home"
                PortletRequest.RENDER_PHASE);

        loginURL.setParameter("mvcRenderCommandName", "/login/login");
        loginURL.setParameter("saveLastPath", "false");
        loginURL.setWindowState(LiferayWindowState.MAXIMIZED);
        loginURL.setPortletMode(PortletMode.VIEW);

        String loginUrl = loginURL.toString();
        renderRequest.setAttribute("loginUrl", loginUrl);

    }
}
