/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author opiframe
 */
public class SecondView extends VerticalLayout implements View{
 
    public static final String NAME = "Second";
    
    public SecondView(final Navigator nav){
                
        Label l = new Label("This is the second view in Navigator");
        Button btn = new Button("Using this you can go back");
        btn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                nav.navigateTo(FirstView.NAME);
            }
        });
        this.addComponent(l);
        this.addComponent(btn);
    }
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
