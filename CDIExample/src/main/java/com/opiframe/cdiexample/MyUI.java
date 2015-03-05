package com.opiframe.cdiexample;


import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import javax.inject.Inject;

/**
 *
 */
@Theme("mytheme")
@CDIUI("")
@Widgetset("com.opiframe.cdiexample.MyAppWidgetset")
public class MyUI extends UI {

    @Inject
    private CDIViewProvider viewProvider;
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Navigator navigator = new Navigator(this, this);
        navigator.addProvider(viewProvider);
        navigator.navigateTo("DataView");
    }
}
