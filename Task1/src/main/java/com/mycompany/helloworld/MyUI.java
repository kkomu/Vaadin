package com.mycompany.helloworld;

import com.mycompany.views.FirstView;
import com.mycompany.views.SecondView;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator.ComponentContainerViewDisplay;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.Console;
import java.io.File;
import java.util.Iterator;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.mycompany.helloworld.MyAppWidgetset")
public class MyUI extends UI implements Button.ClickListener {

    private HorizontalLayout mainLayout = new HorizontalLayout();
    private VerticalLayout txtFields = new VerticalLayout();
    private TextField fname = new TextField("Firstname");
    private TextField lname = new TextField("Lastname");
    private TextField address = new TextField("Address");
    private TextField email = new TextField("Email");
    private TextField phone = new TextField("Phonenumber");
    private Button addButton = new Button("Add Contact");
    private Table contactList = new Table();
    
    @Override
    public void buttonClick(Button.ClickEvent event) {
        Notification.show("Button pressed");
    }

    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        this.setContent(mainLayout);
        
        txtFields.addComponent(fname);
        txtFields.addComponent(lname);
        txtFields.addComponent(address);
        txtFields.addComponent(email);
        txtFields.addComponent(phone);
        txtFields.addComponent(addButton);
        
        txtFields.setMargin(true);
        txtFields.setSpacing(true);
        
        mainLayout.addComponent(txtFields);
        mainLayout.addComponent(contactList);
        
        // Alternative implementation for addButton event handler
        //addButton.addClickListener(this);
        
        // Anonymous event handler for addButton
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                StringBuilder errMessage = new StringBuilder();
                Iterator<Component> iter = txtFields.iterator();
                while(iter.hasNext()) {
                    Component c = iter.next();
                    if(c instanceof TextField) {
                        TextField a = (TextField)c;
                        if(a.getValue().isEmpty()) {
                            errMessage.append(a.getCaption() + " ");
                        }
                    }
                }
                
                String err = errMessage.toString();
                
                if(err.isEmpty()) {
                    addItemToTable();
                    fname.setValue("");
                    lname.setValue("");
                    address.setValue("");
                    phone.setValue("");
                    email.setValue("");
                }
                else {
                    Notification.show(err + "field(s) empty");
                }
            }
        });
        
        contactList.addItemClickListener(new ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                Notification.show(event.getItem().getItemProperty("Firstname").getValue().toString());
            }
        });
        
        this.initTableData();
    }
    
    private void initTableData(){
   
        contactList.setContainerDataSource(createDummyDataSource());
        contactList.setVisibleColumns(new String[]{"Firstname","Lastname","Address","Email","Phone"});
    }
    
    private void addItemToTable(){
     
        Object objectId = contactList.addItem();
        Item row = contactList.getItem(objectId);
        row.getItemProperty("Firstname").setValue(fname.getValue());
        row.getItemProperty("Lastname").setValue(lname.getValue());
        row.getItemProperty("Address").setValue(address.getValue());
        row.getItemProperty("Email").setValue(email.getValue());
        row.getItemProperty("Phone").setValue(phone.getValue());
    }
    
    private IndexedContainer createDummyDataSource() {
        
        IndexedContainer ic = new IndexedContainer();
        ic.addContainerProperty("Firstname", String.class, "");
        ic.addContainerProperty("Lastname", String.class, "");
        ic.addContainerProperty("Address", String.class, "");
        ic.addContainerProperty("Email", String.class, "");
        ic.addContainerProperty("Phone", String.class, "");
        return ic;
    }
    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    
    
}