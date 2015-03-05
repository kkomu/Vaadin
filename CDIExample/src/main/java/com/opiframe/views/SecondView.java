/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opiframe.views;

import com.opiframe.entity.MicroMarket;
import com.opiframe.session.MicroMarketFacade;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import javax.inject.Inject;

/**
 *
 * @author Ohjelmistokehitys
 */
@CDIView("SecondView")
public class SecondView extends HorizontalLayout implements View {
    
    @Inject
    MicroMarketFacade microMarket;

    Table table = new Table();
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        BeanItemContainer bean = new BeanItemContainer(MicroMarket.class);
        bean.addAll(microMarket.findAll());
        
        table.setContainerDataSource(bean);
        table.setVisibleColumns(new Object[] {"radius","zipCode"});
        table.setColumnHeaders(new String[] {"Radius","ZIP code"});
        this.addComponent(table);
    }
}
