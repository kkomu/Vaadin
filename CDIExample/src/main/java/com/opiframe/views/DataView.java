/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opiframe.views;

import com.opiframe.entity.Customer;
import com.opiframe.session.CustomerFacade;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import javax.inject.Inject;


/**
 *
 * @author Ohjelmistokehitys
 */
@CDIView("DataView")
public class DataView extends VerticalLayout implements View {

    private final Label l = new Label("Welcome to DataView");
    private Button nextButton = new Button("Next");
    private Navigator navigator;
    private Table table = new Table();
    
    @Inject
    CustomerFacade customer;
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.addComponent(l);
        this.addComponent(nextButton);
        
        BeanItemContainer<Customer> bean = new BeanItemContainer(Customer.class);
        bean.addAll(customer.findAll());
        table.setContainerDataSource(bean);
        table.setVisibleColumns(new Object[] {"addressline1","city","email"});
        table.setColumnHeaders(new String[] {"Address","City","Email"});
        this.addComponent(table);
        
        navigator = this.getUI().getNavigator();
        
        nextButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                navigator.navigateTo("SecondView");
            }
        });
    }
}
