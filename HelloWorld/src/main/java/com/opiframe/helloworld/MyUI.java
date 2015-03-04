package com.opiframe.helloworld;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.opiframe.helloworld.MyAppWidgetset")
public class MyUI extends UI {

    private final VerticalLayout txtFldLayout = new VerticalLayout();
    private final HorizontalLayout mainLayout = new HorizontalLayout();
    private final TextField txtName = new TextField("Name");
    private final TextField txtAddress = new TextField("Address");
    private final TextField txtPhone = new TextField("Phone");
    private final TextField txtEmail = new TextField("Email");
    private final Button btnOk = new Button("Ok");
    private final Table tblTable = new Table();
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        this.setContent(mainLayout);
        
        txtFldLayout.addComponent(txtName);
        txtFldLayout.addComponent(txtAddress);
        txtFldLayout.addComponent(txtPhone);
        txtFldLayout.addComponent(txtEmail);
        txtFldLayout.addComponent(btnOk);
        txtFldLayout.setMargin(true);
        txtFldLayout.setSpacing(true);
        
        mainLayout.addComponent(txtFldLayout);
        mainLayout.addComponent(tblTable);
        
        tblTable.setVisibleColumns(new Object[] {"Name","Address","Phone","Email"});
        tblTable.setColumnHeaders(new String[] {"Name","Address","Phone","Email"});
        
        /*
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                layout.addComponent(new Label("Thank you for clicking"));
            }
        });
        layout.addComponent(button);
        */
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
