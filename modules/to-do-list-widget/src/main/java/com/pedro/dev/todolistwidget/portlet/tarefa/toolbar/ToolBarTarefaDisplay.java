package com.pedro.dev.todolistwidget.portlet.tarefa.toolbar;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.BaseManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.*;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.*;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.pedro.dev.todolistwidget.constants.ToDoListWidgetPortletKeys;
import com.pedro.dev.todolistwidget.portlet.constants.MVCComandKeys;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ToolBarTarefaDisplay extends BaseManagementToolbarDisplayContext {

    private final PortalPreferences _portalPreferences;
    private final ThemeDisplay _themeDisplay;

    public ToolBarTarefaDisplay(LiferayPortletRequest liferayPortletRequest,
                               LiferayPortletResponse liferayPortletResponse, HttpServletRequest httpServletRequest) {
        super(liferayPortletRequest, liferayPortletResponse, httpServletRequest);

        _portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(liferayPortletRequest);

        _themeDisplay = (ThemeDisplay) httpServletRequest.getAttribute(WebKeys.THEME_DISPLAY);
    }

    /**
     * Returns the creation menu for the toolbar
     * (plus sign on the management toolbar).
     *
     * @return creation menu
     */
    public CreationMenu getCreationMenu() {

        // Create the menu.
        return new CreationMenu() {
            {
                addDropdownItem(
                        dropdownItem -> {
                            dropdownItem.setHref(
                                    liferayPortletResponse.createRenderURL(),
                                    "mvcRenderCommandName", MVCComandKeys.TAREFA_CRIAR, // trocar para o formulario de cadastro
                                    "redirect", currentURLObj.toString());
                            dropdownItem.setLabel(LanguageUtil.get(request, "add-promo"));
                        }
                );
            }
        };
    }

    @Override
    public String getClearResultsURL() {
        return getSearchActionURL();
    }

    /**
     * Returns the entidade list display style.
     * <p>
     * Current selection is stored in portal preferences.
     *
     * @return current display style
     */
    public String getDisplayStyle() {

        String displayStyle = ParamUtil.getString(request, "displayStyle");

        if (Validator.isNull(displayStyle)) {
            displayStyle = _portalPreferences.getValue(ToDoListWidgetPortletKeys.TODOLISTWIDGET, "entidades-display-style", "list");
        } else {
            _portalPreferences.setValue(ToDoListWidgetPortletKeys.TODOLISTWIDGET, "entidades-display-style", displayStyle);

            request.setAttribute(WebKeys.SINGLE_PAGE_APPLICATION_CLEAR_CACHE, Boolean.TRUE);
        }

        return displayStyle;
    }

    /**
     * Returns the sort order column.
     *
     * @return sort column
     */
    public String getOrderByCol() {

        return ParamUtil.getString(request, "orderByCol", "titulo");
    }

    /**
     * Returns the sort type (ascending / descending).
     *
     * @return sort type
     */
    public String getOrderByType() {

        return ParamUtil.getString(request, "orderByType", "asc");
    }

    /**
     * Returns the action URL for the search.
     *
     * @return search action URL
     */
    @Override
    public String getSearchActionURL() {

        PortletURL searchURL = liferayPortletResponse.createRenderURL();

        searchURL.getRenderParameters().setValue("mvcRenderCommandName", MVCComandKeys.TAREFA_LISTAR);

        String navigation = ParamUtil.getString(request, "navigation", "entries");
        searchURL.getRenderParameters().setValue("navigation", navigation);

        searchURL.getRenderParameters().setValue("orderByCol", getOrderByCol());
        searchURL.getRenderParameters().setValue("orderByType", getOrderByType());

        return searchURL.toString();
    }


    /**
     * Returns the view type options (card, list, table).
     *
     * @return list of view types
     */
    @Override
    public List<ViewTypeItem> getViewTypeItems() {

        PortletURL portletURL = liferayPortletResponse.createRenderURL();

        portletURL.getRenderParameters().setValue("mvcRenderCommandName", MVCComandKeys.TAREFA_LISTAR);

        int delta = ParamUtil.getInteger(request, SearchContainer.DEFAULT_DELTA_PARAM);

        if (delta > 0) portletURL.getRenderParameters().setValue("delta", String.valueOf(delta));

        String orderByCol = ParamUtil.getString(request, "orderByCol", "titulo");
        String orderByType = ParamUtil.getString(request, "orderByType", "asc");

        portletURL.getRenderParameters().setValue("orderByCol", orderByCol);
        portletURL.getRenderParameters().setValue("orderByType", orderByType);

        int cur = ParamUtil.getInteger(request, SearchContainer.DEFAULT_CUR_PARAM);

        if (cur > 0) portletURL.getRenderParameters().setValue("cur", String.valueOf(cur));

        return new ViewTypeItemList(portletURL, getDisplayStyle()) {
            {
                addCardViewTypeItem();

                addListViewTypeItem();

                addTableViewTypeItem();
            }
        };
    }

    /**
     * Return the option items for the sort column menu.
     *
     * @return options list for the sort column menu
     */
    @Override
    protected List<DropdownItem> getOrderByDropdownItems() {
        return new DropdownItemList() {
            {
                add(
                        dropdownItem -> {
                            dropdownItem.setActive("title".equals(getOrderByCol()));
                            dropdownItem.setHref(_getCurrentSortingURL(), "orderByCol", "titulo");
                            dropdownItem.setLabel(LanguageUtil.get(request, "title"));
                        }
                );
            }
        };
    }

    /**
     * Returns the current sorting URL.
     *
     * @return current sorting portlet URL
     * @throws javax.portlet.PortletException
     */
    private PortletURL _getCurrentSortingURL() throws PortletException {

        PortletURL sortingURL = PortletURLUtil.clone(currentURLObj, liferayPortletResponse);

        sortingURL.getRenderParameters().setValue("mvcRenderCommandName", MVCComandKeys.TAREFA_LISTAR);

        // Reset current page.

        sortingURL.getRenderParameters().setValue(SearchContainer.DEFAULT_CUR_PARAM, "0");

        String keywords = ParamUtil.getString(request, "keywords");

        sortingURL.getRenderParameters().setValue("keywords", keywords);

        return sortingURL;
    }
}


